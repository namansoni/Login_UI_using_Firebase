package com.example.firebaseadddata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout textInputLayout1,textInputLayout2;
    EditText editTextEmail,editTextpass;
    Button button;
    TextView textView;
    ImageView imageView;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        imageView=(ImageView)findViewById(R.id.loginback);
        textView=(TextView)findViewById(R.id.Register);
        editTextEmail=(EditText) findViewById(R.id.Email);
        editTextpass=(EditText) findViewById(R.id.Password);
        button=(Button)findViewById(R.id.btnSignIn);
        textInputLayout1=(TextInputLayout)findViewById(R.id.textinputlayout1);
        textInputLayout2=(TextInputLayout)findViewById(R.id.textinputlayout2);
        imageView.animate().translationY(-600).setStartDelay(200).setDuration(1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textInputLayout1.setVisibility(View.VISIBLE);
                textInputLayout2.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                editTextEmail.setVisibility(View.VISIBLE);
                editTextpass.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);

            }
        },1000);
        button.setOnClickListener(this);
        textView.setOnClickListener(this);

        if(firebaseAuth.getCurrentUser()!=null){
            Intent i=new Intent(LoginActivity.this,Profile.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==button){
            userLogin();
        }
        if(v==textView){
            Intent i= new Intent(LoginActivity.this,Signup.class);
            startActivity(i);
        }
    }

    private void userLogin() {
        String email= editTextEmail.getText().toString().trim();
        String password= editTextpass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return ;
        }
        progressDialog.setMessage("Loging In");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Logged In",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,Profile.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Email -id not Registered",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}

