package com.example.academia.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class Execicios {

    private String grupoExercicio;
    private String exercicio;


    public Execicios(){

    }

    public void salvar(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("exercicio").child( getGrupoExercicio() ).child(getExercicio()).setValue( this );
    }
    @Exclude
    public String getGrupoExercicio() {
        return grupoExercicio;
    }

    public void setGrupoExercicio(String grupoExercicio) {
        this.grupoExercicio = grupoExercicio;
    }

    public String getExercicio() {
        return exercicio;
    }

    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }
}
