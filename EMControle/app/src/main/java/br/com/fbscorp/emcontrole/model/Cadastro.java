package br.com.fbscorp.emcontrole.model;

import java.io.Serializable;

public class Cadastro implements Serializable{
    private long id;
    private String nome;
    private String email;
    private int medicamento;
    private String dia;
    private String mes;
    private String ano;
    private String hora;
    private String minuto;
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

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAno() {
        return ano;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getHora() {
        return hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
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
