package br.com.fbscorp.emcontrole.model;

import java.io.Serializable;

public class Medicamento implements Serializable{

    private long id;
    private String nome;
    private int locais;
    private int frequencia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLocais() {
        return locais;
    }

    public void setLocais(int locais) {
        this.locais = locais;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return nome;
    }
}
