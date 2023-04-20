package com.example.bingoexpress;

public class TJogador {
    String CPF, Status;
    int Recursos = 0, Saldo = 0, Score = 0, freq = 0;
    String idCliente;
    TCartelaPlayer Cartela = new TCartelaPlayer();

    public TJogador() {
    }
    public String getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public int getRecursos() {
        return Recursos;
    }
    public void setRecursos(int recursos) {
        Recursos = recursos;
    }
    public int getSaldo() {
        return Saldo;
    }
    public void setSaldo(int saldo) {
        Saldo = saldo;
    }
    public int getScore() {
        return Score;
    }
    public void setScore(int score) {
        Score = score;
    }
    public int getFreq() {
        return freq;
    }
    public void setFreq(int freq) {
        this.freq = freq;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
}
