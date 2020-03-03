package com.example.academia.helper;

import android.util.Base64;

public class Codification {

    public static String codificacaoData(String infomation ){
         return Base64.encodeToString(infomation.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodificationData (String dataCodificated){
        return new String(Base64.decode(dataCodificated, Base64.DEFAULT));
    }
}
