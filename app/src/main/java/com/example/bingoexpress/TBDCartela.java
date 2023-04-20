package com.example.bingoexpress;

public class TBDCartela {

    TLinha b = new TLinha();
    TLinha i = new TLinha();
    TLinha n = new TLinha();
    TLinha g = new TLinha();
    TLinha o = new TLinha();

    int p50Status = 0, p100Status = 0, p250Status = 0, p500Status = 0;

    String status = null;

    public TBDCartela() {
    }

    public int getP50Status() {
        return p50Status;
    }
    public void setP50Status(int p50Status) {
        this.p50Status = p50Status;
    }
    public int getP100Status() {
        return p100Status;
    }
    public void setP100Status(int p100Status) {
        this.p100Status = p100Status;
    }
    public int getP250Status() {
        return p250Status;
    }
    public void setP250Status(int p250Status) {
        this.p250Status = p250Status;
    }
    public int getP500Status() {
        return p500Status;
    }
    public void setP500Status(int p500Status) {
        this.p500Status = p500Status;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public TLinha getB() {
        return b;
    }
    public void setB(TLinha b) {
        this.b = b;
    }
    public TLinha getI() {
        return i;
    }
    public void setI(TLinha i) {
        this.i = i;
    }
    public TLinha getN() {
        return n;
    }
    public void setN(TLinha n) {
        this.n = n;
    }
    public TLinha getG() {
        return g;
    }
    public void setG(TLinha g) {
        this.g = g;
    }
    public TLinha getO() {
        return o;
    }
    public void setO(TLinha o) {
        this.o = o;
    }


}
