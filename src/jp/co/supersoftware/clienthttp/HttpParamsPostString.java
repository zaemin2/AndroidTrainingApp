package jp.co.supersoftware.clienthttp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.apache.http.protocol.HTTP;


/**
 * HTTP POST parameter that consists of a String data and its Content-Type 
 */
public class HttpParamsPostString implements HttpPostParameters {

	private final String value;
	private final String contentType;

	public HttpParamsPostString(String value, String contentType) {
		this.value = value;
		this.contentType = contentType;
	}

	/**
	 * Do not use, extra parameters in the URL are not supported
	 * @throws IllegalAccessError
	 */
	@Deprecated
	@Override
	public void add(String name, String value) {
		throw new IllegalAccessError();
	}

	/**
	 * Do not use, extra parameters in the URL are not supported
	 * @throws IllegalAccessError
	 */
	@Deprecated
	@Override
	public void add(String name, boolean b) {
		throw new IllegalAccessError();
	}

	/**
	 * Do not use, extra parameters in the URL are not supported
	 * @throws IllegalAccessError
	 */
	@Deprecated
	@Override
	public void add(String name, int value) {
		throw new IllegalAccessError();
	}

	/**
	 * Do not use, extra parameters in the URL are not supported
	 * @throws IllegalAccessError
	 */
	@Deprecated
	@Override
	public void add(String name, long value) {
		throw new IllegalAccessError();
	}

	@Override
	public void settleHttpHeaders(HttpRequestPost request) {
		request.setHeader(HTTP.CONTENT_TYPE, contentType);
	}
	
	@Override
	public void setConnectionProperties(HttpURLConnection connection) {
		connection.setFixedLengthStreamingMode(value.getBytes().length);
	}

	@Override
	public void writeBodyTo(OutputStream output, HttpRequestPost request, UploadProgressListener progressListener) throws IOException {
		output.write(value.getBytes());
	}
}
