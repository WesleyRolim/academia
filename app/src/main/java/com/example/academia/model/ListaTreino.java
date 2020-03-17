package com.example.academia.model;

public class ListaTreino {

    private String exercicio;
    private String sequencia;
    private String repeticao;

    public ListaTreino(String exercicio, String sequencia, String repeticao) {
        this.exercicio = exercicio;
        this.sequencia = sequencia;
        this.repeticao = repeticao;
    }

    public String getExercicio() {
        return exercicio;
    }

    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public String getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(String repeticao) {
        this.repeticao = repeticao;
    }

}
