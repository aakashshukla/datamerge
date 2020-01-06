package com.mariner.datamergeandsort;

public enum Reports {
	CLIENT_ADDRESS ("client-address"),
	CLIENT_GUID ("client-guid"),
	REQUEST_TIME ("request-time"),
	SERVICE_GUID ("service-guid"),
	RETRIES_REQUEST ("retries-request"),
	PACKETS_REQUESTED ("packets-requested"),
	PACKETS_SERVICED ("packets-serviced"),
	MAX_HOLE_SIZE ("max-hole-size")
	;

private final String clientData;

Reports(String clientData) {
    this.clientData = clientData;
}

public String getClientData() {
    return this.clientData;
}

}
