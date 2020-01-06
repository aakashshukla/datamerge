package com.mariner.datamergeandsort;

import java.util.Date;

public class ReportsModel {
	private String clientAddress;
	private String clientGuid;
	private Date requestTime;
	private String serviceGuid;
	private int retriesRequest;
	private int packetsRequested;
	private int packetsServiced;
	private int maxHoleSize;
	
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getClientGuid() {
		return clientGuid;
	}
	public void setClientGuid(String clientGuid) {
		this.clientGuid = clientGuid;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public String getServiceGuid() {
		return serviceGuid;
	}
	public void setServiceGuid(String serviceGuid) {
		this.serviceGuid = serviceGuid;
	}
	public int getRetriesRequest() {
		return retriesRequest;
	}
	public void setRetriesRequest(int retriesRequest) {
		this.retriesRequest = retriesRequest;
	}
	public int getPacketsRequested() {
		return packetsRequested;
	}
	public void setPacketsRequested(int packetsRequested) {
		this.packetsRequested = packetsRequested;
	}
	public int getPacketsServiced() {
		return packetsServiced;
	}
	public void setPacketsServiced(int packetsServiced) {
		this.packetsServiced = packetsServiced;
	}
	public int getMaxHoleSize() {
		return maxHoleSize;
	}
	public void setMaxHoleSize(int maxHoleSize) {
		this.maxHoleSize = maxHoleSize;
	}


}
