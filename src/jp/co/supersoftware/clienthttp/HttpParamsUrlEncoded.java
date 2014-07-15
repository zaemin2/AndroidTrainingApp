package jp.co.supersoftware.clienthttp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * HTTP POST parameter that are sent as {@link form-urlencoded}
 */
public class HttpParamsUrlEncoded implements HttpPostParameters {

	private final ArrayList<NameValuePair> mParams;
	private String encodedParams;
	private static final String CONTENT_TYPE = URLEncodedUtils.CONTENT_TYPE + "; charset=utf-8";

	/**
	 * Basic constructor
	 */
	public HttpParamsUrlEncoded() {
		mParams = new ArrayList<NameValuePair>();
	}

	/**
	 * Constructor with an initial amount of parameters to hold
	 * @param capacity amount of parameters the object will get
	 */
	public HttpParamsUrlEncoded(int capacity) {
		mParams = new ArrayList<NameValuePair>(capacity);
	}

	private String getEncodedParams() {
		if (null==encodedParams) {
			encodedParams = URLEncodedUtils.format(mParams, "UTF-8").replace("*", "%2A");
			mParams.clear();
		}
		return encodedParams;
	}
	
	@Override
	public void settleHttpHeaders(HttpRequestPost request) {
		request.setHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE);
	}
	
	@Override
	public void setConnectionProperties(HttpURLConnection connection) {
		connection.setFixedLengthStreamingMode(getEncodedParams().getBytes().length);
	}

	@Override
	public void writeBodyTo(OutputStream output, HttpRequestPost request, UploadProgressListener progressListener) throws UnsupportedEncodingException, IOException {
		output.write(getEncodedParams().getBytes());
	}

	@Override
	public void add(String name, String value) {
		mParams.add(new BasicNameValuePair(name, value));
	}

	@Override
	public void add(String name, boolean b) {
		add(name, String.valueOf(b));
	}

	@Override
	public void add(String name, int i) {
		add(name, Integer.toString(i));
	}

	@Override
	public void add(String name, long l) {
		add(name, Long.toString(l));
	}
}
