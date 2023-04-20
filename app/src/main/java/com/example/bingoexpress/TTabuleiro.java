package com.example.bingoexpress;

import java.util.Random;

public class TTabuleiro {

    int[] BolasSorteadas = new int[75];
    int BS = 0, count = 0;

    public TTabuleiro() {

    }
    public void Ressetar(){
        for (int k=0;k<75;k++){
            this.BolasSorteadas[k] = 0;
        }
    }
    public int SortearBola(){
        int p = 1;
        while (p != 0){
             this.BS = new Random().nextInt(75); // Sorteia uma Bola

                if (this.BolasSorteadas[this.BS] == 0){
                    this.BolasSorteadas[this.BS] = this.BS + 1;
                    p = 0;
                }
        }
        this.count++;
        return this.BolasSorteadas[this.BS];
    }

}

