package com.example.pro226design.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pro226design.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_act extends AppCompatActivity {
    EditText email_log,pass_log;
    Button button_log;
    ProgressDialog progressDialog;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_act);

        email_log=(EditText)findViewById(R.id.email_log);
        pass_log=(EditText)findViewById(R.id.pass_log);
        button_log=(Button)findViewById(R.id.LoginUser);
        progressDialog=new ProgressDialog(this);

        mauth=FirebaseAuth.getInstance();



    }

    public void goToRegister(View view) {

        Intent iRegister=new Intent(Login_act.this,Register_act.class);
        startActivity(iRegister);

    }

    public void newsAct(View view) {

        Intent i=new Intent(Login_act.this,MainActivity.class);
        startActivity(i);
    }

    public void LoginUser(View view) {
        String email=email_log.getText().toString().trim();
        String pass=pass_log.getText().toString().trim();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Fill the desired field", Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            progressDialog.setMessage("Loging in...");
            progressDialog.show();

            mauth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();

                        Intent i=new Intent(Login_act.this,MainActivity.class);
                        startActivity(i);
                    }
                }
            });

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

     /*   if(mauth.getCurrentUser()!=null){
            Intent i=new Intent(Login_act.this,MainActivity.class);
            startActivity(i);
        }
        */

    }
}
