package com.example.bingoexpress;


import java.util.Random;

   public class TCartela{

    int[] colunaB = new int[5];
    int[] colunaI = new int[5];
    int[] colunaN = new int[5];
    int[] colunaG = new int[5];
    int[] colunaO = new int[5];

    int[] famB = new int[15];
    int[] famI = new int[15];
    int[] famN = new int[15];
    int[] famG = new int[15];
    int[] famO = new int[15];

    int Saldo;

    String dspB1;

    public TCartela() {
        // constructor - obrigatório
    }
    public int getSaldo() {
        return Saldo;
    }
    public void setSaldo(int saldo) {
        Saldo = saldo;
    }
    public void Ressetar(){
        for (int k=0;k<15;k++){
            this.famB[k] = -1; this.famI[k] = -1; this.famN[k] = -1; this.famG[k] = -1; this.famO[k] = -1;
        }
    }
    public void AutoCriar(){      //criar cartela aleatoriamente

        int sorteadas = 0; // contador para Bolas sorteadas

            while (sorteadas<5){ // Sortear Família B
              int num = new Random().nextInt(15);
                if (this.famB[num] == -1){  // disponível
                    this.famB[num] = num + 1; // coloca na Família o Número Sorteado
                    sorteadas++;
                }
            }

        sorteadas = 0;

         while (sorteadas<5){ // Sortear Família I
          int num = new Random().nextInt(15);
            if (this.famI[num] == -1){  // disponível
                this.famI[num] = num + 16; // coloca na Família o Número Sorteado
                sorteadas++;
            }
        }

        sorteadas = 0;

        while (sorteadas<5){ // Sortear Família N
            int num = new Random().nextInt(15);
            if (this.famN[num] == -1){  // disponível
                this.famN[num] = num + 31; // coloca na Família o Número Sorteado
                sorteadas++;
            }
        }

        sorteadas = 0;

        while (sorteadas<5){ // Sortear Família G
            int num = new Random().nextInt(15);
            if (this.famG[num] == -1){  // disponível
                this.famG[num] = num + 46; // coloca na Família o Número Sorteado
                sorteadas++;
            }
        }

        sorteadas = 0;

        while (sorteadas<5){ // Sortear Família O
            int num = new Random().nextInt(15);
            if (this.famO[num] == -1){  // disponível
                this.famO[num] = num + 61; // coloca na Família o Número Sorteado
                sorteadas++;
            }
        }

     }
    public void criarColuna(int Col){ // Cria

    int i = 0;

    if (Col == 0){ // Família B
            for (int k=0;k<15;k++){
                if (this.famB[k] != -1) {
                    this.colunaB[i] = this.famB[k];
                    i++;
                }
            }
        }

    if (Col == 1){ // Família I
            for (int k=0;k<15;k++){
               if (this.famI[k] != -1){
                  this.colunaI[i] = this.famI[k];
                  i++;
               }
            }
        }

    if (Col == 2){ // Família N
           for (int k=0;k<15;k++){
              if (this.famN[k] != -1){
                this.colunaN[i] = this.famN[k];
                i++;
              }
           }
        }

    if (Col == 3){ // Família G
        for (int k=0;k<15;k++){
            if (this.famG[k] != -1){
                this.colunaG[i] = this.famG[k];
                i++;
            }
        }
    }

    if (Col == 4){ // Família O
        for (int k=0;k<15;k++){
            if (this.famO[k] != -1){
                this.colunaO[i] = this.famO[k];
                i++;
            }
        }
    }

}
    public String Exibir(int Col, int Lin){ // Monta e mostra Cartela de formato Aleatório

        if (Col==0){   // Família B
            if (Lin==0){
                dspB1 = String.valueOf(this.colunaB[0]);
            }

            if (Lin==1){
                dspB1 = String.valueOf(colunaB[1]);
            }

            if (Lin==2){
                dspB1 = String.valueOf(colunaB[2]);
            }

            if (Lin==3){
                dspB1 = String.valueOf(colunaB[3]);
            }

            if (Lin==4){
                dspB1 = String.valueOf(colunaB[4]);
            }
        }

        if (Col==1){   // Família I
            if (Lin==0){
                dspB1 = String.valueOf(this.colunaI[0]);
            }

            if (Lin==1){
                dspB1 = String.valueOf(colunaI[1]);
            }

            if (Lin==2){
                dspB1 = String.valueOf(colunaI[2]);
            }

            if (Lin==3){
                dspB1 = String.valueOf(colunaI[3]);
            }

            if (Lin==4){
                dspB1 = String.valueOf(colunaI[4]);
            }
        }


        if (Col==2){   // Família N
            if (Lin==0){
                dspB1 = String.valueOf(this.colunaN[0]);
            }

            if (Lin==1){
                dspB1 = String.valueOf(colunaN[1]);
            }

            if (Lin==2){
                dspB1 = String.valueOf(colunaN[2]);
            }

            if (Lin==3){
                dspB1 = String.valueOf(colunaN[3]);
            }

            if (Lin==4){
                dspB1 = String.valueOf(colunaN[4]);
            }
        }


        if (Col==3){   // Família G
            if (Lin==0){
                dspB1 = String.valueOf(this.colunaG[0]);
            }

            if (Lin==1){
                dspB1 = String.valueOf(colunaG[1]);
            }

            if (Lin==2){
                dspB1 = String.valueOf(colunaG[2]);
            }

            if (Lin==3){
                dspB1 = String.valueOf(colunaG[3]);
            }

            if (Lin==4){
                dspB1 = String.valueOf(colunaG[4]);
            }
        }

        if (Col==4){   // Família 0
            if (Lin==0){
                dspB1 = String.valueOf(this.colunaO[0]);
            }

            if (Lin==1){
                dspB1 = String.valueOf(colunaO[1]);
            }

            if (Lin==2){
                dspB1 = String.valueOf(colunaO[2]);
            }

            if (Lin==3){
                dspB1 = String.valueOf(colunaO[3]);
            }

            if (Lin==4){
                dspB1 = String.valueOf(colunaO[4]);
            }
        }
      return dspB1;
    }

}
