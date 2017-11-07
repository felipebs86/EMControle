package br.com.fbscorp.emcontrole.model;

import java.security.Timestamp;
import java.util.Date;

public class Cadastro {
    private long id;
    private String nome;
    private String email;
    private int medicamento;
    private Date dataInicio;
    private Timestamp hora;
    private boolean lembrete;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public boolean isLembrete() {
        return lembrete;
    }

    public void setLembrete(boolean lembrete) {
        this.lembrete = lembrete;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }
}
