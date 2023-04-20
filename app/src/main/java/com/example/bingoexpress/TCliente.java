package com.example.bingoexpress;

public class TCliente {
    String nome = null, email = null, cpf = null, senha = null, dataIn = null, status = null, cel = null;
    int score = 0, cartelas = 0, sorteios = 0, saldo = 0, in50P = 0, in100P = 0, in250P = 0, in500P = 0;

    public TCliente() {
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getDataIn() {
        return dataIn;
    }
    public void setDataIn(String dataIn) {
        this.dataIn = dataIn;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getCartelas() {
        return cartelas;
    }
    public void setCartelas(int cartelas) {
        this.cartelas = cartelas;
    }
    public int getSorteios() {
        return sorteios;
    }
    public void setSorteios(int sorteios) {
        this.sorteios = sorteios;
    }
    public int getSaldo() {
        return saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getIn50P() {
        return in50P;
    }
    public void setIn50P(int in50P) {
        this.in50P = in50P;
    }
    public int getIn100P() {
        return in100P;
    }
    public void setIn100P(int in100P) {
        this.in100P = in100P;
    }
    public int getIn250P() {
        return in250P;
    }
    public void setIn250P(int in250P) {
        this.in250P = in250P;
    }
    public int getIn500P() {
        return in500P;
    }
    public void setIn500P(int in500P) {
        this.in500P = in500P;
    }

    public String getCel() {
        return cel;
    }
    public void setCel(String cel) {
        this.cel = cel;
    }
}
