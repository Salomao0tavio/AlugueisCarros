package com.example.alugueiscarros.models;


import java.util.List;

public class UsuarioModel {
    private String idUsuario;
    private String nome;
    private String rg;
    private String cpf;
    private String endereco;
    private String profissao;
    //private List<Rendimento> rendimentos;


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

//    public List<Rendimento> getRendimentos() {
//        return rendimentos;
//    }
//
//    public void setRendimentos(List<Rendimento> rendimentos) {
//        this.rendimentos = rendimentos;
//    }
}