package com.mariner.datamergeandsort;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;

/**
 * Controller for data filtering and generating results
 */
public class DataMergeAndSortController {
	protected static final Logger log = Logger.getLogger(DataMergeAndSortController.class);
	private static final String RESULT = "src/main/resources/results.csv";

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
    	
		List<ReportsModel> reportList = new ArrayList<>();
		
		reportList.addAll(new CsvDataReader().readFile());
		reportList.addAll(new JsonDataReader().readFile());
		reportList.addAll(new XmlDataReader().readFile());
		
		reportList.sort(new Comparator<ReportsModel>() {
			public int compare(ReportsModel r1, ReportsModel r2) {
				return r1.getRequestTime().compareTo(r2.getRequestTime());
			}
		});
    	
    	printSummary(reportList);
    }
	
	/**
     * generate output file with summary results
     *
     * @param reportList list of data reports
     */
	private static void printSummary(List<ReportsModel> reportList) {
		try {
			Map<String, Integer> serviceGUIDRecords = new HashMap<>();
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(RESULT));
			CSVPrinter csvPrinter = new CSVPrinter(writer,
					CSVFormat.DEFAULT.withHeader(Reports.CLIENT_ADDRESS.getClientData(), Reports.CLIENT_GUID.getClientData(), Reports.REQUEST_TIME.getClientData(),
							Reports.SERVICE_GUID.getClientData(), Reports.RETRIES_REQUEST.getClientData(), Reports.PACKETS_REQUESTED.getClientData(), Reports.PACKETS_SERVICED.getClientData(),
							Reports.MAX_HOLE_SIZE.getClientData()));
					
			int c = 0;
			for (ReportsModel report : reportList) {	
				csvPrinter.printRecord(report.getClientAddress(), report.getClientGuid(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z").format(report.getRequestTime()),
						report.getServiceGuid(), report.getRetriesRequest(), report.getPacketsRequested(), report.getPacketsServiced(),
						report.getMaxHoleSize());

				final String serviceGUID = report.getServiceGuid();
				int count = 1;
				if (serviceGUIDRecords.containsKey(serviceGUID)) {
					count = serviceGUIDRecords.get(serviceGUID);
					count++;
				}
				serviceGUIDRecords.put(serviceGUID, count);
				c++;
			}
			System.out.println("Total Count: " + c);
			csvPrinter.flush();
			log.info("Result Summary: ");
			for (Map.Entry<String, Integer> entry : serviceGUIDRecords.entrySet()) {
				String keyId = entry.getKey();
				int valueCount = entry.getValue();
				log.info(keyId + ":: " + valueCount);
			}
		} catch (Exception e) {
			log.error("Error Occurred: ", e);
		}
	}
}
