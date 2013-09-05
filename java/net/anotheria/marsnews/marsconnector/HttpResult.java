package net.anotheria.marsnews.marsconnector;

import java.util.HashMap;
import java.util.Map;

public class HttpResult {
	private int resultCode;
	
	private byte[] data;
	
	private Map<String,String> headers;
	
	public HttpResult(){
		headers = new HashMap<String, String>();
	}
	
	public HttpResult(int aResultCode){
		this();
		resultCode = aResultCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public void addHeader(String name, String value){
		headers.put(name, value);
	}
	
	public String getHeader(String name){
		return headers.get(name);
	}

	public String getDataAsString(){
		return new String(data);
	}
}
