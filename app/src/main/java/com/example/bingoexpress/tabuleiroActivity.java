package com.example.bingoexpress;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tabuleiroActivity extends AppCompatActivity {

    public TConfigSorteios RegSorteios = new TConfigSorteios();
    public TConfigSorteio RegSorteio = new TConfigSorteio();
    public DatabaseReference BD_Raiz;
    public FirebaseUser ClienteLogado;
    public boolean Autorizado = false;
    public int BS = 0;
    public String BOLASORTEADA = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabuleiro);

        BD_Raiz = FirebaseDatabase.getInstance().getReference();
        ClienteLogado = FirebaseAuth.getInstance().getCurrentUser();
        HideTabuleiro();
    }

    public void Finalizar(View view){
        finish();
    }
    public void ShowTabuleiro(String idSorteio, int idKey){
       // DatabaseReference BDTabuleiro = BD_Raiz.child("Sorteios").child("")
    }
    public void ShowBola(String bs){
        final Button b1 = findViewById(R.id.b1);   final Button b2 = findViewById(R.id.b2);   final Button b3 = findViewById(R.id.b3);
        final Button b4 = findViewById(R.id.b4);   final Button b5 = findViewById(R.id.b5);   final Button b6 = findViewById(R.id.b6);
        final Button b7 = findViewById(R.id.b7);   final Button b8 = findViewById(R.id.b8);   final Button b9 = findViewById(R.id.b9);
        final Button b10 = findViewById(R.id.b10); final Button b11 = findViewById(R.id.b11); final Button b12 = findViewById(R.id.b12);
        final Button b13 = findViewById(R.id.b13); final Button b14 = findViewById(R.id.b14); final Button b15 = findViewById(R.id.b15);
        final Button b16 = findViewById(R.id.b16); final Button b17 = findViewById(R.id.b17); final Button b18 = findViewById(R.id.b18);
        final Button b19 = findViewById(R.id.b19); final Button b20 = findViewById(R.id.b20); final Button b21 = findViewById(R.id.b21);
        final Button b22 = findViewById(R.id.b22); final Button b23 = findViewById(R.id.b23); final Button b24 = findViewById(R.id.b24);
        final Button b25 = findViewById(R.id.b25); final Button b26 = findViewById(R.id.b26); final Button b27 = findViewById(R.id.b27);
        final Button b28 = findViewById(R.id.b28); final Button b29 = findViewById(R.id.b29); final Button b30 = findViewById(R.id.b30);
        final Button b31 = findViewById(R.id.b31); final Button b32 = findViewById(R.id.b32); final Button b33 = findViewById(R.id.b33);
        final Button b34 = findViewById(R.id.b34); final Button b35 = findViewById(R.id.b35); final Button b36 = findViewById(R.id.b36);
        final Button b37 = findViewById(R.id.b37); final Button b38 = findViewById(R.id.b38); final Button b39 = findViewById(R.id.b39);
        final Button b40 = findViewById(R.id.b40); final Button b41 = findViewById(R.id.b41); final Button b42 = findViewById(R.id.b42);
        final Button b43 = findViewById(R.id.b43); final Button b44 = findViewById(R.id.b44); final Button b45 = findViewById(R.id.b45);
        final Button b46 = findViewById(R.id.b46); final Button b47 = findViewById(R.id.b47); final Button b48 = findViewById(R.id.b48);
        final Button b49 = findViewById(R.id.b49); final Button b50 = findViewById(R.id.b50); final Button b51 = findViewById(R.id.b51);
        final Button b52 = findViewById(R.id.b52); final Button b53 = findViewById(R.id.b53); final Button b54 = findViewById(R.id.b54);
        final Button b55 = findViewById(R.id.b55); final Button b56 = findViewById(R.id.b56); final Button b57 = findViewById(R.id.b57);
        final Button b58 = findViewById(R.id.b58); final Button b59 = findViewById(R.id.b59); final Button b60 = findViewById(R.id.b60);
        final Button b61 = findViewById(R.id.b61); final Button b62 = findViewById(R.id.b62); final Button b63 = findViewById(R.id.b63);
        final Button b64 = findViewById(R.id.b64); final Button b65 = findViewById(R.id.b65); final Button b66 = findViewById(R.id.b66);
        final Button b67 = findViewById(R.id.b67); final Button b68 = findViewById(R.id.b68); final Button b69 = findViewById(R.id.b69);
        final Button b70 = findViewById(R.id.b70); final Button b71 = findViewById(R.id.b71); final Button b72 = findViewById(R.id.b72);
        final Button b73 = findViewById(R.id.b73); final Button b74 = findViewById(R.id.b74); final Button b75 = findViewById(R.id.b75);

        if (bs.equals("b1")){
            b1.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b2")){
            b2.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b3")){
            b3.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b4")){
            b4.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b5")){
            b5.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b6")){
            b6.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b7")){
            b7.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b8")){
            b8.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b9")){
            b9.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b10")){
            b10.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b11")){
            b11.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b12")){
            b12.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b13")){
            b13.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b14")){
            b14.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b15")){
            b15.setVisibility(View.VISIBLE);
        }

        // -----

        if (bs.equals("b16")){
            b16.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b17")){
            b17.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b18")){
            b18.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b19")){
            b19.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b20")){
            b20.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b21")){
            b21.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b22")){
            b22.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b23")){
            b23.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b24")){
            b24.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b25")){
            b25.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b26")){
            b26.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b27")){
            b27.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b28")){
            b28.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b29")){
            b29.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b30")){
            b30.setVisibility(View.VISIBLE);
        }

        // -----

        if (bs.equals("b31")){
            b31.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b32")){
            b32.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b33")){
            b33.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b34")){
            b34.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b35")){
            b35.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b36")){
            b36.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b37")){
            b37.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b38")){
            b38.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b39")){
            b39.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b40")){
            b40.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b41")){
            b41.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b42")){
            b42.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b43")){
            b43.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b44")){
            b44.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b45")){
            b45.setVisibility(View.VISIBLE);
        }

        // -----

        if (bs.equals("b46")){
            b46.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b47")){
            b47.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b48")){
            b48.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b49")){
            b49.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b50")){
            b50.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b51")){
            b51.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b52")){
            b52.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b53")){
            b53.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b54")){
            b54.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b55")){
            b55.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b56")){
            b56.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b57")){
            b57.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b58")){
            b58.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b59")){
            b59.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b60")){
            b60.setVisibility(View.VISIBLE);
        }

        // -----

        if (bs.equals("b61")){
            b61.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b62")){
            b62.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b63")){
            b63.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b64")){
            b64.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b65")){
            b65.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b66")){
            b66.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b67")){
            b67.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b68")){
            b68.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b69")){
            b69.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b70")){
            b70.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b71")){
            b71.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b72")){
            b72.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b73")){
            b73.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b74")){
            b74.setVisibility(View.VISIBLE);
        }
        if (bs.equals("b75")){
            b75.setVisibility(View.VISIBLE);
        }


    }
    public void HideTabuleiro(){
         final Button b1 = findViewById(R.id.b1);   final Button b2 = findViewById(R.id.b2);   final Button b3 = findViewById(R.id.b3);
         final Button b4 = findViewById(R.id.b4);   final Button b5 = findViewById(R.id.b5);   final Button b6 = findViewById(R.id.b6);
         final Button b7 = findViewById(R.id.b7);   final Button b8 = findViewById(R.id.b8);   final Button b9 = findViewById(R.id.b9);
        final Button b10 = findViewById(R.id.b10); final Button b11 = findViewById(R.id.b11); final Button b12 = findViewById(R.id.b12);
        final Button b13 = findViewById(R.id.b13); final Button b14 = findViewById(R.id.b14); final Button b15 = findViewById(R.id.b15);
        final Button b16 = findViewById(R.id.b16); final Button b17 = findViewById(R.id.b17); final Button b18 = findViewById(R.id.b18);
        final Button b19 = findViewById(R.id.b19); final Button b20 = findViewById(R.id.b20); final Button b21 = findViewById(R.id.b21);
        final Button b22 = findViewById(R.id.b22); final Button b23 = findViewById(R.id.b23); final Button b24 = findViewById(R.id.b24);
        final Button b25 = findViewById(R.id.b25); final Button b26 = findViewById(R.id.b26); final Button b27 = findViewById(R.id.b27);
        final Button b28 = findViewById(R.id.b28); final Button b29 = findViewById(R.id.b29); final Button b30 = findViewById(R.id.b30);
        final Button b31 = findViewById(R.id.b31); final Button b32 = findViewById(R.id.b32); final Button b33 = findViewById(R.id.b33);
        final Button b34 = findViewById(R.id.b34); final Button b35 = findViewById(R.id.b35); final Button b36 = findViewById(R.id.b36);
        final Button b37 = findViewById(R.id.b37); final Button b38 = findViewById(R.id.b38); final Button b39 = findViewById(R.id.b39);
        final Button b40 = findViewById(R.id.b40); final Button b41 = findViewById(R.id.b41); final Button b42 = findViewById(R.id.b42);
        final Button b43 = findViewById(R.id.b43); final Button b44 = findViewById(R.id.b44); final Button b45 = findViewById(R.id.b45);
        final Button b46 = findViewById(R.id.b46); final Button b47 = findViewById(R.id.b47); final Button b48 = findViewById(R.id.b48);
        final Button b49 = findViewById(R.id.b49); final Button b50 = findViewById(R.id.b50); final Button b51 = findViewById(R.id.b51);
        final Button b52 = findViewById(R.id.b52); final Button b53 = findViewById(R.id.b53); final Button b54 = findViewById(R.id.b54);
        final Button b55 = findViewById(R.id.b55); final Button b56 = findViewById(R.id.b56); final Button b57 = findViewById(R.id.b57);
        final Button b58 = findViewById(R.id.b58); final Button b59 = findViewById(R.id.b59); final Button b60 = findViewById(R.id.b60);
        final Button b61 = findViewById(R.id.b61); final Button b62 = findViewById(R.id.b62); final Button b63 = findViewById(R.id.b63);
        final Button b64 = findViewById(R.id.b64); final Button b65 = findViewById(R.id.b65); final Button b66 = findViewById(R.id.b66);
        final Button b67 = findViewById(R.id.b67); final Button b68 = findViewById(R.id.b68); final Button b69 = findViewById(R.id.b69);
        final Button b70 = findViewById(R.id.b70); final Button b71 = findViewById(R.id.b71); final Button b72 = findViewById(R.id.b72);
        final Button b73 = findViewById(R.id.b73); final Button b74 = findViewById(R.id.b74); final Button b75 = findViewById(R.id.b75);

        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);
        b5.setVisibility(View.INVISIBLE);
        b6.setVisibility(View.INVISIBLE);
        b7.setVisibility(View.INVISIBLE);
        b8.setVisibility(View.INVISIBLE);
        b9.setVisibility(View.INVISIBLE);
        b10.setVisibility(View.INVISIBLE);
        b11.setVisibility(View.INVISIBLE);
        b12.setVisibility(View.INVISIBLE);
        b13.setVisibility(View.INVISIBLE);
        b14.setVisibility(View.INVISIBLE);
        b15.setVisibility(View.INVISIBLE);

        b16.setVisibility(View.INVISIBLE);
        b17.setVisibility(View.INVISIBLE);
        b18.setVisibility(View.INVISIBLE);
        b19.setVisibility(View.INVISIBLE);
        b20.setVisibility(View.INVISIBLE);
        b21.setVisibility(View.INVISIBLE);
        b22.setVisibility(View.INVISIBLE);
        b23.setVisibility(View.INVISIBLE);
        b24.setVisibility(View.INVISIBLE);
        b25.setVisibility(View.INVISIBLE);
        b26.setVisibility(View.INVISIBLE);
        b27.setVisibility(View.INVISIBLE);
        b28.setVisibility(View.INVISIBLE);
        b29.setVisibility(View.INVISIBLE);
        b30.setVisibility(View.INVISIBLE);

        b31.setVisibility(View.INVISIBLE);
        b32.setVisibility(View.INVISIBLE);
        b33.setVisibility(View.INVISIBLE);
        b34.setVisibility(View.INVISIBLE);
        b35.setVisibility(View.INVISIBLE);
        b36.setVisibility(View.INVISIBLE);
        b37.setVisibility(View.INVISIBLE);
        b38.setVisibility(View.INVISIBLE);
        b39.setVisibility(View.INVISIBLE);
        b40.setVisibility(View.INVISIBLE);
        b41.setVisibility(View.INVISIBLE);
        b42.setVisibility(View.INVISIBLE);
        b43.setVisibility(View.INVISIBLE);
        b44.setVisibility(View.INVISIBLE);
        b45.setVisibility(View.INVISIBLE);


        b46.setVisibility(View.INVISIBLE);
        b47.setVisibility(View.INVISIBLE);
        b48.setVisibility(View.INVISIBLE);
        b49.setVisibility(View.INVISIBLE);
        b50.setVisibility(View.INVISIBLE);
        b51.setVisibility(View.INVISIBLE);
        b52.setVisibility(View.INVISIBLE);
        b53.setVisibility(View.INVISIBLE);
        b54.setVisibility(View.INVISIBLE);
        b55.setVisibility(View.INVISIBLE);
        b56.setVisibility(View.INVISIBLE);
        b57.setVisibility(View.INVISIBLE);
        b58.setVisibility(View.INVISIBLE);
        b59.setVisibility(View.INVISIBLE);
        b60.setVisibility(View.INVISIBLE);

        b61.setVisibility(View.INVISIBLE);
        b62.setVisibility(View.INVISIBLE);
        b63.setVisibility(View.INVISIBLE);
        b64.setVisibility(View.INVISIBLE);
        b65.setVisibility(View.INVISIBLE);
        b66.setVisibility(View.INVISIBLE);
        b67.setVisibility(View.INVISIBLE);
        b68.setVisibility(View.INVISIBLE);
        b69.setVisibility(View.INVISIBLE);
        b70.setVisibility(View.INVISIBLE);
        b71.setVisibility(View.INVISIBLE);
        b72.setVisibility(View.INVISIBLE);
        b73.setVisibility(View.INVISIBLE);
        b74.setVisibility(View.INVISIBLE);
        b75.setVisibility(View.INVISIBLE);
    }
    public int getSorteioIdKey(String idSorteio){
        int idK = 1;
            if (idSorteio.equals("50P")){
                // ler idKey em Sorteios/50P/Config
                DatabaseReference idk = BD_Raiz.child("Sorteios").child("50P").child("Config").child("idKey");
                Autorizado = true;
                Toast.makeText(getApplicationContext(),"Exibindo Sorteio/Resultado",Toast.LENGTH_SHORT).show();

            }
        return idK;
    }
    public void ShowTabuleiro50P(View view){
        //ShowTabuleiro("50P", getSorteioIdKey("50P"));

        int bs = 0;
        final TextView txtIdKey50P = findViewById(R.id.txtIdKey50P);
        final TextView txtBS = findViewById(R.id.txtBS);
        final Button btnBolaSorteada = findViewById(R.id.btnBolaSorteada);
        HideTabuleiro();

        DatabaseReference BD_Sorteios = BD_Raiz.child("Sorteios").child("Config");
        BD_Sorteios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RegSorteios = dataSnapshot.getValue(TConfigSorteios.class);
                txtIdKey50P.setText(String.valueOf(RegSorteios.p50idKey));

                DatabaseReference BD_Sorteio = BD_Raiz.child("Sorteios").child("50P").child(txtIdKey50P.getText().toString()).child("Config");
                BD_Sorteio.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        RegSorteio = dataSnapshot.getValue(TConfigSorteio.class);
                        txtBS.setText(String.valueOf(RegSorteio.bolasSorteadas));
                        //Toast.makeText(getApplicationContext(),"Sorteio Nº " + RegSorteios.p50idKey + " Bolas Sorteadas: " + RegSorteio.bolasSorteadas,Toast.LENGTH_SHORT).show();

                            for (int k=1;k<= RegSorteio.bolasSorteadas;k++){
                                DatabaseReference BD_Bola = BD_Raiz.child("Sorteios").child("50P").child(txtIdKey50P.getText().toString()).child("Tabuleiro").child(String.valueOf(k));
                                        BD_Bola.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                btnBolaSorteada.setText(dataSnapshot.getValue().toString());
                                                BOLASORTEADA = "b" + btnBolaSorteada.getText().toString();
                                                //Toast.makeText(getApplicationContext(),"Bola Sorteada: " + BOLASORTEADA,Toast.LENGTH_SHORT).show();
                                                ShowBola(BOLASORTEADA);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                            }
                                        });
                            }


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });





    }


}










