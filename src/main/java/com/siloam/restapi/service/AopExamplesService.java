package com.siloam.restapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AopExamplesService {

    @Autowired
    @Qualifier("ServiceRestTemplate")
    private RestTemplate restTemplate;

    public static final String MESSAGE = "Hello World !!!";
    public static final String USERNAME = "PTADA";
    public static final String PASSWORD = "astrapay123";
    private static final String LOGIN_URL = "https://filetransfer.permatabank.com/template/login";
    private static final String START_URL = "/~";
    private static final String SWITCH_PAYLOAD = "Login In";

    public String getMessage(String message) throws IOException {





        var webPage = Jsoup
                .connect("https://filetransfer.permatabank.com/template/login")
                .get();
        System.out.println(webPage.body().getAllElements());

        // get login form
        var loginForm = Jsoup
                .connect("https://filetransfer.permatabank.com/template/login")
                .method(Connection.Method.GET)
                .execute();

        // POST login data
        var loginResponse = Jsoup
                .connect("https://filetransfer.permatabank.com/template/login")
                .data("user", USERNAME)
                .data("password", PASSWORD)
                .cookies(loginForm.cookies())
                .timeout(100000)
                .method(Connection.Method.POST)
                .execute();

        // GET page
        var document = Jsoup.connect("https://filetransfer.permatabank.com")
//                .method(Connection.Method.GET)
                .cookies(loginResponse.cookies())
                .timeout(100000)
                .get();
        System.out.println(document.body().getAllElements());

        System.out.println(MESSAGE);
        System.out.println("====================================================================");
        var sentence = message;
        System.out.println(sentence);
        var i = new AtomicInteger(BigDecimal.ZERO.intValue());
        var firstCharacter = (int) 'a' - BigDecimal.ONE.intValue();
        var lastCharacter = (int) 'z' + BigDecimal.ZERO.intValue();
        List<String> characters = sentence.toLowerCase().chars().mapToObj(c -> {
            if (c<=firstCharacter || c>lastCharacter) return String.valueOf((char) c);
            i.getAndIncrement();
            var ascii = (char) c + i.get();
            ascii = this.encryptArithmetic(ascii,firstCharacter,lastCharacter);
            return String.valueOf( (char) ascii );
        }).collect(Collectors.toList());
        var result = String.join("",characters);
        System.out.println(result);
        System.out.println("");

        // decrypt sentence
        var j = new AtomicInteger(BigDecimal.ZERO.negate().intValue());
        List<String> decryptCharacter = result.chars().mapToObj(c -> {
            if (c<=firstCharacter || c>lastCharacter) return String.valueOf((char) c);
            j.getAndDecrement();
            var ascii = (char) c + j.get();
            ascii = this.decryptArithmetic(ascii,firstCharacter,lastCharacter);
            return String.valueOf( (char) ascii );
        }).collect(Collectors.toList());
        var decrypted = String.join("",decryptCharacter);
        System.out.println(decrypted);
        System.out.println("");
        System.out.println("====================================================================");

        return result;
    }

    public int encryptArithmetic(int ascii, int firstCharacter, int lastCharacter){
        var isAsciiGreater = ascii > lastCharacter;
        if (isAsciiGreater) ascii = firstCharacter + ascii - lastCharacter;
        return isAsciiGreater?encryptArithmetic(
                ascii,firstCharacter,lastCharacter):ascii;
    }

    public int decryptArithmetic(int ascii, int firstCharacter, int lastCharacter){
        var isAsciiLower = ascii <= firstCharacter;
        if (isAsciiLower) ascii = ascii + lastCharacter - firstCharacter + BigDecimal.ONE.intValue();
        return isAsciiLower?decryptArithmetic(
                ascii-BigDecimal.ONE.intValue(),firstCharacter,lastCharacter):ascii;
    }


    public String decrypt(String message){
        var i = new AtomicInteger(BigDecimal.ZERO.intValue());
        var firstCharacter = (int) 'a' - BigDecimal.ONE.intValue();
        var lastCharacter = (int) 'z' + BigDecimal.ZERO.intValue();


        // decrypt sentence
        var j = new AtomicInteger(BigDecimal.ZERO.negate().intValue());
        List<String> decryptCharacter = message.chars().mapToObj(c -> {
            if (c<=firstCharacter || c>lastCharacter) return String.valueOf((char) c);
            j.getAndDecrement();
            var ascii = (char) c + j.get();
            ascii = this.decryptArithmetic(ascii,firstCharacter,lastCharacter);
            return String.valueOf( (char) ascii );
        }).collect(Collectors.toList());
        var decrypted = String.join("",decryptCharacter);
        System.out.println(decrypted);
        System.out.println("");
        System.out.println("====================================================================");

        return decrypted;
    }
}
