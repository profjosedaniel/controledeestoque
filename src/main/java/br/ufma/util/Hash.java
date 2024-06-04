package br.ufma.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Hash {
       public static String hash(String text){
        String sha256hex = Hashing.sha256()
        .hashString(text, StandardCharsets.UTF_8)
        .toString();
        return sha256hex;
    }

}
