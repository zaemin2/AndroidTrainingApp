package jp.co.supersoftware.clienthttp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.net.Uri;


/**
 * Interface for HTTP requests to be passed to {@link HttpClient}
 * @see {@link AbstractHttpRequest} 
 */
public interface HttpRequest extends HttpExceptionCreator {

	/**
	 * Get the target URL in {@link android.net.Uri Uri} format
	 */
	Uri getUri();
	
	/**
	 * Get the target URL in {@link java.net.URL URL} format
	 * @throws MalformedURLException
	 */
	URL getURL() throws MalformedURLException;

	/**
	 * Add an extra HTTP header to this request
	 * @param name Name of the header
	 * @param value Value of the header
	 */
	void addHeader(String name, String value);

	/**
	 * Set an extra HTTP header to this request, removing all previous values
	 * @param name Name of the header
	 * @param value Value of the header
	 */
	void setHeader(String name, String value);
	
	/**
	 * Get the single value of a HTTP header for this request
	 * @param name Name of the header
	 * @return The value of the header or {@code null} if it is not set
	 */
	String getHeader(String name);

	/**
	 * Settle the HTTP headers for the lifetime of this request, useful to sign the query
	 */
	void settleHttpHeaders() throws HttpException ;

	/**
	 * Set the HTTP headers on the {@link HttpURLConnection}
	 * @param connection Set the HTTP headers and other settings on this {@link HttpURLConnection} before being processed
	 * @throws ProtocolException
	 */
	void setConnectionProperties(HttpURLConnection connection) throws ProtocolException;

	/**
	 * Output the HTTP body on the connection
	 * <p>Opening and closing the OutputStream should be done there</p>
	 * @param connection
	 * @throws IOException
	 */
	void outputBody(HttpURLConnection connection) throws IOException;

	/**
	 * Called when the request has been performed on the server, even if the response is an error
	 * @param resp Contains the received headers/data from the server
	 */
	void setResponse(HttpURLConnection resp);

	/**
	 * @return the HTTP response if there was any
	 */
	HttpURLConnection getResponse();

	/**
	 * Returns the {@link LoggerTagged} for this request or {@code null} 
	 */
	LoggerTagged getLogger();

	/**
	 * Returns the {@link HttpConfig} for this request or {@code null} 
	 */
	HttpConfig getHttpConfig();

	/**
	 * Set the {@link HttpConfig} for this request or {@code null} 
	 */
	void setHttpConfig(HttpConfig config);
	
	/**
	 * Get the list of extra headers set for this request
	 */
	Header[] getAllHeaders();
}
