package jp.co.supersoftware.clienthttp;


/**
 * HTTP config to connect to a web service
 */
public interface HttpConfig {

	/**
	 * Get the read timeout for the request (may be null)
	 * @return read timeout in milliseconds, -1 for no read timeout
	 */
	int getReadTimeout(HttpRequest request);
}
