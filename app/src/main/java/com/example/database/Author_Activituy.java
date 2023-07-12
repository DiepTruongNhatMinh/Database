package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.R.layout;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Author_Activituy extends AppCompatActivity {
    ArrayList<Author> list_author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_activituy);

        //Data mapping
        EditText edt_author_id = (EditText) findViewById(R.id.author_id);
        EditText edt_author_name = (EditText) findViewById(R.id.author_name);
        EditText edt_author_email = (EditText) findViewById(R.id.author_email);
        EditText edt_author_address = (EditText) findViewById(R.id.author_address);

        Button btn_exit = (Button) findViewById(R.id.btn_Exit);
        Button btn_select = (Button) findViewById(R.id.btn_Select);
        Button btn_save = (Button) findViewById(R.id.btn_Save);
        Button btn_delete = (Button) findViewById(R.id.btn_Delete);
        Button btn_update = (Button) findViewById(R.id.btn_Update);
        GridView gridView = (GridView) findViewById(R.id.gridView);

        DatabaseOpenHelper dbhelper = new DatabaseOpenHelper(this);

        //btn Exit
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //btn Save
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();
                author.setAuthor_id(Integer.parseInt(edt_author_id.getText().toString()));
                author.setAuthor_address(edt_author_address.getText().toString());
                author.setAuthor_email(edt_author_email.getText().toString());
                author.setAuthor_name(edt_author_name.getText().toString());

                if (dbhelper.insertAuthor(author) > 0)
                    Toast.makeText(getApplicationContext(), "Save successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Save error!", Toast.LENGTH_SHORT).show();
            }
        });

        //btn Select
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_author_id.getText().toString().equals(""))
                    list_author = dbhelper.getAllAuthor();
                else
                    list_author = dbhelper.getIDAuthor(Integer.parseInt(edt_author_id.getText().toString()));
                ArrayList<String> list_string = new ArrayList<>();
                for (Author author:list_author) {
                    list_string.add(author.getAuthor_id()+"");
                    list_string.add(author.getAuthor_name()+"");
                    list_string.add(author.getAuthor_address()+"");
                    list_string.add(author.getAuthor_email()+"");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Author_Activituy.this, android.R.layout.simple_list_item_1, list_string);
                gridView.setAdapter(adapter);
            }
        });

        //btn Delete
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbhelper.deleteAuthor(Integer.parseInt(edt_author_id.getText().toString())) > 0)
                    Toast.makeText(getApplicationContext(), "Delete successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Delete error!", Toast.LENGTH_SHORT).show();
            }
        });

        //btn Update
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();
                author.setAuthor_id(Integer.parseInt(edt_author_id.getText().toString()));
                author.setAuthor_address(edt_author_address.getText().toString());
                author.setAuthor_email(edt_author_email.getText().toString());
                author.setAuthor_name(edt_author_name.getText().toString());

                //
                if (dbhelper.updateAuthor(author) > 0)
                    Toast.makeText(getApplicationContext(), "Update successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Update error!", Toast.LENGTH_SHORT).show();
            }
        });

        //

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Author author = list_author.get(position/4);
                edt_author_address.setText(author.getAuthor_address()+"");
                edt_author_email.setText(author.getAuthor_email()+"");
                edt_author_id.setText((author.getAuthor_id()+""));
                edt_author_name.setText(author.getAuthor_name()+"");
            }
        });

    }
}