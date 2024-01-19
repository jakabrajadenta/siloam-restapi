package com.siloam.restapi.controller;

import antlr.StringUtils;
import com.siloam.restapi.dto.EmployeeDto;
import com.siloam.restapi.dto.EmployeeRequestDto;
import com.siloam.restapi.service.EmployeeService;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.net.ConnectException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/employee-service")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/testing-string")
    public Object testingString(){
        Object ada = "ada";
        Object kosong = null;

        var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,10000)
                .responseTimeout(Duration.ofMillis(10000))
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                );
        var webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();

        var responseEntity = webClient.post()
                .uri("http://localhost:3000/jalin/v1.0/cepatSecure/paymentAuthentication")
                .accept(MediaType.ALL).contentType(MediaType.APPLICATION_JSON)
//                .header(X_CLIENT_KEY,routeData.getClientKey())
//                .header(X_TIMESTAMP,timestamp)
//                .header(X_SIGNATURE,transactionSignature)
//                .header(AUTHORIZATION, authenticationToken)
                .bodyValue(EmployeeDto.builder().build())
                .exchangeToMono(clientResponse -> {
//                    if (clientResponse.statusCode().is5xxServerError()) {
//                        return Mono.just(this.handleWebClientException((TransactionRequestDto) requestDto, ResponseCodeMap.InputCode.EXTERNAL_ERROR.name()));
//                    }
                    return clientResponse.toEntity(Object.class);
                })
//                .onErrorReturn(e -> e.getCause() instanceof TimeoutException, this.handleWebClientException((TransactionRequestDto) requestDto, ResponseCodeMap.InputCode.SUSPEND.name()))
//                .onErrorReturn(e -> e.getCause() instanceof ConnectException, this.handleWebClientException((TransactionRequestDto) requestDto, ResponseCodeMap.InputCode.TIMEOUT.name()))
                .block();

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        System.out.println((String) ada);
        System.out.println((String) kosong);
//        return new ResponseEntity<>(responseEntity.getBody(),responseEntity.getStatusCode());
        return responseEntity;
    }

    @GetMapping("/filter")
    public Page<EmployeeDto> findAllFilter(EmployeeRequestDto requestDto, Pageable pageable){
        System.out.println("=============================================================");
        Long start = System.currentTimeMillis();
        System.out.println("started at "+LocalDateTime.now());
        Page<EmployeeDto> response = employeeService.findAllFilter(requestDto, pageable);
        Long end = System.currentTimeMillis();
        System.out.println("ended at "+LocalDateTime.now());
        System.out.println("Time: " + (end-start) + "ms");
        return response;
    }

    @GetMapping("/batch")
    public List<EmployeeDto> findAllBatc(){
        System.out.println("=============================================================");
        Long start = System.currentTimeMillis();
        System.out.println("started at "+LocalDateTime.now());
        List<EmployeeDto> response = employeeService.findAllBatch();
        Long end = System.currentTimeMillis();
        System.out.println("ended at "+LocalDateTime.now());
        System.out.println("Time: " + (end-start) + "ms");
        return response;
    }

    @GetMapping
    public List<EmployeeDto> findAll(){
        System.out.println("=============================================================");
        Long start = System.currentTimeMillis();
        System.out.println("started at "+LocalDateTime.now());
        List<EmployeeDto> response = employeeService.findAll();
        Long end = System.currentTimeMillis();
        System.out.println("ended at "+LocalDateTime.now());
        System.out.println("Time: " + (end-start) + "ms");
        soutPhoneNumber();
        return response;
    }

    public void soutPhoneNumber(){
        String ZERO = "0";
        String bin = "7788";
        String[] vaNumber = {"7788000812345678","7788008123456789","7788081234567890","7788812345678901"};
        System.out.println("==========RAW==========");
        for (String va : vaNumber) {
            System.out.println(va);
        }
        System.out.println("==========001==========");
        for (String s : vaNumber) {
            String phoneNumber = s.substring(bin.length());
            Long noZeroPhoneNumber = Long.valueOf(phoneNumber);
            System.out.println(ZERO.concat(String.valueOf(noZeroPhoneNumber)));
        }
        System.out.println("==========002==========");
        for (String s : vaNumber) {
            String phoneNumber = StringUtils.stripFront(s.substring(bin.length()),ZERO);
            System.out.println(ZERO.concat(String.valueOf(phoneNumber)));
        }
        System.out.println("==========003==========");
    }
}
