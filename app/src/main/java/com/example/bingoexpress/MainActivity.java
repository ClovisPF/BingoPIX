package com.example.bingoexpress;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    //public FirebaseAuth ClienteBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ClienteBD = FirebaseAuth.getInstance();
        //Button btnAcessar = findViewById(R.id.btnAcessar);
        //Button btnPrimeiroAcesso = findViewById(R.id.btnPrimeiroAcesso);
        //Button btnAdm = findViewById(R.id.btnAdm);

       // btnAdm.setVisibility(View.INVISIBLE);
        /*

            if (isConnected(MainActivity.this)){
                //Toast.makeText(MainActivity.this,"Possue acesso à Internet.",Toast.LENGTH_LONG).show();
                btnAcessar.setEnabled(true);
                btnPrimeiroAcesso.setEnabled(true);
            } else {
                Toast.makeText(MainActivity.this,"Não possue acesso à Internet.",Toast.LENGTH_LONG).show();
                btnAcessar.setEnabled(false);
                btnPrimeiroAcesso.setEnabled(false);
            }

         */
    }
    public void CriarCadastro(View view){
        AlertDialog.Builder NovoCliente = new AlertDialog.Builder(MainActivity.this);
        NovoCliente.setTitle("Criar Nova Conta...");
        NovoCliente.setMessage("Olá, Estamos muito contentes com a sua visita. Para criar sua nova conta, " +
                "você deverá acessar o Formulário de Cadastro pressionando Criar Nova Conta.");

        NovoCliente.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        NovoCliente.setPositiveButton("Criar Nova Conta", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), AddClienteActivity.class);
                startActivity(i);
            }
        });
      NovoCliente.show();
    }
    public boolean isConnected(Context cont){
        ConnectivityManager conmag = (ConnectivityManager)cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conmag != null ) {
            conmag.getActiveNetworkInfo();

            //Verifica internet pela WIFI
            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
               // Toast.makeText(MainActivity.this,"Conexão estabelecida com Wi-FI",Toast.LENGTH_LONG).show();
                return true;
            }

            //Verifica se tem internet móvel
            // Toast.makeText(MainActivity.this,"Conexão estabelecida com Dados Móveis",Toast.LENGTH_LONG).show();
            return conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
        }
      return false;
    }
    public void AdmLogin(String email, String senha){
       // ClienteBD = FirebaseAuth.getInstance();
      /*  ClienteBD.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){  // login efetuado com sucesso
                    //Toast.makeText(MainActivity.this,"Login efetuado com sucesso",Toast.LENGTH_LONG).show();
                    Intent ik = new Intent(getApplicationContext(), AdmActivity.class);
                    startActivity(ik);
                } else {  // login não efetuado.
                    Toast.makeText(MainActivity.this,"Erro no Login",Toast.LENGTH_LONG).show();
                }
            }
        })*/;
    }
    public void isLogin(String email, String senha){
        /*ClienteBD = FirebaseAuth.getInstance();
        ClienteBD.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login efetuado com sucesso",Toast.LENGTH_SHORT).show();
                    Intent ik = new Intent(getApplicationContext(), EditorCartela.class);
                    startActivity(ik);
                } else {
                    Toast.makeText(MainActivity.this,"Email ou senha inválidos. (Cód 381)",Toast.LENGTH_LONG).show();
                }
            }
        })*/;

    }
    public boolean ValidarForm(String email, String senha) {

        if (email.isEmpty() || senha.isEmpty() || senha.length() < 6) {
            return false;
        } else return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public void Finalizar(View view) {
       // ClienteBD.signOut();
        finish();
    }
    public void Acessar(View view){

        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtSenha = findViewById(R.id.edtSenha);

        if (ValidarForm(edtEmail.getText().toString(), edtSenha.getText().toString())){ // Verificar se dados estão corretos
         // isLogin(edtEmail.getText().toString(), edtSenha.getText().toString()); // Efetuar Login
        } else {  // Dados Incompletos ou errados)
          //  Toast.makeText(getApplicationContext(),"Erro de Identificação - Cód 381", Toast.LENGTH_SHORT).show();
        }
    }
    public void AdmAcessar(View view){
        AdmLogin("clovispf15@gmail.com","150177");
    }

}




