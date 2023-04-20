package com.example.bingoexpress;

public class TCartelaPlayer {

    int bolasAcertadas = 0, idCartela = 0;
    String status = null, idCliente = null;

    public TCartelaPlayer() {
    }
    public int getBolasAcertadas() {
        return bolasAcertadas;
    }
    public void setBolasAcertadas(int bolasAcertadas) {
        this.bolasAcertadas = bolasAcertadas;
    }
    public int getIdCartela() {
        return idCartela;
    }
    public void setIdCartela(int idCartela) {
        this.idCartela = idCartela;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

}
