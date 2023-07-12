package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    ArrayList<Book>  list_book;
    ArrayList<Author> list_author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //https://openplanning.net/12617/android-spinner

        //Data mapping

        Spinner spinner = (Spinner) findViewById(R.id.spinner_Author_name);
        EditText edt_book_id = (EditText) findViewById(R.id.book_id);
        EditText edt_book_title = (EditText) findViewById(R.id.book_title);
        Button btn_save = (Button) findViewById(R.id.btn_Save);
        Button btn_select = (Button) findViewById(R.id.btn_Select);
        Button btn_update = (Button) findViewById(R.id.btn_Update);
        Button btn_delete = (Button) findViewById(R.id.btn_Delete);
        Button btn_exit = (Button) findViewById(R.id.btn_Exit);
        GridView gridView = (GridView) findViewById(R.id.gridView);

        DatabaseOpenHelper dbhelper = new DatabaseOpenHelper(this);

        //Spinner
        list_author = dbhelper.getAllAuthor();
        ArrayList<String> list_item = new ArrayList<>();
        for(Author au:list_author)
            list_item.add(au.getAuthor_id() + "\t" + au.getAuthor_name());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_item);
        spinner.setAdapter(adapter);

        //btn EXIT
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //btn SELECT
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_book_id.getText().toString().equals(""))
                    list_book = dbhelper.getAllBook();
                else
                    list_book = dbhelper.getIDBook(Integer.parseInt(edt_book_id.getText().toString()));
                ArrayList<String> list_string = new ArrayList<>();
                for (Book book:list_book) {
                    list_string.add(book.getBook_id()+"");
                    list_string.add(book.getTitle()+"");
                    list_string.add(book.getAuthor_id()+"");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookActivity.this, android.R.layout.simple_list_item_1, list_string);
                gridView.setAdapter(adapter);
            }
        });

        //btn_SAVE
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_spinner = spinner.getSelectedItem().toString();
                String[] author_id_spinner = str_spinner.split("\t");
                Book book = new Book();
                book.setBook_id(Integer.parseInt(edt_book_id.getText().toString()));
                book.setTitle(edt_book_title.getText().toString());
                book.setAuthor_id(Integer.parseInt(author_id_spinner[0]));
                if(dbhelper.insertBook(book) > 0)
                    Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Save Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}