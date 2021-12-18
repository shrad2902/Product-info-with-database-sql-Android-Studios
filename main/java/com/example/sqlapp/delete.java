package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.database.sqlite.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class delete extends AppCompatActivity {
    Spinner deletesp;
    SQLiteDatabase db;
    Cursor cr;
    ImageButton deleteb;
    String[] theIDs;
    TextView textView;
    List<String> theID=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deletesp = findViewById(R.id.deletesp);
        deleteb = findViewById(R.id.deleteb);


        db=openOrCreateDatabase("Mydata", Context.MODE_PRIVATE,null);

        String listPIDquery="select * from pinfo1";cr=db.rawQuery(listPIDquery,null);
        cr.moveToFirst();
        int RowCount = cr.getCount();
        for(int i=0; i<RowCount;i++)
        {
            theID.add(cr.getString(0));
            cr.moveToNext();
        }
        theIDs = theID.toArray(new String[theID.size()]);
        ArrayAdapter<String> theSpinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,theIDs);
        theSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deletesp.setAdapter(theSpinner);
        deletesp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cr.moveToPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        deleteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.delete("pinfo1", "pid=?", new String[]{cr.getString(0)});
                Toast.makeText(getApplicationContext(),cr.getString(1)+" deleted",Toast.LENGTH_LONG).show();

                finish();
                overridePendingTransition( 0, 0);
                startActivity(getIntent());
                overridePendingTransition( 0, 0);
            }
        });
    }
}