package com.gustavosg.tbcdrones;

public class Usuario {

    private String nomecompleto;
    private String usuario;
    private String senha;
    private String funcao;

    public Usuario() {
    }

    public Usuario(String usuario, String senha, String nomecompleto, String funcao) {
        this.nomecompleto = nomecompleto;
        this.usuario = usuario;
        this.senha = senha;
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

}
