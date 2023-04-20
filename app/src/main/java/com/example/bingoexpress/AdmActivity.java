package com.example.bingoexpress;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdmActivity extends AppCompatActivity {

    public DatabaseReference BD_Raiz;
    public FirebaseUser ClienteLogado;
    public TCartelaPlayer RegCartelaPlayer = new TCartelaPlayer();
    public TSorteios RegSorteios = new TSorteios();
    public TLinha RegLinhas = new TLinha();
    public TTabuleiro Tabuleiro = new TTabuleiro();
    int BolaSorteada = 0, countSorteioBola = 0,
        quorum50P = 0, quorum100P = 0, quorum250P = 0, quorum500P = 0;

    String appkC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);

        BD_Raiz = FirebaseDatabase.getInstance().getReference();
        ClienteLogado = FirebaseAuth.getInstance().getCurrentUser();

        //CriarSorteio(1);
        Tabuleiro.Ressetar();

        final TextView txtContSorteios = findViewById(R.id.txtContSorteios); // Sorteios Realizados

        final TextView txtTituloSorteio = findViewById(R.id.txtTituloSorteio);
        final TextView txtParticipantesSorteio = findViewById(R.id.txtParticipantesSorteio);
        final TextView txtArrecadado = findViewById(R.id.txtArrecado);
        final TextView txtValorPremio = findViewById(R.id.txtValorPremio);
        final TextView txtValorCartela = findViewById(R.id.txtValorCartela);
        final TextView txtTotalCartelas = findViewById(R.id.txtTotalCartelas);

        // - Monitoramento de Sorteios

             final TextView txt50Particip = findViewById(R.id.txt50Particip);
            final TextView txt100Particip = findViewById(R.id.txt100Particip);
            final TextView txt250Particip = findViewById(R.id.txt250Particip);
            final TextView txt500Particip = findViewById(R.id.txt500Particip);

             final TextView txtIdKey50P = findViewById(R.id.txtIdKey50P);
            final TextView txtIdKey100P = findViewById(R.id.txtIdKey100P);
            final TextView txtIdKey250P = findViewById(R.id.txtIdKey250P);
            final TextView txtIdKey500P = findViewById(R.id.txtIdKey500P);

            final TextView txtStatusSorteio50P = findViewById(R.id.txtStatusSorteio50P);

        final TextView txtIdSorteio = findViewById(R.id.txtIdSorteio);
        final TextView txtIdKey = findViewById(R.id.txtIdKey);

        final Button rbtn50P = findViewById(R.id.rbtn50P);

        DatabaseReference BD_Sorteios = BD_Raiz.child("Sorteios").child("Config");
        BD_Sorteios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RegSorteios = dataSnapshot.getValue(TSorteios.class);

                txtIdKey50P.setText(String.valueOf(RegSorteios.p50idKey));
                txtIdKey100P.setText(String.valueOf(RegSorteios.p100idKey));
                txtIdKey250P.setText(String.valueOf(RegSorteios.p250idKey));
                txtIdKey500P.setText(String.valueOf(RegSorteios.p500idKey));

                txt50Particip.setText(String.valueOf(RegSorteios.p50Participantes));
                txt100Particip.setText(String.valueOf(RegSorteios.p100Participantes));
                txt250Particip.setText(String.valueOf(RegSorteios.p250Participantes));
                txt500Particip.setText(String.valueOf(RegSorteios.p500Participantes));

                txtStatusSorteio50P.setText(RegSorteios.status50P);

                 quorum50P = Integer.parseInt(txt50Particip.getText().toString());
                quorum100P = Integer.parseInt(txt100Particip.getText().toString());
                quorum250P = Integer.parseInt(txt250Particip.getText().toString());
                quorum500P = Integer.parseInt(txt500Particip.getText().toString());

                if (quorum50P==10){  // 50 Participantes
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s9);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                    mp.start();
                    rbtn50P.setEnabled(true);
                    BD_Raiz.child("Sorteios").child("Config").child("status50P").setValue("EXECUTANDO");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //---------------------------------------------------------------------------------------
    }
    public void Finalizer(View view) {
        finish();
    }
    public String CriarCodCart(String idUser) {
        /*Date hoje = new Date();
        SimpleDateFormat dia = new SimpleDateFormat("dd");
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        String Dia = dia.format(hoje.getTime());
        String Mes = mes.format(hoje.getTime());
        String Ano = ano.format(hoje.getTime());*/

        return "";//idUser + "-" + Dia + "-" + Mes + "-" + Ano;

    }
    public void CrierSorterOnClick(final View view) {
        final TextView txtContSorteios = findViewById(R.id.txtContSorteios);
        AlertDialog.Builder NovoCliente = new AlertDialog.Builder(AdmActivity.this);
        NovoCliente.setTitle("Criar Sorteio...");
        NovoCliente.setMessage("O Administrador solicita criação de novo Sorteio.");

        NovoCliente.setPositiveButton("Criar Sorteio", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Criar Sorteio
                CrierSorter(String.valueOf(Integer.parseInt(txtContSorteios.getText().toString()) + 1));


            }
        });

        NovoCliente.setNegativeButton("Agora Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        NovoCliente.show();
    }
    public void CrierSorter(String idSorteio) {
        Toast.makeText(getApplicationContext(), "O Sorteio Número: " + idSorteio + " será criado.", Toast.LENGTH_LONG).show();

        // Cabeçalho Raiz de Sorteios - Atualização --------------------------------------------------------------------------



    }
    public void IniciarSorteio(int idSorteio){

    }

    public String findC(int bola) {
        String kC = null;
        if (bola > 0 && bola < 16) {
            return "b";
        }
        if (bola > 15 && bola < 31) {
            return "i";
        }
        if (bola > 30 && bola < 46) {
            return "n";
        }
        if (bola > 45 && bola < 61) {
            return "g";
        }
        if (bola > 60 && bola < 76) {
            return "o";
        }
        return kC;
    }
    public void SorterBolaOnClick(View view) {

        Button btnBola = findViewById(R.id.btnBola);
        Button btnVerificarBola = findViewById(R.id.btnVerificarBola);
        Button btnSortearBola = findViewById(R.id.btnSortearBola);
        TextView txtCountSorteio = findViewById(R.id.txtCountSorteio);
        TextView txtkC = findViewById(R.id.txtkC);
        final TextView txtIdSorteio = findViewById(R.id.txtIdSorteio);
        final TextView txtIdKey = findViewById(R.id.txtIdKey);
        final TextView txtIdKey50P = findViewById(R.id.txtIdKey50P);

        if (countSorteioBola < 75) {
            BolaSorteada = Tabuleiro.SortearBola();
            countSorteioBola++;
            txtCountSorteio.setText(String.valueOf(countSorteioBola));
            btnBola.setText(String.valueOf(BolaSorteada));
            txtkC.setText(findC(BolaSorteada));
            appkC = txtkC.getText().toString();
            // ---------------  Registrando Bola Sorteada no Tabuleiro do Sorteio Atual -----------------------

           BD_Raiz.child("Sorteios").child(txtIdSorteio.getText().toString()).child(txtIdKey.getText().toString()).child("Tabuleiro").child(String.valueOf(countSorteioBola)).setValue(BolaSorteada);
           BD_Raiz.child("Sorteios").child(txtIdSorteio.getText().toString()).child(txtIdKey.getText().toString()).child("Config").child("bolasSorteadas").setValue(countSorteioBola);

            btnVerificarBola.setEnabled(true);
            btnSortearBola.setEnabled(false);
            // ------------------------------------------------------------------------------------------------
            //Toast.makeText(getApplicationContext(), "Sorteio: " + String.valueOf(countSorteioBola) + "- Bola Sorteada: " + String.valueOf(BolaSorteada), Toast.LENGTH_LONG).show();
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s10);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mp.start();
        } else {
            Toast.makeText(getApplicationContext(), "Limite máximo de Sorteio.", Toast.LENGTH_LONG).show();
        }

    }

    public void VerifierBolaOnClick(View view) {

        final TextView txtIdSorteio = findViewById(R.id.txtIdSorteio);
        final TextView txtIdKey = findViewById(R.id.txtIdKey);
        final TextView txtkC = findViewById(R.id.txtkC);
        final Button btnBola = findViewById(R.id.btnBola);
        final Button btnVerificarBola = findViewById(R.id.btnVerificarBola);
        final Button btnSortearBola = findViewById(R.id.btnSortearBola);
        final TextView txtIdCartela = findViewById(R.id.txtIdCartela);
        final TextView txtIdCliente = findViewById(R.id.txtIdCliente);
        final TextView txtIncidencias = findViewById(R.id.txtIncidencias);
        final TextView txt50Particip = findViewById(R.id.txt50Particip);

        txtIncidencias.setText("0");
        /*
        AlertDialog.Builder iVerificarCartela = new AlertDialog.Builder(AdmActivity.this);
        iVerificarCartela.setTitle("Procurando Bola... ");
        iVerificarCartela.setMessage("O Aplicativo vai procurar a Bola: " + txtkC.getText() + btnBola.getText() + " em todas as " + txt50Particip.getText().toString() + " Cartelas Incritas neste Sorteio.");

        iVerificarCartela.setPositiveButton("Procurar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // colocar sistema de busca aqui ? ----------------------------------------



                //-------------------------------------------------------------------------
            }
        });
        iVerificarCartela.show();
        */
        // - verificando ---------------------------------------
        for (int ik = 1; ik <=Integer.parseInt(txt50Particip.getText().toString()); ik++) {
            DatabaseReference BD_CartelaPlayer = BD_Raiz.child("Sorteios").child(txtIdSorteio.getText().toString()).child(txtIdKey.getText().toString()).child("Cartelas").child(String.valueOf(ik));
            final int finalIk = ik;
            BD_CartelaPlayer.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) { //  --------- Coletando Informações da Cartala Player ----------------
                    RegCartelaPlayer = dataSnapshot.getValue(TCartelaPlayer.class);
                    txtIdCliente.setText(RegCartelaPlayer.idCliente);
                    txtIdCartela.setText(String.valueOf(RegCartelaPlayer.idCartela));
                    DatabaseReference BDCartela = BD_Raiz.child("Clientes").child(RegCartelaPlayer.idCliente).child("Cartelas").child(String.valueOf(RegCartelaPlayer.idCartela)).child(txtkC.getText().toString());
                    BDCartela.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // verificar aqui
                            RegLinhas = dataSnapshot.getValue(TLinha.class);
                                if (RegLinhas.linha1==BolaSorteada || RegLinhas.linha2==BolaSorteada || RegLinhas.linha3==BolaSorteada || RegLinhas.linha4==BolaSorteada || RegLinhas.linha5==BolaSorteada){
                                    txtIncidencias.setText(String.valueOf(Integer.parseInt(txtIncidencias.getText().toString()) + 1));
                                   UpDateBolaAcertada(txtIdSorteio.getText().toString(), finalIk);// -  Atualizar Contador de BolasAcertadas --
                                }
                            // ------------------------ fim verificação -----------------------------
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
        //---------------------------------------- Verificado
        btnVerificarBola.setEnabled(false);
        btnSortearBola.setEnabled(true);
        Toast.makeText(getApplicationContext(),"Bola Verificada com Sucesso",Toast.LENGTH_SHORT).show();
    }
    public void UpDateBolaAcertada(String idSorteio, int idCartela){
        final Button btnSortearBola = findViewById(R.id.btnSortearBola);
        final Button btnVerificarBola = findViewById(R.id.btnVerificarBola);
        final TextView txtCartelasArmadas = findViewById(R.id.txtCartelasArmadas);
        final TextView txtGanhadores = findViewById(R.id.txtGanhadores);
        final TextView txtStatusSorteio50P = findViewById(R.id.txtStatusSorteio50P);
        final TextView txtIdSorteio = findViewById(R.id.txtIdSorteio);
        final TextView txtIdKey = findViewById(R.id.txtIdKey);

        final DatabaseReference BD_CartelaPlayer = BD_Raiz.child("Sorteios").child(idSorteio).child(txtIdKey.getText().toString()).child("Cartelas").child(String.valueOf(idCartela));
        BD_CartelaPlayer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RegCartelaPlayer = dataSnapshot.getValue(TCartelaPlayer.class);
                RegCartelaPlayer.bolasAcertadas++;

                    if (RegCartelaPlayer.bolasAcertadas==25){
                        RegCartelaPlayer.status="BINGO";
                        btnSortearBola.setEnabled(false);
                        btnVerificarBola.setEnabled(false);
                        txtGanhadores.setText(String.valueOf(Integer.parseInt(txtGanhadores.getText().toString()) + 1));
                        txtStatusSorteio50P.setText("ENCERRADO");
                        // BD_Raiz.child("Sorteios").child(txtIdSorteio.getText().toString()).child("Config").child("status").setValue(txtStatusSorteio.getText().toString());
                         FinalizeSorter(txtIdSorteio.getText().toString(), Integer.parseInt(txtIdKey.getText().toString()));
                    }

                if (RegCartelaPlayer.bolasAcertadas==24){
                    // cartelas armadas
                    txtCartelasArmadas.setText(String.valueOf(Integer.parseInt(txtCartelasArmadas.getText().toString()) + 1));
                }

                BD_CartelaPlayer.setValue(RegCartelaPlayer);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void SetSorter50P(View view){
        final Button rbtn50P =  findViewById(R.id.rbtn50P);
        final Button btnSortearBola = findViewById(R.id.btnSortearBola);
        final TextView txtIdSorteio = findViewById(R.id.txtIdSorteio);
        final TextView txtIdKey = findViewById(R.id.txtIdKey);
        final TextView txtIdKey50P = findViewById(R.id.txtIdKey50P);
        //final TextView txtStatusSorteio50P =  findViewById(R.id.txtStatusSorteio50P);

        txtIdSorteio.setText("50P");
        txtIdKey.setText(txtIdKey50P.getText().toString());
        btnSortearBola.setEnabled(true);
       // BD_Raiz.child("Sorteios").child("Config").child("status50P").setValue("EXECUTANDO");
        rbtn50P.setEnabled(false);
    }
    public void FinalizeSorter(final String idSorteio, final int idKey){
        final TextView txtCountSorteio = findViewById(R.id.txtCountSorteio);
        final TextView txt50Particip = findViewById(R.id.txt50Particip);

        final TextView txtIdCliente  = findViewById(R.id.txtIdCliente);
        final TextView txtIdCartela  = findViewById(R.id.txtIdCartela);

        final Button rbtn50P = findViewById(R.id.rbtn50P);
        txtCountSorteio.setText("0");
        rbtn50P.setEnabled(false);

        // - Limpando ---------------------------------------
        for (int ik = 1; ik <=Integer.parseInt(txt50Particip.getText().toString()); ik++) {
            DatabaseReference BD_CartelaPlayer = BD_Raiz.child("Sorteios").child(idSorteio).child(String.valueOf(idKey)).child("Cartelas").child(String.valueOf(ik));

            BD_CartelaPlayer.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) { //  --------- Coletando Informações da Cartala Player ----------------
                    RegCartelaPlayer = dataSnapshot.getValue(TCartelaPlayer.class);
                    txtIdCliente.setText(RegCartelaPlayer.idCliente);
                    txtIdCartela.setText(String.valueOf(RegCartelaPlayer.idCartela));
                    LimperStatus50P(txtIdCliente.getText().toString(), Integer.parseInt(txtIdCartela.getText().toString()));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        //---------------------------------------- Limpado

        // Incrementação para Próximo Sorteio -------------------------------------------------

        BD_Raiz.child("Sorteios").child("Config").child("p50Participantes").setValue(0);
        BD_Raiz.child("Sorteios").child("Config").child("p50idKey").setValue(idKey + 1);
        BD_Raiz.child("Sorteios").child("Config").child("status50P").setValue("DISPONÍVEL");
        //-------------------------------------------------------------------------------------

        finish();

    }
    public void LimperStatus50P(String idCliente, int idCartela){
        BD_Raiz.child("Clientes").child(idCliente).child("Cartelas").child(String.valueOf(idCartela)).child("p50Status").setValue(0);
    }


}

    // levar pedaço de cano pro Tio Moy
