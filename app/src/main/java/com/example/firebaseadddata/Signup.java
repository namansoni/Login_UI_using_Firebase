package com.example.firebaseadddata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
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

public class Signup extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView, imageview1;
    EditText editTextEmail, editTextPass;
    TextView textView1, textView2,textView3;
    android.widget.Button Button;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button=(Button)findViewById(R.id.button);
        progressDialog=new ProgressDialog(this);
        imageView = (ImageView) findViewById(R.id.imageView5);
        imageview1 = (ImageView) findViewById(R.id.imageView6);
        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        view=(View)findViewById(R.id.view);
        firebaseAuth=FirebaseAuth.getInstance();
        imageView.animate().translationY(-1200).setStartDelay(800).setDuration(1200);
        imageview1.animate().translationY(-1200).setStartDelay(800).setDuration(1200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);

                editTextEmail.setVisibility(View.VISIBLE);
                editTextPass.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                Button.setVisibility(View.VISIBLE);
            }
        }, 2000);
        Button.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if(v==Button){
            RegisterUser();
        }

    }

    private void RegisterUser() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPass.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this,"Please Enter Your Email",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
            return ;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(Signup.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(Signup.this,Profile.class);
                    startActivity(i);
                }else
                {
                    Toast.makeText(Signup.this,"Failed to Register",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    }

