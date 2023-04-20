package com.example.bingoexpress;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class ClienteActivity extends AppCompatActivity {
    public DatabaseReference BD_Raiz;
    public FirebaseUser ClienteLogado;

    public TCliente RegCliente = new TCliente();

    public TConfigSorteios RegConfigSorteios = new TConfigSorteios();

    int kCicle = 1, SorteioAtual_ValorCartela = 0;
    int SorteioAtual_idKey = 1;

    String SorteioAtual_idSorteio = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        final TextView txtClienteLogado = findViewById(R.id.txtClienteLogado);
        final TextView txtSaldoDisponivel = findViewById(R.id.txtSaldoDisponivel);
        final TextView txtCartelas = findViewById(R.id.txtCartelas);
        //------------------------------------------------------------------------------------------
        final TextView txt50Pinscritos = findViewById(R.id.txt50Pinscritos);
        final TextView txt100Pinscritos = findViewById(R.id.txt100Pinscritos);
        final TextView txt250Pinscritos = findViewById(R.id.txt250Pinscritos);
        final TextView txt500Pinscritos = findViewById(R.id.txt500Pinscritos);
        //------------------------------------------------------------------------------------------
        final TextView txtP50idKey = findViewById(R.id.txtP50idKey);
        final TextView txtP100idKey = findViewById(R.id.txtP100idKey);
        final TextView txtP250idKey = findViewById(R.id.txtP250idKey);
        final TextView txtP500idKey = findViewById(R.id.txtP500idKey);
        // ----------------------------------------------------------------------------------------
        final TextView txtidKey = findViewById(R.id.txtidKey);
        //-----------------------------------------------------------------------------------------
        final Button btnParticipar= findViewById(R.id.btnParticipar);

        SorteioAtual_idSorteio = "50P";

        BD_Raiz = FirebaseDatabase.getInstance().getReference();
        ClienteLogado = FirebaseAuth.getInstance().getCurrentUser();

                    //   -----------------------    INFORMAÇÕES SOBRE O CLIENTE  ----------------------------

        DatabaseReference BD_ClienteLogado = BD_Raiz.child("Clientes").child(ClienteLogado.getUid()).child("Registros");
        BD_ClienteLogado.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RegCliente = dataSnapshot.getValue(TCliente.class);
                txtClienteLogado.setText(RegCliente.getNome());
                txtSaldoDisponivel.setText("Saldo Disponível: " + RegCliente.getSaldo());
                txtCartelas.setText("Minhas Cartelas: " + RegCliente.getCartelas());

                if (RegCliente.getCartelas() == 0){
                    btnParticipar.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"Você ainda não possue Cartela.",Toast.LENGTH_LONG).show();
                } else {
                    btnParticipar.setEnabled(true);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        DatabaseReference Sorteios = BD_Raiz.child("Sorteios").child("Config"); // ------   Registros Geral de Sorteios  -----------
        Sorteios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RegConfigSorteios = dataSnapshot.getValue(TConfigSorteios.class);

                 txtP50idKey.setText(String.valueOf(RegConfigSorteios.p50idKey));
                txtP100idKey.setText(String.valueOf(RegConfigSorteios.p100idKey));
                txtP250idKey.setText(String.valueOf(RegConfigSorteios.p250idKey));
                txtP500idKey.setText(String.valueOf(RegConfigSorteios.p500idKey));

                txt50Pinscritos.setText(String.valueOf(RegConfigSorteios.p50Participantes));
                txt100Pinscritos.setText(String.valueOf(RegConfigSorteios.p100Participantes));
                txt250Pinscritos.setText(String.valueOf(RegConfigSorteios.p250Participantes));
                txt500Pinscritos.setText(String.valueOf(RegConfigSorteios.p500Participantes));

                txtidKey.setText(txtP50idKey.getText().toString());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

     MostrarSorteio(SorteioAtual_idSorteio, 1);

    }
    public void Finalizar(View view){
        //ClienteBD.signOut();
        finish();
    }
    public void ParticiparOnClick(View view){
        String idSorteio_Title;


        if (SorteioAtual_idSorteio.equals("50P")){
            idSorteio_Title = "50 Participantes";
        } else if (SorteioAtual_idSorteio.equals("100P")){
            idSorteio_Title = "100 Participantes";
        } else if (SorteioAtual_idSorteio.equals("250P")){
            idSorteio_Title = "250 Participantes";
        } else {
            idSorteio_Title = "500 Participantes";
        }

                AlertDialog.Builder Participar = new AlertDialog.Builder(ClienteActivity.this);
                Participar.setTitle("Participar do Sorteio...");
                Participar.setMessage(RegCliente.getNome() + ", você está solicitando participar do Sorteio de " + idSorteio_Title +
                        ". Para isso, você deve inscrever uma ou mais Cartelas.");

                Participar.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int idCall = 0;

                        if (kCicle==0){   // 500

                            //Toast.makeText(getApplicationContext(),"contador: " + kCicle + "Valor Cartela: " + SorteioAtual_ValorCartela,Toast.LENGTH_SHORT).show();
                            Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
                            ik.putExtra("idSorteio", SorteioAtual_idSorteio);
                            ik.putExtra("idKey", SorteioAtual_idKey);
                            ik.putExtra("vlCartela", SorteioAtual_ValorCartela);
                            startActivity(ik);

                        } else if (kCicle==1){  // 50

                            //Toast.makeText(getApplicationContext(),"contador: " + kCicle + "Valor Cartela: " + SorteioAtual_ValorCartela,Toast.LENGTH_SHORT).show();
                            Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
                            ik.putExtra("idSorteio", SorteioAtual_idSorteio);
                            ik.putExtra("idKey", SorteioAtual_idKey);
                            ik.putExtra("vlCartela", SorteioAtual_ValorCartela);
                            startActivity(ik);

                        } else if (kCicle==2){  // 100
                            //Toast.makeText(getApplicationContext(),"contador: " + kCicle + "Valor Cartela: " + SorteioAtual_ValorCartela,Toast.LENGTH_SHORT).show();
                            Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
                            ik.putExtra("idSorteio", SorteioAtual_idSorteio);
                            ik.putExtra("idKey", SorteioAtual_idKey);
                            ik.putExtra("vlCartela", SorteioAtual_ValorCartela);
                            startActivity(ik);

                        } else { // 250

                            //Toast.makeText(getApplicationContext(),"contador: " + kCicle + "Valor Cartela: " + SorteioAtual_ValorCartela,Toast.LENGTH_SHORT).show();
                            Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
                            ik.putExtra("idSorteio", SorteioAtual_idSorteio);
                            ik.putExtra("idKey", SorteioAtual_idKey);
                            ik.putExtra("vlCartela", SorteioAtual_ValorCartela);
                            startActivity(ik);

                        }
                    }
                });

                Participar.setNegativeButton("Agora não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                //--------------------------------------------------------------------------------------------

                Participar.show();

    }
    public void MostrarSorteio(String idSorteio, int idKey){
        final TextView txtTitulo = findViewById(R.id.txtTitulo);
        final TextView txtValorCartela = findViewById(R.id.txtValorCartela);
        final TextView txtValorPremio = findViewById(R.id.txtValorPremio);

        DatabaseReference SorteioAtual = BD_Raiz.child("Sorteios").child(idSorteio).child(String.valueOf(idKey)).child("Config");
        SorteioAtual.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TConfigSorteio RegSorteioAtual = dataSnapshot.getValue(TConfigSorteio.class);
                txtTitulo.setText(RegSorteioAtual.getTitulo());
                txtValorPremio.setText("R$ "+ RegSorteioAtual.valorPremio + ",00");
                txtValorCartela.setText("R$ " + RegSorteioAtual.valorCartela + ",00");
                SorteioAtual_ValorCartela = RegSorteioAtual.valorCartela;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

       /*

        */



    }
    public void Proximo(View view){

        final TextView txtP50idKey = findViewById(R.id.txtP50idKey);
        final TextView txtP100idKey = findViewById(R.id.txtP100idKey);
        final TextView txtP250idKey = findViewById(R.id.txtP250idKey);
        final TextView txtP500idKey = findViewById(R.id.txtP500idKey);

        kCicle++;

        if (kCicle==1){
            MostrarSorteio("50P", Integer.parseInt(txtP50idKey.getText().toString()));
            SorteioAtual_idSorteio = "50P";
            SorteioAtual_idKey = Integer.parseInt(txtP50idKey.getText().toString());
        }

        if (kCicle==2){
            MostrarSorteio("100P", Integer.parseInt(txtP100idKey.getText().toString()));
            SorteioAtual_idSorteio = "100P";
            SorteioAtual_idKey = Integer.parseInt(txtP100idKey.getText().toString());
        }

        if (kCicle==3){
            MostrarSorteio("250P", Integer.parseInt(txtP250idKey.getText().toString()));
            SorteioAtual_idSorteio = "250P";
            SorteioAtual_idKey = Integer.parseInt(txtP250idKey.getText().toString());

        }

        if (kCicle==4){
            MostrarSorteio("500P", Integer.parseInt(txtP500idKey.getText().toString()));
            SorteioAtual_idSorteio = "500P";
            SorteioAtual_idKey = Integer.parseInt(txtP500idKey.getText().toString());
            kCicle = 0;
        }

    }

    public void btn50OnClick(View view){
        final TextView txtP50idKey = findViewById(R.id.txtP50idKey);
        SorteioAtual_idSorteio = "50P";
        SorteioAtual_idKey = Integer.parseInt(txtP50idKey.getText().toString());


        AlertDialog.Builder msgSorteio50 = new AlertDialog.Builder(ClienteActivity.this);
        msgSorteio50.setTitle("Sorteio com 50 Participantes...");
        msgSorteio50.setMessage("Olá "+ RegCliente.getNome() +", Este Sorteio (Cartela Cheia) será executado automaticamente quando 50 pessoas efetuarem suas inscrições." +
                "Para participar, você precisa inscrever uma ou mais Cartelas. Deseja fazer sua inscrição agora ?");

        msgSorteio50.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Preparando Sorteio 50 Participantes.",Toast.LENGTH_SHORT).show();
                // Preparar variáveis para envio ao próximo Activity

                MostrarSorteio("50P", Integer.parseInt(txtP50idKey.getText().toString()));
                SorteioAtual_idSorteio = "50P";
                SorteioAtual_idKey = Integer.parseInt(txtP50idKey.getText().toString());
                // Chamar Activity correspondente

                Intent GerCartelas = new Intent(getApplicationContext(), EditorCartela.class);
                startActivity(GerCartelas);
                // BD_Raiz.child("Sorteios").child("50P").child("Config").child("idSorteio").setValue(1);
                // P50();
            }
        });

        msgSorteio50.setNegativeButton("Agora Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //--------------------------------------------------------------------------------------------

        msgSorteio50.show();
    }

    public void btn100OnClick(View view){
        final TextView txtP100idKey = findViewById(R.id.txtP100idKey);
        AlertDialog.Builder msgSorteio100 = new AlertDialog.Builder(ClienteActivity.this);
        msgSorteio100.setTitle("Sorteio com 100 Participantes...");
        msgSorteio100.setMessage("Olá "+ RegCliente.getNome() +", Este Sorteio (Cartela Cheia) será executado automaticamente quando 100 pessoas efetuarem suas inscrições." +
                "Para participar, você precisa inscrever uma ou mais Cartelas. Deseja fazer sua inscrição agora ?");

        msgSorteio100.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Preparando Sorteio com 100 Participantes.",Toast.LENGTH_SHORT).show();
                // BD_Raiz.child("Sorteios").child("100P").child("Config").child("idSorteio").setValue(1);
                MostrarSorteio("100P", Integer.parseInt(txtP100idKey.getText().toString()));
                SorteioAtual_idSorteio = "100P";
                SorteioAtual_idKey = Integer.parseInt(txtP100idKey.getText().toString());
                Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
                ik.putExtra("idSorteio", SorteioAtual_idSorteio);
                ik.putExtra("idKey", SorteioAtual_idKey);
                ik.putExtra("vlCartela", SorteioAtual_ValorCartela);
                startActivity(ik);
                // P100();
            }
        });

        msgSorteio100.setNegativeButton("Agora Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //--------------------------------------------------------------------------------------------

        msgSorteio100.show();
    }

    public void btn250OnClick(View view){
        final TextView txtP250idKey = findViewById(R.id.txtP250idKey);
        AlertDialog.Builder msgSorteio250 = new AlertDialog.Builder(ClienteActivity.this);
        msgSorteio250.setTitle("Sorteio com 250 Participantes...");
        msgSorteio250.setMessage("Olá "+ RegCliente.getNome() +", Este Sorteio (Cartela Cheia) será executado automaticamente quando 250 pessoas efetuarem suas inscrições." +
                "Para participar, você precisa inscrever uma ou mais Cartelas. Deseja fazer sua inscrição agora ?");

        msgSorteio250.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Preparando Sorteio com 250 Participantes.",Toast.LENGTH_SHORT).show();
                MostrarSorteio("250P", Integer.parseInt(txtP250idKey.getText().toString()));
                SorteioAtual_idSorteio = "250P";
                SorteioAtual_idKey = Integer.parseInt(txtP250idKey.getText().toString());
                Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
                ik.putExtra("idSorteio", SorteioAtual_idSorteio);
                ik.putExtra("idKey", SorteioAtual_idKey);
                ik.putExtra("vlCartela", SorteioAtual_ValorCartela);
                startActivity(ik);

                // P250();
            }
        });

        msgSorteio250.setNegativeButton("Agora Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //--------------------------------------------------------------------------------------------

        msgSorteio250.show();
    }

    public void btn500OnClick(View view){
        final TextView txtP500idKey = findViewById(R.id.txtP500idKey);
        AlertDialog.Builder msgSorteio500 = new AlertDialog.Builder(ClienteActivity.this);
        msgSorteio500.setTitle("Sorteio com 500 Participantes...");
        msgSorteio500.setMessage("Olá "+ RegCliente.getNome() +", Este Sorteio (Cartela Cheia) será executado automaticamente quando 500 pessoas efetuarem suas inscrições." +
                "Para participar, você precisa inscrever uma ou mais Cartelas. Deseja fazer sua inscrição agora ?");

        msgSorteio500.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Preparando Sorteio com 500 Participantes.",Toast.LENGTH_SHORT).show();

                MostrarSorteio("500P", Integer.parseInt(txtP500idKey.getText().toString()));
                SorteioAtual_idSorteio = "500P";
                SorteioAtual_idKey = Integer.parseInt(txtP500idKey.getText().toString());
                Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
                ik.putExtra("idSorteio", SorteioAtual_idSorteio);
                ik.putExtra("idKey", SorteioAtual_idKey);
                ik.putExtra("vlCartela", SorteioAtual_ValorCartela);
                startActivity(ik);
                // P500();
            }
        });

        msgSorteio500.setNegativeButton("Agora Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //--------------------------------------------------------------------------------------------

        msgSorteio500.show();
    }

    public void MinhascartelasOnClick(View view){
        int idCall = 3;  finish();
        Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
        ik.putExtra("idSorteio", SorteioAtual_idSorteio);
        ik.putExtra("idKey", SorteioAtual_idKey);
        ik.putExtra("vlCartela", SorteioAtual_ValorCartela);
        ik.putExtra("Called", idCall);
        startActivity(ik);
    }

}


