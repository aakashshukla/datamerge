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

public class CsvDataReader implements DataReader {   
    public List<ReportsModel> readFile() {
    	List<ReportsModel> dataReportList = new ArrayList<>();
    	
    	try {        
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/reports.csv"));
            
            CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader());
            
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
