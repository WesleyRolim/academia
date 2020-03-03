package com.example.academia.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    Context contexto;
    SharedPreferences preferencias;
    private final int MODE = 0;
    private final String NOME_ARQUIVO = "arquivoUsuarioLogado";
    private SharedPreferences.Editor editor;
    private final String CHAVE_IDENTIFICACAO = "identificadorUsuarioLogado";

    public Preferencias (Context contextoParametro){

        contexto = contextoParametro;
        preferencias = contexto.getSharedPreferences(NOME_ARQUIVO, MODE);
        editor = preferencias.edit();

    }

    public void salvarDados ( String identificacaoUsuario){

        editor.putString(CHAVE_IDENTIFICACAO, identificacaoUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferencias.getString(CHAVE_IDENTIFICACAO, null);
    }
}
