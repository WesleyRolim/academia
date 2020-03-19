package com.example.academia.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class FichaDoAluno {

    private String aluno;
    private String objetivo;
    private String frequancia;
    private String descanco;
    // Verirficar a necessiade de colocar isso aqui, pois pode ser carregado na tela da Lista de Exercicios
    private String duracao;
    private String dataInicio;
    private String dataFim;
    private String peso;
    private String altura;

    public FichaDoAluno() {
    }

    public void salvar(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("ficha").child( getAluno() ).setValue( this );
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFrequancia() {
        return frequancia;
    }

    public void setFrequancia(String frequancia) {
        this.frequancia = frequancia;
    }

    public String getDescanco() {
        return descanco;
    }

    public void setDescanco(String descanco) {
        this.descanco = descanco;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getAluno() {
        return aluno;
    }

    @Exclude
    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }
}
