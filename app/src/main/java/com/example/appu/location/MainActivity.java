package com.example.appu.location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B = (Button) findViewById(R.id.button5);
        final Intent I =  new Intent (this, Main2Activity.class);
       B.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(I);
           }
       });
    }
}
