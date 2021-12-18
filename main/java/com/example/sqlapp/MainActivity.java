package com.example.sqlapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if (id==R.id.insert){
            Intent i = new Intent(getApplicationContext(), Insert.class);
            startActivity(i);

        }
        if (id==R.id.show){

            Intent s = new Intent(getApplicationContext(), show.class);
            startActivity(s);

        }
        if (id==R.id.update){

            Intent u = new Intent(getApplicationContext(), update.class);
            startActivity(u);

        }
        if (id==R.id.delete){

            Intent d = new Intent(getApplicationContext(), delete.class);
            startActivity(d);

        }
        if (id==R.id.exit){
            finish();
            System.exit(0);

        }
        return super.onOptionsItemSelected(item);
    }
}