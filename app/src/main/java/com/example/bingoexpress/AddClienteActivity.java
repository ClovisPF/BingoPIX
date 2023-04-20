package com.example.bingoexpress;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddClienteActivity extends AppCompatActivity {

    public DatabaseReference BD_Raiz;
    public FirebaseAuth ClienteBD;

    public final TCliente Cliente = new TCliente();
    public TRegClientes RegClientes = new TRegClientes();
    boolean CPFExiste = false, ContaCriada = false;
    public int Contingente = 0;
    public int SemCartela = 0;
    public int SemSaldo = 0;
    public final int TotalCartelas = 0;
    public int Rest1 = 0;
    public int Rest2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);
        BD_Raiz = FirebaseDatabase.getInstance().getReference();
        ClienteBD  = FirebaseAuth.getInstance();

        final Button btnContingente = findViewById(R.id.btnContingente);
        DatabaseReference BD_RegConfig = BD_Raiz.child("Config");
            BD_RegConfig.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    RegClientes = dataSnapshot.getValue(TRegClientes.class);
                    Contingente = RegClientes.Contingente + 1;
                    SemCartela = RegClientes.SemCartela + 1;
                    SemSaldo = RegClientes.SemSaldo;
                    btnContingente.setText(String.valueOf(Contingente));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }
    public void Finalizar(View view) {
        finish();
    }
    public void CheckBoxOnClick(View view) {
        CheckBox checkBox18 = findViewById(R.id.checkBox18);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        if (checkBox18.isChecked()) {
            btnCadastrar.setEnabled(true);
        } else {
            btnCadastrar.setEnabled(false);
        }
    }
    public int InformaData(String dt) {
        Date hoje = new Date();
        SimpleDateFormat dia = new SimpleDateFormat("dd");
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        String Dia = dia.format(hoje.getTime());
        String Mes = mes.format(hoje.getTime());
        String Ano = ano.format(hoje.getTime());
        int k = 0;
        if (dt.equals("dia")) {
            k = Integer.parseInt(Dia);
        }
        if (dt.equals("mes")) {
            k = Integer.parseInt(Mes);
        }
        if (dt.equals("ano")) {
            k = Integer.parseInt(Ano);
        }
        return k;
    }
    public boolean InvalidCPF(String cpf) {
        return cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999") ||
                cpf.equals("00000000000");
    }
    public boolean CPFValido(String cpf) {

        int[] IntCPF = new int[11];

        if (InvalidCPF(cpf) || cpf.length() != 11) { // CPF Inválido
            return false;
        } else { // CPF pode ser válido - Verificar agora
            for (int i = 0; i < 11; i++) { // transformar StrCPF em IntCPF
                IntCPF[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            // Cálculo de R1 ----------------

            for (int i = 0; i < 9; i++) {
                Rest1 = (IntCPF[i] * (10 - i)) + Rest1;
            }

            Rest1 = (Rest1 * 10) % 11;
            if (Rest1 == 10) {
                Rest1 = 0;
            }

            //-----------------------------

            // Cálculo de R2  -----------------

            for (int i = 0; i < 10; i++) {
                Rest2 = (IntCPF[i] * (11 - i)) + Rest2;
            }
            Rest2 = (Rest2 * 10) % 11;
            if (Rest2 == 10) {
                Rest2 = 0;
            }
            // --------------------------------
            // Validar CPF Agora

            return Rest1 == IntCPF[9] && Rest2 == IntCPF[10];
        }
    }
    public void CriarConta(final String cel, final String nome, final String cpf, final String email, final String senha){

      ClienteBD.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(AddClienteActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()){
            AlertDialog.Builder NovoCliente = new AlertDialog.Builder(AddClienteActivity.this);
            NovoCliente.setTitle("Parabéns! " + nome);
            NovoCliente.setMessage("É com muita alegria e sataisfação que informamos a Criação da sua Nova Conta. E lembre-se, A Sorte está com você!");

            NovoCliente.setPositiveButton(" Ok ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 UpDateCliente(cel, nome, cpf, email, senha, ClienteBD.getUid());
                 UpDateClientes(); // Atualizar Clientes/Config
                 finish();
                }
            });
            NovoCliente.show();
        } else {
          Toast.makeText(AddClienteActivity.this,"ERRO: Email e/ou Senha Inválidos.",Toast.LENGTH_LONG).show();
          ContaCriada = false;
          }
            }
        });
    }
    public void ExisteCPF(String cpf){
        DatabaseReference LocalizaCPF = BD_Raiz.child("Clientes").child(cpf);
        LocalizaCPF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(AddClienteActivity.this, "CPF Já Existe na base de dados: " + dataSnapshot.getValue(), Toast.LENGTH_LONG).show();
                    CPFExiste = true;
                } else {
                    Toast.makeText(AddClienteActivity.this, "CPF não existe na base de dados.", Toast.LENGTH_LONG).show();
                    CPFExiste = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public boolean ValidarForm(String n, String c, String e, String s) {

        if (!CPFValido(c) || n.isEmpty() || e.isEmpty() || s.isEmpty()) {
            //Toast.makeText(AddClienteActivity.this, "Nome:" + n + " CPF: " + c, Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
    public void Cadastrar(View view) {
        EditText edtNome = findViewById(R.id.edtNome);
        EditText edtCPF = findViewById(R.id.edtCPF);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtSenha = findViewById(R.id.edtSenha);
        EditText edtCel = findViewById(R.id.edtCel);

        String Cel = edtCel.getText().toString();
        String Nome = edtNome.getText().toString();
        String CPF = edtCPF.getText().toString();
        String Email = edtEmail.getText().toString();
        String Senha = edtSenha.getText().toString();

        if (ValidarForm(Nome, CPF, Email, Senha)) { // Verifica se Form tá ok
            CriarConta(Cel, Nome, CPF, Email, Senha); // Criar Novo Cliente
        } else {
            Toast.makeText(AddClienteActivity.this, "Formulário incompleto - Cód 380", Toast.LENGTH_LONG).show();
        }

    }
    public void UpDateCliente(String cel, String nome, String cpf, String email, String senha, String idCliente) {

        DatabaseReference registros = BD_Raiz.child("Clientes").child(idCliente).child("Registros");

        Cliente.cel      = cel;
        Cliente.nome     = nome;
        Cliente.cpf      = cpf;
        Cliente.senha    = senha;
        Cliente.email    = email;
        Cliente.dataIn   = InformaData("dia") + "-" + InformaData("mes") + "-" + InformaData("ano");
        Cliente.status   = "A CONFIRMAR";
        Cliente.score    = 0;
        Cliente.cartelas = 0;
        Cliente.sorteios = 0;
        Cliente.saldo    = 150;
        Cliente.in50P    = 0;
        Cliente.in100P   = 0;
        Cliente.in250P   = 0;
        Cliente.in500P   = 0;
        registros.setValue(Cliente);
    }
    public void UpDateClientes(){

        DatabaseReference RegConig = BD_Raiz.child("Config");
            RegClientes.SemCartela = SemCartela;
            RegClientes.SemSaldo = SemSaldo;
            //RegClientes.Ativos = 0;
            RegClientes.Contingente = Contingente;
            RegClientes.TotalCartelas = TotalCartelas;
        RegConig.setValue(RegClientes);

    }

}
