package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.database.sqlite.*;

public class show extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor cr;
    TextView id, name, price, qnty, mcn;
    ImageButton next, prev, first,last ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        db=openOrCreateDatabase("Mydata", Context.MODE_PRIVATE,null);

        id = findViewById(R.id.spid);
        name = findViewById(R.id.spname);
        price = findViewById(R.id.spprice);
        qnty = findViewById(R.id.spqnty);
        mcn = findViewById(R.id.spmcn);
        first  = findViewById(R.id.sfirst);
        last  = findViewById(R.id.slast);
        prev  = findViewById(R.id.sprev);
        next  = findViewById(R.id.snext);

        String q1="select * from pinfo1";
        cr=db.rawQuery(q1,null);

        int idindex = cr.getColumnIndex("pid");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    cr.moveToNext();
                    id.setText(cr.getString(0));
                    name.setText(cr.getString(1));
                    price.setText(cr.getString(2));
                    qnty.setText(cr.getString(3));
                    mcn.setText(cr.getString(4));
                }
                catch(Exception e){
                    cr.moveToLast();
                    id.setText(cr.getString(0));
                    name.setText(cr.getString(1));
                    price.setText(cr.getString(2));
                    qnty.setText(cr.getString(3));
                    mcn.setText(cr.getString(4));

                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    cr=db.rawQuery(q1,null);
                    cr.moveToPrevious();
                    id.setText(cr.getString(0));
                    name.setText(cr.getString(1));
                    price.setText(cr.getString(2));
                    qnty.setText(cr.getString(3));
                    mcn.setText(cr.getString(4));

                }
                catch(Exception b){
                    cr=db.rawQuery(q1,null);
                    cr.moveToFirst();
                    id.setText(cr.getString(0));
                    name.setText(cr.getString(1));
                    price.setText(cr.getString(2));
                    qnty.setText(cr.getString(3));
                    mcn.setText(cr.getString(4));

                }

            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cr=db.rawQuery(q1,null);
                cr.moveToFirst();
                id.setText(cr.getString(0));
                name.setText(cr.getString(1));
                price.setText(cr.getString(2));
                qnty.setText(cr.getString(3));
                mcn.setText(cr.getString(4));

            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cr=db.rawQuery(q1,null);
                cr.moveToLast();
                id.setText(cr.getString(0));
                name.setText(cr.getString(1));
                price.setText(cr.getString(2));
                qnty.setText(cr.getString(3));
                mcn.setText(cr.getString(4));
            }
        });
    }
}