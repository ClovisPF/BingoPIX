package com.example.bingoexpress;

import android.content.DialogInterface;
import android.content.Intent;
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

public class EditorCartela extends AppCompatActivity {

    public DatabaseReference BD_Raiz;
    public FirebaseUser ClienteLogado;
    public TCartela cartela = new TCartela();
    public TTabuleiro tabuleiro = new TTabuleiro();
    public TCliente RegCliente = new TCliente();
    public TBDCartela RegCartela = new TBDCartela();
    public TSorteios RegSorteios = new TSorteios();
    public TTabuleiro Tabuleiro = new TTabuleiro();
    public int vlCartelaAtual, SaldoCliente;
    public boolean ClienteExiste = false;

    int CartelaAtual = 0, SaldoAtual = 0, quorum50P = 0,
        BolaSorteada = 0, countSorteioBola = 0, totalCartelas = 0;

    String idSorteio = null, idSorteio_Title = null, appkC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_cartela);

        final TextView txtClienteLogado = findViewById(R.id.txtClienteLogado);
        final TextView txtSaldoDisponivel = findViewById(R.id.txtSaldoDisponivel);
        final TextView txtCartelas = findViewById(R.id.txtCartelas);

         final TextView txtIn50P = findViewById(R.id.txtIn50P);
        final TextView txtIn100P = findViewById(R.id.txtIn100P);
        final TextView txtIn250P = findViewById(R.id.txtIn250P);
        final TextView txtIn500P = findViewById(R.id.txtIn500P);

        final TextView txtSorteios = findViewById(R.id.txtSorteios);
        final TextView txtTituloCartela = findViewById(R.id.txtTituloCartela);

         final TextView txtIdKey50P = findViewById(R.id.txtIdKey50P);
        final TextView txtIdKey100P = findViewById(R.id.txtIdKey100P);
        final TextView txtIdKey250P = findViewById(R.id.txtIdKey250P);
        final TextView txtIdKey500P = findViewById(R.id.txtIdKey500P);

         final TextView txt50Particip = findViewById(R.id.txt50Particip);
        final TextView txt100Particip = findViewById(R.id.txt100Particip);
        final TextView txt250Particip = findViewById(R.id.txt250Particip);
        final TextView txt500Particip = findViewById(R.id.txt500Particip);

        final Button btnIdCartela = findViewById(R.id.btnIdCartela);
        final Button rbtn50P = findViewById(R.id.rbtn50P);
        final Button rbtn100P = findViewById(R.id.rbtn100P);
        final Button rbtn250P = findViewById(R.id.rbtn250P);
        final Button rbtn500P = findViewById(R.id.rbtn500P);

        BD_Raiz = FirebaseDatabase.getInstance().getReference();
        ClienteLogado = FirebaseAuth.getInstance().getCurrentUser();

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s9);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mp.start();

        DatabaseReference BD_ClienteLogado = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Registros");
        BD_ClienteLogado.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {  // ----------   Identificação do Cliente --------------
                RegCliente = dataSnapshot.getValue(TCliente.class);
                txtClienteLogado.setText(RegCliente.getNome());
                txtSaldoDisponivel.setText("Saldo Disponível: R$ " + RegCliente.getSaldo()+ ",00");
                txtCartelas.setText("Cartelas: " + RegCliente.getCartelas());
                txtIn50P.setText(String.valueOf(RegCliente.in50P));
                txtIn100P.setText(String.valueOf(RegCliente.in100P));
                txtIn250P.setText(String.valueOf(RegCliente.in250P));
                txtIn500P.setText(String.valueOf(RegCliente.in500P));
                txtSorteios.setText(String.valueOf(RegCliente.sorteios));

                SaldoAtual = RegCliente.saldo;

                // Mostrar bolas
                if (RegCliente.getCartelas() > 0){  // Possue Cartela(s)
                    CartelaAtual = 1;
                    MostrarBolas(1);
                    btnIdCartela.setText("1");
                    txtTituloCartela.setText("Cartela Nº 1");
                    rbtn50P.setEnabled(true);
                    rbtn100P.setEnabled(true);
                    rbtn250P.setEnabled(true);
                    rbtn500P.setEnabled(true);

                } else {
                // adicionar Dialogbox
                    AlertDialog.Builder NovoCartela = new AlertDialog.Builder(EditorCartela.this);
                    NovoCartela.setTitle("Crie suas Cartelas...");
                    NovoCartela.setMessage(RegCliente.getNome() + ", você ainda não possue Cartelas. Para Criar uma ou mais Cartelas de forma automática e aleatória, basta pressionar o ícone NOVA CARTELA.");
                    NovoCartela.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                  NovoCartela.show();
                  rbtn50P.setEnabled(false);
                  rbtn100P.setEnabled(false);
                  rbtn250P.setEnabled(false);
                  rbtn500P.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

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

                quorum50P = Integer.parseInt(txt50Particip.getText().toString());

                if (quorum50P == 10){
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.novo_cliente);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                    mp.start();

                   // ExecutarSorteio("50P", Integer.parseInt(txtIdKey50P.getText().toString()));

                } else {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
      tabuleiro.Ressetar();
    }
    public void CriarCartela(){

        Button btnValidarCartela = findViewById(R.id.btnSavarCartela);
        btnValidarCartela.setEnabled(true);

        // ------------  Criação da Cartela no App --------------------------------------------------------------------------------
        cartela.Ressetar(); cartela.AutoCriar();
        cartela.criarColuna(0); cartela.criarColuna(1); cartela.criarColuna(2); cartela.criarColuna(3); cartela.criarColuna(4);
        //-------------------------------------------------------------------------------------------------------------------------

        // ------------  Assignação das Variáveis do App ----------------------------------------------------------------------------
        Button b1 = findViewById(R.id.b1); Button b2 = findViewById(R.id.b2); Button b3 = findViewById(R.id.b3);Button b4 = findViewById(R.id.b4); Button b5 = findViewById(R.id.b5);
        Button i1 = findViewById(R.id.i1); Button i2 = findViewById(R.id.i2); Button i3 = findViewById(R.id.i3);Button i4 = findViewById(R.id.i4); Button i5 = findViewById(R.id.i5);
        Button n1 = findViewById(R.id.n1); Button n2 = findViewById(R.id.n2); Button n3 = findViewById(R.id.n3);Button n4 = findViewById(R.id.n4); Button n5 = findViewById(R.id.n5);
        Button g1 = findViewById(R.id.g1); Button g2 = findViewById(R.id.g2); Button g3 = findViewById(R.id.g3);Button g4 = findViewById(R.id.g4); Button g5 = findViewById(R.id.g5);
        Button o1 = findViewById(R.id.o1); Button o2 = findViewById(R.id.o2); Button o3 = findViewById(R.id.o3);Button o4 = findViewById(R.id.o4); Button o5 = findViewById(R.id.o5);
        //----------------------------------------------------------------------------------------------------------------------------

        // ------------------------------------   Exibição das Bolas da Cartela -----------------------------------------------
        // Família B

        b1.setText(cartela.Exibir(0,0));
        b2.setText(cartela.Exibir(0,1));
        b3.setText(cartela.Exibir(0,2));
        b4.setText(cartela.Exibir(0,3));
        b5.setText(cartela.Exibir(0,4));

        // Família I

        i1.setText(cartela.Exibir(1,0));
        i2.setText(cartela.Exibir(1,1));
        i3.setText(cartela.Exibir(1,2));
        i4.setText(cartela.Exibir(1,3));
        i5.setText(cartela.Exibir(1,4));

        // Família N

        n1.setText(cartela.Exibir(2,0));
        n2.setText(cartela.Exibir(2,1));
        n3.setText(cartela.Exibir(2,2));
        n4.setText(cartela.Exibir(2,3));
        n5.setText(cartela.Exibir(2,4));

        // Família G

        g1.setText(cartela.Exibir(3,0));
        g2.setText(cartela.Exibir(3,1));
        g3.setText(cartela.Exibir(3,2));
        g4.setText(cartela.Exibir(3,3));
        g5.setText(cartela.Exibir(3,4));

        // Família O

        o1.setText(cartela.Exibir(4,0));
        o2.setText(cartela.Exibir(4,1));
        o3.setText(cartela.Exibir(4,2));
        o4.setText(cartela.Exibir(4,3));
        o5.setText(cartela.Exibir(4,4));

        //------------------------------------------------------------------------------------------------


    }
    public void CriarCartelaOnClick(View view){

        final Button btnIdCartela = findViewById(R.id.btnIdCartela);

        AlertDialog.Builder DlgCriarCartela = new AlertDialog.Builder(EditorCartela.this);
        DlgCriarCartela.setTitle("Criando Cartela...");
        DlgCriarCartela.setMessage("A criação automática e aleatória da Cartela " + (RegCliente.getCartelas() + 1) +
                " será executada. Você poderá Salva-la pressionando SALVAR CARTELA ou se preferir sortear outra Cartela, pressione o ícone NOVA CARTELA.");

        DlgCriarCartela.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CriarCartela(); // função interna de App para Criar Cartela apenas no visor.
                btnIdCartela.setText(String.valueOf(RegCliente.getCartelas() + 1));
                //btnSaldoCartela.setText("0");
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s1);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();
                Toast.makeText(getApplicationContext(),"pode SALVAR esta Cartela ou Gerar outra.",Toast.LENGTH_LONG).show();
                //btnUsarCartela.setEnabled(false);
            }

        });
        DlgCriarCartela.setNegativeButton("Agora não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(),"Ok, tudo bem " + RegCliente.getNome() + ". Você Pode fazer isso em outro momento.",Toast.LENGTH_LONG).show();
            }
        });
        DlgCriarCartela.show();
    }
    public void MostrarBolas(int idCartela){

        //----------------------  Cartela Id e Saldo  -----------------------------------


        final Button rbtn50P = findViewById(R.id.rbtn50P);
        final Button rbtn100P = findViewById(R.id.rbtn100P);
        final Button rbtn250P = findViewById(R.id.rbtn250P);
        final Button rbtn500P = findViewById(R.id.rbtn500P);

        final TextView txtTituloCartela = findViewById(R.id.txtTituloCartela);

        CartelaAtual = idCartela;
        txtTituloCartela.setText("Cartela Nº " + CartelaAtual);

        DatabaseReference sldCartela = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas").child(String.valueOf(idCartela));
        sldCartela.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RegCartela = dataSnapshot.getValue(TBDCartela.class);

                    if (RegCartela.p50Status > 0){
                        rbtn50P.setVisibility(View.INVISIBLE);
                    } else {
                        rbtn50P.setVisibility(View.VISIBLE);
                    }

                if (RegCartela.p100Status > 0){
                    rbtn100P.setVisibility(View.INVISIBLE);
                } else {
                    rbtn100P.setVisibility(View.VISIBLE);
                }

                if (RegCartela.p250Status > 0){
                    rbtn250P.setVisibility(View.INVISIBLE);
                } else {
                    rbtn250P.setVisibility(View.VISIBLE);
                }

                if (RegCartela.p500Status > 0){
                    rbtn500P.setVisibility(View.INVISIBLE);
                } else {
                    rbtn500P.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //Toast.makeText(getApplicationContext(),"Exibindo a Cartela Nº: "+ CartelaAtual,Toast.LENGTH_SHORT).show();

//--------------------- FAMILIA B -------------------------------------------------------------------------------------------------

        final Button b1 = findViewById(R.id.b1);
        DatabaseReference BD_B1 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("b").child("linha1");
        BD_B1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b1.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button b2 = findViewById(R.id.b2);
        DatabaseReference BD_B2 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("b").child("linha2");
        BD_B2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b2.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button b3 = findViewById(R.id.b3);
        DatabaseReference BD_B3 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("b").child("linha3");
        BD_B3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b3.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button b4 = findViewById(R.id.b4);
        DatabaseReference BD_B4 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("b").child("linha4");
        BD_B4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b4.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button b5 = findViewById(R.id.b5);
        DatabaseReference BD_B5 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("b").child("linha5");
        BD_B5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                b5.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

//--------------------- FAMILIA I -------------------------------------------------------------------------------------------------

        final Button i1 = findViewById(R.id.i1);
        DatabaseReference BD_I1 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("i").child("linha1");
        BD_I1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i1.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button i2 = findViewById(R.id.i2);
        DatabaseReference BD_I2 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("i").child("linha2");
        BD_I2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i2.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button i3 = findViewById(R.id.i3);
        DatabaseReference BD_I3 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("i").child("linha3");
        BD_I3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i3.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button i4 = findViewById(R.id.i4);
        DatabaseReference BD_I4 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("i").child("linha4");
        BD_I4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i4.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button i5 = findViewById(R.id.i5);
        DatabaseReference BD_I5 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("i").child("linha5");
        BD_I5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i5.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

//--------------------- FAMILIA N -------------------------------------------------------------------------------------------------

        final Button n1 = findViewById(R.id.n1);
        DatabaseReference BD_N1 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("n").child("linha1");
        BD_N1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n1.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button n2 = findViewById(R.id.n2);
        DatabaseReference BD_N2 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                    .child(String.valueOf(idCartela)).child("n").child("linha2");
        BD_N2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n2.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button n3 = findViewById(R.id.n3);
        DatabaseReference BD_N3 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("n").child("linha3");
        BD_N3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n3.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button n4 = findViewById(R.id.n4);
        DatabaseReference BD_N4 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("n").child("linha4");
        BD_N4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n4.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button n5 = findViewById(R.id.n5);
        DatabaseReference BD_N5 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("n").child("linha5");
        BD_N5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n5.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

//--------------------- FAMILIA G -------------------------------------------------------------------------------------------------

        final Button g1 = findViewById(R.id.g1);
        DatabaseReference BD_G1 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("g").child("linha1");
        BD_G1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                g1.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button g2 = findViewById(R.id.g2);
        DatabaseReference BD_G2 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("g").child("linha2");
        BD_G2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                g2.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button g3 = findViewById(R.id.g3);
        DatabaseReference BD_G3 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("g").child("linha3");
        BD_G3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                g3.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button g4 = findViewById(R.id.g4);
        DatabaseReference BD_G4 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("g").child("linha4");
        BD_G4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                g4.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button g5 = findViewById(R.id.g5);
        DatabaseReference BD_G5 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("g").child("linha5");
        BD_G5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                g5.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

//--------------------- FAMILIA O -------------------------------------------------------------------------------------------------

        final Button o1 = findViewById(R.id.o1);
        DatabaseReference BD_O1 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("o").child("linha1");
        BD_O1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o1.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button o2 = findViewById(R.id.o2);
        DatabaseReference BD_O2 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("o").child("linha2");
        BD_O2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o2.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button o3 = findViewById(R.id.o3);
        DatabaseReference BD_O3 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("o").child("linha3");
        BD_O3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o3.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button o4 = findViewById(R.id.o4);
        DatabaseReference BD_O4 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("o").child("linha4");
        BD_O4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o4.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Button o5 = findViewById(R.id.o5);
        DatabaseReference BD_O5 = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas")
                .child(String.valueOf(idCartela)).child("o").child("linha5");
        BD_O5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o5.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

//---------------------------------------------------------------------------------------------

    }
    public void Anterior(View view){
        final Button btnIdCartela = findViewById(R.id.btnIdCartela);
        final Button btnSalvarCartela = findViewById(R.id.btnSavarCartela);
        btnSalvarCartela.setEnabled(false);
         if (CartelaAtual > 1){
            CartelaAtual--;
             btnIdCartela.setText(String.valueOf(CartelaAtual));
             MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s4);
             mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                 @Override
                 public void onCompletion(MediaPlayer mp) {
                     mp.release();
                 }
             });
             mp.start();
             MostrarBolas(CartelaAtual);
          } else {
             MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s5);
             mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                 @Override
                 public void onCompletion(MediaPlayer mp) {
                     mp.release();
                 }
             });
             mp.start();
            Toast.makeText(getApplicationContext(),"Não há Cartela pra mostrar",Toast.LENGTH_SHORT).show();
         }
    }
    public void Proximo(View view){
        final Button btnIdCartela = findViewById(R.id.btnIdCartela);
        final Button btnSalvarCartela = findViewById(R.id.btnSavarCartela);
        btnSalvarCartela.setEnabled(false);
         if (CartelaAtual < RegCliente.getCartelas()){
           CartelaAtual++;
           btnIdCartela.setText(String.valueOf(CartelaAtual));
             MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s4);
             mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                 @Override
                 public void onCompletion(MediaPlayer mp) {
                     mp.release();
                 }
             });
             mp.start();
           MostrarBolas(CartelaAtual);
        } else {
             MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s5);
             mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                 @Override
                 public void onCompletion(MediaPlayer mp) {
                     mp.release();
                 }
             });
             mp.start();
           Toast.makeText(getApplicationContext(),"Não há Cartela pra mostrar",Toast.LENGTH_SHORT).show();
        }
    }
    public void Finalizar(View view){
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s10);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mp.start();
        finish();
    }

    public void UpDateCartela(String idUser, String idCartela){

        // -------------------   Gravando no BD ------------------------------

      DatabaseReference  RegBDCartela = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Cartelas").child(idCartela);

         RegCartela.b.linha1 = cartela.colunaB[0];
         RegCartela.b.linha2 = cartela.colunaB[1];
         RegCartela.b.linha3 = cartela.colunaB[2];
         RegCartela.b.linha4 = cartela.colunaB[3];
         RegCartela.b.linha5 = cartela.colunaB[4];

        RegCartela.i.linha1 = cartela.colunaI[0];
        RegCartela.i.linha2 = cartela.colunaI[1];
        RegCartela.i.linha3 = cartela.colunaI[2];
        RegCartela.i.linha4 = cartela.colunaI[3];
        RegCartela.i.linha5 = cartela.colunaI[4];

        RegCartela.n.linha1 = cartela.colunaN[0];
        RegCartela.n.linha2 = cartela.colunaN[1];
        RegCartela.n.linha3 = cartela.colunaN[2];
        RegCartela.n.linha4 = cartela.colunaN[3];
        RegCartela.n.linha5 = cartela.colunaN[4];

        RegCartela.g.linha1 = cartela.colunaG[0];
        RegCartela.g.linha2 = cartela.colunaG[1];
        RegCartela.g.linha3 = cartela.colunaG[2];
        RegCartela.g.linha4 = cartela.colunaG[3];
        RegCartela.g.linha5 = cartela.colunaG[4];

        RegCartela.o.linha1 = cartela.colunaO[0];
        RegCartela.o.linha2 = cartela.colunaO[1];
        RegCartela.o.linha3 = cartela.colunaO[2];
        RegCartela.o.linha4 = cartela.colunaO[3];
        RegCartela.o.linha5 = cartela.colunaO[4];
         RegCartela.p50Status = 0;
        RegCartela.p100Status = 0;
        RegCartela.p250Status = 0;
        RegCartela.p500Status = 0;

      RegBDCartela.setValue(RegCartela);

    }
    public void UpDateStatusSorteioCartela(String idUser, int idCartela, String IdSorteio){
       // Toast.makeText(getApplicationContext(),"Cliente: " + idUser + " Cartela: " + idCartela + "Sorteio: " + IdSorteio + "Nº: " + SorteioAtual,Toast.LENGTH_LONG).show();
        if (IdSorteio.equals("50P")){
            BD_Raiz.child("Clientes").child(idUser).child("Cartelas").child(String.valueOf(idCartela)).child("p50Status").setValue(1);
        } else if (IdSorteio.equals("100P")){
            BD_Raiz.child("Clientes").child(idUser).child("Cartelas").child(String.valueOf(idCartela)).child("p100Status").setValue(1);
        } else if (IdSorteio.equals("250P")){
            BD_Raiz.child("Clientes").child(idUser).child("Cartelas").child(String.valueOf(idCartela)).child("p250Status").setValue(1);
        } else {
            BD_Raiz.child("Clientes").child(idUser).child("Cartelas").child(String.valueOf(idCartela)).child("p500Status").setValue(1);
        }

    }
    public void UpDateSaldoCliente(String idUser, int debito){
        SaldoAtual = SaldoAtual - debito;
        BD_Raiz.child("Clientes").child(idUser).child("Registros").child("saldo").setValue(SaldoAtual);
    }
    public void UpDateRegCliente(String idUser){
        DatabaseReference BD_ClienteLogado = BD_Raiz.child("Clientes").child(idUser).child("Registros");
        RegCliente.cartelas = CartelaAtual;
        BD_ClienteLogado.setValue(RegCliente);
    }
    public void UpDateSorteios(String idUser){
        final TextView txtSorteios = findViewById(R.id.txtSorteios);
        int cont = Integer.parseInt(txtSorteios.getText().toString()) + 1;
        BD_Raiz.child("Clientes").child(idUser).child("Registros").child("sorteios").setValue(cont);

    }
    public void UpDateParticipantes(String idSorteio, int cont){
        cont = cont + 1;
        BD_Raiz.child("Sorteios").child("Config").child(idSorteio).setValue(cont);
    }

    public void SalvarCartela(View view){

        final Button btnSalvarCartela = findViewById(R.id.btnSavarCartela);

        CartelaAtual = RegCliente.getCartelas() + 1;

        AlertDialog.Builder DlgSalvarCartela = new AlertDialog.Builder(EditorCartela.this);
        DlgSalvarCartela.setTitle("Salvando Nova Cartela...");
        DlgSalvarCartela.setMessage(RegCliente.getNome()+", você confirma salvar a Cartela Nº " + CartelaAtual + " na sua base de dados ?");

        DlgSalvarCartela.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                 UpDateCartela(ClienteLogado.getUid(), String.valueOf(CartelaAtual));// Coloca no Firebase a cartela do Cliente Logado
                // Atualizar Config
                 UpDateRegCliente(ClienteLogado.getUid());
                 MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s8);
                 mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                 mp.start();
                 Toast.makeText(getApplicationContext(),"A Cartela Nº " + CartelaAtual + " foi salvada com Sucesso.",Toast.LENGTH_SHORT).show();
                 MostrarBolas(CartelaAtual);
                 btnSalvarCartela.setEnabled(false);
            }
        });
        DlgSalvarCartela.setNegativeButton("Agora não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnSalvarCartela.setEnabled(false);
            }
        });
        DlgSalvarCartela.show();
    }
    public void GerenciadorSorteios(View view){
        //finish();
        Intent ik = new Intent(getApplicationContext(), SorteiosActivity.class);
        startActivity(ik);
    }
    public void UpDateIn50P(String idUser){
        final TextView txtIn50P = findViewById(R.id.txtIn50P);
        int cont = Integer.parseInt(txtIn50P.getText().toString()) + 1;
        BD_Raiz.child("Clientes").child(idUser).child("Registros").child("in50P").setValue(cont); // Auto Incrementar
    }
    public void add50P(String idKey, int idCatelaPlayer, String idCliente, int idCartela){

        if (idCatelaPlayer < 10) {

            BD_Raiz.child("Sorteios").child("50P").child(idKey).child("Config").child("participantes").setValue(idCatelaPlayer);
            DatabaseReference BD_Player50 = BD_Raiz.child("Sorteios").child("50P").child(idKey).child("Cartelas").child(String.valueOf(idCatelaPlayer));
            TCartelaPlayer Player50 = new TCartelaPlayer();
            Player50.idCliente = idCliente;
            Player50.idCartela = idCartela;
            Player50.bolasAcertadas = 0;
            Player50.status = "JOGANDO";
            BD_Player50.setValue(Player50);

            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.s3);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mp.start();
            //Toast.makeText(getApplicationContext(),"Cartela Nº " + idCatelaPlayer,Toast.LENGTH_LONG).show();
        } else {

            if (idCatelaPlayer > 10){
               // Toast.makeText(getApplicationContext(),"Sorteio em Andamento.",Toast.LENGTH_LONG).show();
            } else {
                BD_Raiz.child("Sorteios").child("50P").child(idKey).child("Config").child("participantes").setValue(idCatelaPlayer);
                DatabaseReference BD_Player50 = BD_Raiz.child("Sorteios").child("50P").child(idKey).child("Cartelas").child(String.valueOf(idCatelaPlayer));
                TCartelaPlayer Player50 = new TCartelaPlayer();
                Player50.idCliente = idCliente;
                Player50.idCartela = idCartela;
                Player50.bolasAcertadas = 0;
                Player50.status = "JOGANDO";
                BD_Player50.setValue(Player50);
            }
        }
    }
    public void btn50OnClick(View view) {
        final TextView txtIdKey50P = findViewById(R.id.txtIdKey50P);
        final TextView txt50Particip = findViewById(R.id.txt50Particip);
        final Button btnIdCartela = findViewById(R.id.btnIdCartela);
        final Button rbtn50P = findViewById(R.id.rbtn50P);

       // if ()
        if (quorum50P < 10) {

            android.app.AlertDialog.Builder msgSorteio50 = new android.app.AlertDialog.Builder(EditorCartela.this);
            msgSorteio50.setTitle("Sorteio com 50 Participantes...");
            msgSorteio50.setMessage("Olá " + RegCliente.getNome() + ", você CONFIRMA a sua participação no Sorteio Nº " + txtIdKey50P.getText().toString() + " com 50 Participantes " +
                    "usando sua Cartela Nº " + btnIdCartela.getText().toString() + " ? Serão descontados R$ 10,00 do seu saldo atual.");

            msgSorteio50.setPositiveButton("Sim, Quero Participar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Atualizar saldo
                    UpDateSaldoCliente(ClienteLogado.getUid(), 10);
                    // Atualizar Status da Cartela.Sorteio
                    UpDateStatusSorteioCartela(ClienteLogado.getUid(), CartelaAtual, "50P");
                    // Atualizar Contador Individual de Sorteio
                    UpDateIn50P(ClienteLogado.getUid());
                    // Atualizar Contador Geral de Sorteios
                    UpDateSorteios(ClienteLogado.getUid());
                    // Atualizar contador do Sorteio 50P
                    UpDateParticipantes("p50Participantes", Integer.parseInt(txt50Particip.getText().toString()));
                    // Inserir cartela no Sorteio 50P
                    add50P(txtIdKey50P.getText().toString(), Integer.parseInt(txt50Particip.getText().toString()) + 1, ClienteLogado.getUid(), Integer.parseInt(btnIdCartela.getText().toString()));


                }
            });

            msgSorteio50.setNegativeButton("Agora Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            //--------------------------------------------------------------------------------------------

            msgSorteio50.show();

        } else {
            Toast.makeText(getApplicationContext(),"ATENÇÃO. Sorteio em Andamento.",Toast.LENGTH_LONG).show();
        }
    }

    public void btnTabuleiroOnClick(View view){
        //finish();
        Intent ik = new Intent(getApplicationContext(), tabuleiroActivity.class);
        startActivity(ik);
    }

}

