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

public class Register_act extends AppCompatActivity {

    EditText email_id,pass,pass2;
    Button registerUser;
  FirebaseAuth mfirebaseAuth;
  ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_act);
        email_id=(EditText)findViewById(R.id.email_id);
        pass=(EditText)findViewById(R.id.pass);
        pass2=(EditText)findViewById(R.id.pass2);
        registerUser=(Button)findViewById(R.id.registerUser);
        progressDialog=new ProgressDialog(this);

        //initializing firebase auth object

        mfirebaseAuth=FirebaseAuth.getInstance();
    }

    public void goToLogin(View view) {
        Intent iLogin=new Intent(Register_act.this,Login_act.class);
        startActivity(iLogin);
    }

    public void RegisterUser(View view) {
        String email=email_id.getText().toString().trim();
        String password=pass.getText().toString().trim();
        String password2=pass2.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)){
            Toast.makeText(this, "Please fill the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            if(password.equals(password2) && (password.length()>6)){
                progressDialog.setMessage("Please wait... ");
                progressDialog.show();
                mfirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)  {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                        Toast.makeText(Register_act.this, "Registered sucessfully", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Register_act.this,MainActivity.class);
                        startActivity(i);
                        }
                    }
                });




            }else{
                Toast.makeText(this, "Password do not match or pass length is less than 6 char", Toast.LENGTH_SHORT).show();
                return;
            }
        }


    }
}
