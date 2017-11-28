package br.com.fbscorp.emcontrole.model;

import java.io.Serializable;

public class Cadastro implements Serializable{
    private long id;
    private String nome;
    private String email;
    private int medicamento;
    private String data;
    private String hora;
    private String lembrete;
    private int idLocal;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(int medicamento) {
        this.medicamento = medicamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String isLembrete() {
        return lembrete;
    }

    public void setLembrete(String lembrete) {
        this.lembrete = lembrete;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }
}
