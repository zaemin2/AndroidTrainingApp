package jp.co.supersoftware.clienthttp;

import java.net.HttpURLConnection;


public interface CookieManager {
	public void setCookieHeader(HttpRequest request);
	public void setCookieResponse(HttpRequest request, HttpURLConnection resp);
}
