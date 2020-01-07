package com.mariner.datamergeandsort;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Class to read JSON data and parse it
 */
public class JsonDataReader implements DataReader {
	
	/**
	 * read JSON file and add result to the List
	 * 
	 * @return list of results where value of PacketsServiced field greater than 0
	 */
    public List<ReportsModel> readFile() {
    	List<ReportsModel> dataReportList = new ArrayList<>();
    	JSONParser parser = new JSONParser();
    	try {
    		Object obj = parser.parse(new FileReader(this.getClass().getClassLoader().getResource("reports.json").getFile()));
    		JSONArray jsonArray = (JSONArray) obj;
    			
			for (Object jsonRecord : jsonArray) {
				JSONObject jsonObject = (JSONObject) jsonRecord;
				ReportsModel dataReport = new ReportsModel();
				dataReport.setClientAddress(jsonObject.get(Reports.CLIENT_ADDRESS.getClientData()).toString());
				dataReport.setClientGuid(jsonObject.get(Reports.CLIENT_GUID.getClientData()).toString());
				dataReport.setRequestTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z").parse(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z").format(jsonObject.get(Reports.REQUEST_TIME.getClientData()))));
				dataReport.setServiceGuid(jsonObject.get(Reports.SERVICE_GUID.getClientData()).toString());
				dataReport.setRetriesRequest(Integer.parseInt(jsonObject.get(Reports.RETRIES_REQUEST.getClientData()).toString()));
				dataReport.setPacketsRequested(Integer.parseInt( jsonObject.get(Reports.PACKETS_REQUESTED.getClientData()).toString()));
				dataReport.setPacketsServiced(Integer.parseInt( jsonObject.get(Reports.PACKETS_SERVICED.getClientData()).toString()));
				dataReport.setMaxHoleSize(Integer.parseInt( jsonObject.get(Reports.MAX_HOLE_SIZE.getClientData()).toString()));
				
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
