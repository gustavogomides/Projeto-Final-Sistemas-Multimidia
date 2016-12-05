package com.gustavosg.tbcdrones;

/**
 * Projeto: TBCDrones
 * Criado por Gustavo em 19/10/2016.
 */
public class Resposta {
    private int q1;
    private int q2;
    private int q3;
    private int q4;
    private int q5;
    private double mediafinal;
    private Usuario usuario;

    public Resposta(int q1, int q2, int q3, int q4, int q5, Usuario usuario, double mediafinal) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.mediafinal = mediafinal;
        this.usuario = usuario;
    }

    public Resposta() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public int getQ4() {
        return q4;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public int getQ5() {
        return q5;
    }

    public void setQ5(int q5) {
        this.q5 = q5;
    }

    public double getMediafinal() {
        return mediafinal;
    }

    public void setMediafinal(double mediafinal) {
        this.mediafinal = mediafinal;
    }
}
