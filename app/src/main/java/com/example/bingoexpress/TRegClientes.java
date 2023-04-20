package com.example.bingoexpress;

public class TRegClientes {
    int Contingente = 0;      // Total de Clientes
    int SemSaldo = 0;         // Total de Clientes sem saldo
    int SemCartela = 0;       // Total de Clientes sem cartela
    int Ativos = 0;           // Total de Clientes Ativos
    int TotalCartelas = 0;    // Total de Cartelas


    public TRegClientes() {
    }
    public int getContingente() {
        return Contingente;
    }
    public void setContingente(int contingente) {
        Contingente = contingente;
    }
    public int getSemSaldo() {
        return SemSaldo;
    }
    public void setSemSaldo(int semSaldo) {
        SemSaldo = semSaldo;
    }
    public int getSemCartela() {
        return SemCartela;
    }
    public void setSemCartela(int semCartela) {
        SemCartela = semCartela;
    }
    public int getAtivos() {
        return Ativos;
    }
    public void setAtivos(int ativos) {
        Ativos = ativos;
    }
    public int getTotalCartelas() {
        return TotalCartelas;
    }
    public void setTotalCartelas(int totalCartelas) {
        TotalCartelas = totalCartelas;
    }

}
