package com.example.academia.model;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlanoDeTreino {

    private String email;
    private String grupoMuscularea;
    private String exercicio;
    private String sequencia;
    private String repeticao;
    private String tipoTreino;
    private String numeroExercicio;

    public void PlanoDeTreino(){

    }

    public void salvar(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("treino").child( getEmail() ).child( getTipoTreino() ).child( getNumeroExercicio()).setValue( this );
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrupoMuscularea() {
        return grupoMuscularea;
    }

    public void setGrupoMuscularea(String grupoMuscularea) {
        this.grupoMuscularea = grupoMuscularea;
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


    public String getTipoTreino() {
        return tipoTreino;
    }

    public void setTipoTreino(String tipoTreino) {
        this.tipoTreino = tipoTreino;
    }

    public String getNumeroExercicio() {
        return numeroExercicio;
    }

    public void setNumeroExercicio(String numeroExercicio) {
        this.numeroExercicio = numeroExercicio;
    }
}
