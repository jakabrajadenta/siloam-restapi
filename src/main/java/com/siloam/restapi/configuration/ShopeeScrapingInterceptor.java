package com.siloam.restapi.configuration;

import org.codehaus.commons.nullanalysis.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopeeScrapingInterceptor implements ClientHttpRequestInterceptor {

    private static final String USER_AGENT_MAC = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.5 Safari/605.1.15";
    private static final String USER_AGENT_LINUX = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36";
    private static final String USER_AGENT_WINDOWS = "Mozilla/5.0 (Windows NT 6.0; U; en; rv:1.8.1) Gecko/20061208 Firefox/2.0.0 Opera 9.51";
    private static final String DELIMITER = " ";
    private String cookie;

    @Override
    public @NotNull ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add(HttpHeaders.USER_AGENT, USER_AGENT_WINDOWS);
//        request.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
        request.getHeaders().add(HttpHeaders.ACCEPT, "*/*");
        request.getHeaders().add(HttpHeaders.HOST, "shopee.co.id");
//        request.getHeaders().add(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.5");
        request.getHeaders().add(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
        request.getHeaders().add(HttpHeaders.CONNECTION, "keep-alive");
//        request.getHeaders().add("Upgrade-Insecure-Requests", "1");
//        request.getHeaders().add("Sec-Fetch-Dest", "document");
//        request.getHeaders().add("Sec-Fetch-Mode", "navigate");
//        request.getHeaders().add("Sec-Fetch-Site", "none");
//        request.getHeaders().add("Sec-Fetch-User", "?1");
//        request.getHeaders().add("Cache-Control", "max-age=0");
        if (cookie != null) request.getHeaders().add(HttpHeaders.COOKIE, cookie);
        ClientHttpResponse response = execution.execute(request, body);
//        var cookies = Objects.requireNonNull(response.getHeaders().get(HttpHeaders.SET_COOKIE));
//        cookie = String.join(DELIMITER, getListCookies(cookies));
        return response;
    }

    public List<String> getListCookies(List<String> cookies){
        var listCookies = new ArrayList<String>();
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("REC_T_ID")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_EC")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_F")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_R_T_ID")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_R_T_IV")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_SI")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_ST")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_T_ID")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_T_IV")) listCookies.add(s);
        } );
        cookies.forEach(s -> {
            s = s.substring(0,s.indexOf(";")+1);
            if (s.contains("SPC_U")) listCookies.add(s);
        } );
        return listCookies;
    }
}
