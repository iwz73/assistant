package idv.hsiehpinghan.tableauassistant.utility;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLUtility {

	public static void skipSslCheck(String tableauServerIp) {
		try {
			SSLContext sslc = SSLContext.getInstance("TLS");
			TrustManager[] tTrustManagers = { new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
					// do nothing
				}

				@Override
				public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
					// do nothing
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };
			sslc.init(null, tTrustManagers, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					if (hostname.equals("10.88.91.30"))
						return true;
					return false;
				}
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
