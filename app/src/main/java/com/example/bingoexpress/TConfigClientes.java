package com.example.bingoexpress;

public class TConfigClientes {
    int Ativos = 0, Contingente = 0, SemCartela = 0, SemSaldo = 0, TotalCartelas = 0;

    public TConfigClientes() {
    }
    public int getAtivos() {
        return Ativos;
    }
    public void setAtivos(int ativos) {
        Ativos = ativos;
    }
    public int getContingente() {
        return Contingente;
    }
    public void setContingente(int contingente) {
        Contingente = contingente;
    }
    public int getSemCartela() {
        return SemCartela;
    }
    public void setSemCartela(int semCartela) {
        this.SemCartela = semCartela;
    }
    public int getSemSaldo() {
        return SemSaldo;
    }
    public void setSemSaldo(int semSaldo) {
        this.SemSaldo = semSaldo;
    }
    public int getTotalCartelas() {
        return TotalCartelas;
    }
    public void setTotalCartelas(int totalCartelas) {
        this.TotalCartelas = totalCartelas;
    }

}
