package com.mariner.datamergeandsort;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class to read XML data and parse it
 */
public class XmlDataReader implements DataReader {
	
	/**
	 * read XML file and add result to the List
	 * 
	 * @return list of results where value of PacketsServiced field greater than 0
	 */
    public List<ReportsModel> readFile() {
    	List<ReportsModel> dataReportList = new ArrayList<>();
    	
    	try {
    		File file = new File(this.getClass().getClassLoader().getResource("reports.xml").getFile());
    		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    		Document doc = docBuilder.parse(file);
    				
    		doc.getDocumentElement().normalize();  				
    		NodeList nodeList = doc.getElementsByTagName("report");

    		for (int temp = 0; temp < nodeList.getLength(); temp++) {
    			Node node = nodeList.item(temp);	
    			if (node.getNodeType() == Node.ELEMENT_NODE) {

    				Element eElement = (Element) node;  				
    				ReportsModel dataReport = new ReportsModel();
    				
    				dataReport.setClientAddress(eElement.getElementsByTagName(Reports.CLIENT_ADDRESS.getClientData()).item(0).getTextContent());
    				dataReport.setClientGuid(eElement.getElementsByTagName(Reports.CLIENT_GUID.getClientData()).item(0).getTextContent());
    				dataReport.setRequestTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z").parse(eElement.getElementsByTagName(Reports.REQUEST_TIME.getClientData()).item(0).getTextContent()));
    				dataReport.setServiceGuid(eElement.getElementsByTagName(Reports.SERVICE_GUID.getClientData()).item(0).getTextContent());
    				dataReport.setRetriesRequest(Integer.parseInt(eElement.getElementsByTagName(Reports.RETRIES_REQUEST.getClientData()).item(0).getTextContent()));
    				dataReport.setPacketsRequested(Integer.parseInt(eElement.getElementsByTagName(Reports.PACKETS_REQUESTED.getClientData()).item(0).getTextContent()));
    				dataReport.setPacketsServiced(Integer.parseInt(eElement.getElementsByTagName(Reports.PACKETS_SERVICED.getClientData()).item(0).getTextContent()));
    				dataReport.setMaxHoleSize(Integer.parseInt(eElement.getElementsByTagName(Reports.MAX_HOLE_SIZE.getClientData()).item(0).getTextContent()));
    				
    				if (dataReport.getPacketsServiced() != 0) {
    					dataReportList.add(dataReport);
    				}
    			}
    		}
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	return dataReportList;
    }
}
