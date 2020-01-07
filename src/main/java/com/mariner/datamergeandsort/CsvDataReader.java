package com.mariner.datamergeandsort;

import java.io.Reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Class to read CSV data and parse it
 */
public class CsvDataReader implements DataReader {
	
	/**
	 * read CSV file and add result to the List
	 * 
	 * @return list of results where value of PacketsServiced field greater than 0
	 */
    public List<ReportsModel> readFile() {
    	List<ReportsModel> dataReportList = new ArrayList<>();
    	
    	try {        
            Reader reader = Files.newBufferedReader(Paths.get(this.getClass().getClassLoader().getResource("reports.csv").getFile()));
            
            CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withHeader(Reports.CLIENT_ADDRESS.getClientData(), Reports.CLIENT_GUID.getClientData(), 
							Reports.REQUEST_TIME.getClientData(), Reports.SERVICE_GUID.getClientData(), Reports.RETRIES_REQUEST.getClientData(), 
							Reports.PACKETS_REQUESTED.getClientData(), Reports.PACKETS_SERVICED.getClientData(),Reports.MAX_HOLE_SIZE.getClientData()));
            
		for (CSVRecord csvRecord : csvParser) {
			ReportsModel dataReport = new ReportsModel();
			
			dataReport.setClientAddress(csvRecord.get(Reports.CLIENT_ADDRESS.getClientData()));
			dataReport.setClientGuid(csvRecord.get(Reports.CLIENT_GUID.getClientData()));
			dataReport.setRequestTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z").parse(csvRecord.get(Reports.REQUEST_TIME.getClientData())));
			dataReport.setServiceGuid(csvRecord.get(Reports.SERVICE_GUID.getClientData()));
			dataReport.setRetriesRequest(Integer.parseInt(csvRecord.get(Reports.RETRIES_REQUEST.getClientData())));
			dataReport.setPacketsRequested(Integer.parseInt(csvRecord.get(Reports.PACKETS_REQUESTED.getClientData())));
			dataReport.setPacketsServiced(Integer.parseInt(csvRecord.get(Reports.PACKETS_SERVICED.getClientData())));
			dataReport.setMaxHoleSize(Integer.parseInt(csvRecord.get(Reports.MAX_HOLE_SIZE.getClientData())));
			
			if (dataReport.getPacketsServiced() != 0) {
				dataReportList.add(dataReport);
			}
		}
    	} catch (Exception e) {
    		System.out.println(e);
    	}
		return dataReportList;
    }
}
