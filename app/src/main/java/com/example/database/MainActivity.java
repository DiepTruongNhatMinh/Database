package com.example.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DATA MAPPING

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAuthor:{
                Intent intent_author = new Intent(MainActivity.this, Author_Activituy.class);
                startActivity(intent_author);
            }
            case R.id.mnBook:{
                Intent intent_book = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent_book);
            }
            case  R.id.mnExit:{

            }
        }

                return super.onOptionsItemSelected(item);
    }
}