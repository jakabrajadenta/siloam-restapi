package com.siloam.restapi.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AopExamplesService {

    public static final String MESSAGE = "Hello World !!!";

    public String getMessage(String message){
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

}
