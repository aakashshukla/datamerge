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

public class DataMergeAndSortController {
	protected static final Logger log = Logger.getLogger(DataMergeAndSortController.class);
	private static final String SUMMARY_PATH = "src/main/resources/summary.csv";

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
	
	private static void printSummary(List<ReportsModel> reportList) {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(SUMMARY_PATH));
			CSVPrinter csvPrinter = new CSVPrinter(writer,
					CSVFormat.DEFAULT.withHeader(Reports.CLIENT_ADDRESS.getClientData(), Reports.CLIENT_GUID.getClientData(), Reports.REQUEST_TIME.getClientData(),
							Reports.SERVICE_GUID.getClientData(), Reports.RETRIES_REQUEST.getClientData(), Reports.PACKETS_REQUESTED.getClientData(), Reports.PACKETS_SERVICED.getClientData(),
							Reports.MAX_HOLE_SIZE.getClientData()));
			
			Map<String, Integer> serviceGUIDRecords = new HashMap<>();
			int c = 0;

			for (ReportsModel report : reportList) {
				
				csvPrinter.printRecord(report.getClientAddress(), report.getClientGuid(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z").format(report.getRequestTime()),
						report.getServiceGuid(), report.getRetriesRequest(), report.getPacketsRequested(), report.getPacketsServiced(),
						report.getMaxHoleSize());

				final String serviceGUID = report.getServiceGuid();
				if (serviceGUIDRecords.containsKey(serviceGUID)) {
					Integer count = serviceGUIDRecords.get(serviceGUID);
					serviceGUIDRecords.put(serviceGUID, count + 1);
				} else {
					serviceGUIDRecords.put(serviceGUID, 1);
				}
				c++;
			}
			System.out.println("Total Count: " + c);
			csvPrinter.flush();
			log.info("ServiceGUID Summary: ");
			for (Map.Entry<String, Integer> entry : serviceGUIDRecords.entrySet()) {
				String keyId = entry.getKey();
				Object keyValue = entry.getValue();
				System.out.println(String.format("%s: %s", keyId, keyValue));
				log.info(String.format("%s: %s", keyId, keyValue));
			}

		} catch (Exception e) {
			log.error("Error Occurred: ", e);
		}
	}
}
