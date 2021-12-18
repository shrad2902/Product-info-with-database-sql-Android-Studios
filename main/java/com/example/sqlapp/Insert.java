package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.database.sqlite.*;


public class Insert extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor cr;
    EditText id, name, price, qnty, mcn;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        id = findViewById(R.id.ipid);
        name = findViewById(R.id.ipname);
        price = findViewById(R.id.ipprice);
        qnty = findViewById(R.id.ipqnty);
        mcn = findViewById(R.id.ipmcn);
        button  = findViewById(R.id.iadd);

        db = openOrCreateDatabase("Mydata", Context.MODE_PRIVATE,null);
        db.execSQL("Create Table If Not Exists pinfo1(pid int, pname varchar, price int, pqnty int, pmcn varchar);");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = "Insert into pinfo1 Values('"+id.getText()+"','"+name.getText()+"','"+price.getText()+"','"+qnty.getText()+"','"+mcn.getText()+"')";
                db.execSQL(q);
                Toast.makeText(getApplicationContext(),"Product " + name.getText()+ " Added!", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }
}