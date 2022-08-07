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
import java.util.stream.Collectors;

public class ShopeeScrapingInterceptor implements ClientHttpRequestInterceptor {

    private static final String USER_AGENT_MAC = "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
    private static final String USER_AGENT_LINUX = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
    private static final String DELIMITER = " ";
    private String cookie;

    @Override
    public @NotNull ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("User-Agent", USER_AGENT_LINUX);
        if (cookie != null) request.getHeaders().add(HttpHeaders.COOKIE, cookie);
        ClientHttpResponse response = execution.execute(request, body);
        var cookies = Objects.requireNonNull(response.getHeaders().get(HttpHeaders.SET_COOKIE));
        cookie = String.join(DELIMITER, getListCookies(cookies));
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
