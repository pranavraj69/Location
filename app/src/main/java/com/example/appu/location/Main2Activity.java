package com.example.appu.location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

       // private FirebaseDatabase firebaseDatabase;
        private DatabaseReference databaseReference;
        private EditText e1;
        private EditText e2;
        private EditText e3 ;
        private EditText e4 ;
        private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        e1 = (EditText) findViewById(R.id.editText5);
        e2 = (EditText) findViewById(R.id.editText8);
        e3 = (EditText) findViewById(R.id.editText6);
        e4 = (EditText) findViewById(R.id.editText7);
        b = (Button) findViewById(R.id.button4);
       final Intent intent = new Intent(this,MapsActivity.class);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                    startActivity(intent);


            }
        });
    }
    private void update()
    {
        String vehiclename = e1.getText().toString().trim();
        String vehicleno = e2.getText().toString().trim();
        String pass = e3.getText().toString().trim();
        String phonenum = e4.getText().toString().trim();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("loc/Userdetails");
        Details details = new Details(vehiclename,pass,vehicleno,phonenum);
        databaseReference.setValue(details);
    }
}
