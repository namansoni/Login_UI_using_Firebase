package com.example.firebaseadddata;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Button button,button1;
    DatabaseReference databaseReference;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.Name);
        editText2=(EditText)findViewById(R.id.place);
        button=(Button)findViewById(R.id.savedata);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("person");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddata();
            }
        });

    }


    private void adddata() {
        String name=editText1.getText().toString().trim();
        String place=editText2.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            String id= databaseReference.push().getKey();
            person=new Person();
            person.setName(name);
            person.setId(id);
            person.setPlace(place);
            databaseReference.push().setValue(person);

            Toast.makeText(this, "Data Added" , Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Please Enter Data", Toast.LENGTH_SHORT).show();
        }
    }
}
