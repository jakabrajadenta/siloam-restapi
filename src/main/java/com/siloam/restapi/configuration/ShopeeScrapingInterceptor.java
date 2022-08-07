package com.siloam.restapi.configuration;

import org.codehaus.commons.nullanalysis.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Objects;

public class ShopeeScrapingInterceptor implements ClientHttpRequestInterceptor {

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
    private static final String DELIMITER = "";
    private String cookie;

    @Override
    public @NotNull ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("User-Agent", USER_AGENT);
        if (cookie != null) request.getHeaders().add(HttpHeaders.COOKIE, cookie);
        ClientHttpResponse response = execution.execute(request, body);
        cookie = String.join(DELIMITER, Objects.requireNonNull(response.getHeaders().get(HttpHeaders.SET_COOKIE)));
        return response;
    }
}
