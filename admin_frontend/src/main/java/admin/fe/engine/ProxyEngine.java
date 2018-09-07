package admin.fe.engine;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URL;
import java.security.cert.X509Certificate;

/*

Author Muhammad Burhanudin

 */

public class ProxyEngine {

    public String proxy(String destPath, String body, HttpMethod method, HttpServletRequest request,
                        HttpServletResponse response) {
        String result = "";

        try {
            URL url = new URL(destPath);
            String path = url.getPath();

            URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), path, request.getQueryString(), null);

            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
            } };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) { return true; }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            if (request.getHeader("AuthenticationToken") != null) {

                System.out.println("header -> AuthenticationToken : " + request.getHeader("AuthenticationToken"));
                headers.add("AuthenticationToken", request.getHeader("AuthenticationToken"));
            }

            if (request.getHeader("WsSecurityUserName") != null) {

                System.out.println("header -> WsSecurityUserName : " + request.getHeader("WsSecurityUserName"));
                headers.add("WsSecurityUserName", request.getHeader("WsSecurityUserName"));
            }

            if (request.getHeader("WsSecurityPassword") != null) {

                System.out.println("header -> WsSecurityPassword : " + request.getHeader("WsSecurityPassword"));
                headers.add("WsSecurityPassword", request.getHeader("WsSecurityPassword"));
            }

            HttpEntity<String> entity = new HttpEntity<String>(body, headers);

            System.out.println("==> proxy to " + uri.toString() + " : " + body);

            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, method, entity, String.class);

            System.out.println("==> reply : " + responseEntity.getStatusCode().toString() + " - " + responseEntity.getBody());

            return responseEntity.getBody();
        }
        catch (Exception e) {

            System.out.println("Error proxy to avantrade : " + destPath);
            e.printStackTrace(System.out);
        }

        return result;
    }



}
