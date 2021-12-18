package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.database.sqlite.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class update extends AppCompatActivity {
    Spinner updatesp;
    SQLiteDatabase db;
    Cursor cr;
    ImageButton bupdate;
    List<String> theID=new ArrayList<String>();
    EditText upname, upprice, upqnty, upmcn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updatesp = findViewById(R.id.spinner);
        bupdate = findViewById(R.id.update);
        upname = findViewById(R.id.upname);
        upprice = findViewById(R.id.upprice);
        upqnty = findViewById(R.id.upqnty);
        upmcn = findViewById(R.id.upmcn);


        db=openOrCreateDatabase("Mydata", Context.MODE_PRIVATE,null);

        String listPIDquery="select * from pinfo1";
        cr=db.rawQuery(listPIDquery,null);
        cr.moveToFirst();
        int RowCount = cr.getCount();
        for(int i=0; i<RowCount;i++)
        {
            theID.add(cr.getString(0));
            cr.moveToNext();
        }
        ArrayAdapter<String> theSpinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,theID);
        theSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updatesp.setAdapter(theSpinner);
        updatesp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cr.moveToPosition(i);
                upname.setText(cr.getString(1));
                upprice.setText(cr.getString(2));
                upqnty.setText(cr.getString(3));
                upmcn.setText(cr.getString(4));

                bupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = upname.getText().toString();
                        int qnty = Integer.parseInt(upqnty.getText().toString());
                        int price = Integer.parseInt(upprice.getText().toString());
                        String mcn = upmcn.getText().toString();

                        ContentValues cv = new ContentValues();
                        cv.put("pname",name);
                        cv.put("price",price);
                        cv.put("pqnty",qnty);
                        cv.put("pmcn",mcn);
                        db.update("pinfo1",cv,"pid=?",new String[]{cr.getString(0)});
                        Toast.makeText(getApplicationContext(),"Product Updated!", Toast.LENGTH_LONG).show();

                        finish();

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}