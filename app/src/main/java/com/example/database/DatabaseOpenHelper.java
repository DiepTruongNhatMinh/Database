package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.tv.AitInfo;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, "ThefirstDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Authors("+"author_id integer primary key,"+"author_name text,"+"author_address text,"+"author_email text)");

        db.execSQL("CREATE TABLE Book ("+" book_id integer primary key,"+" title text, "+" author_id integer not null constraint author_id references "+" " +
                " Authors(author_id) ON DELETE CASCADE ON UPDATE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Authors");
        onCreate(db);
    }
    //Author Table
    public int insertAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("author_id", author.getAuthor_id());
        contentValues.put("author_name", author.getAuthor_name());
        contentValues.put("author_address", author.getAuthor_address());
        contentValues.put("author_email", author.getAuthor_email());
        //if result == 0 (-1) --> the date not be store, result != 0, -1 --> the data is stored
        int result = (int)db.insert("Authors", null, contentValues);
        db.close();
        return result;
    }

    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors", null);
        if(cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public ArrayList<Author> getIDAuthor(int author_id){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors where author_id=" + author_id, null);
        if(cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public int updateAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("author_id", author.getAuthor_id());
        contentValues.put("author_name", author.getAuthor_name());
        contentValues.put("author_address", author.getAuthor_address());
        contentValues.put("author_email", author.getAuthor_email());
        String whereClause = "author_id = ?";  //and
        String whereArgs[] = {author.getAuthor_id() + ""};
        int result = db.update("Authors", contentValues, whereClause, whereArgs);
        db.close();
        return result;
    }

    public int deleteAuthor(int id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "author_id = ?";
        String whereArgs[] = {id + ""};
        int result = db.delete("Authors", whereClause, whereArgs);
        db.close();
        return result;
    }

    //Book Table
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Books", null);
        if(cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public ArrayList<Book> getIDBook(int book_id){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book where book_id ="  + book_id, null);
        if(cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public int insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book_id", book.getBook_id());
        contentValues.put("title", book.getTitle());
        contentValues.put("author_id", book.getAuthor_id());
        int result = (int) db.insert("Book", null, contentValues);
        db.close();
        return result;
    }
    public int updateBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book_id", book.getBook_id());
        contentValues.put("title", book.getTitle());
        contentValues.put("author_id", book.getAuthor_id());
        String whereClause = "id = ?";
        String whereArgs[] = {book.getBook_id() + ""};
        int result = db.update("Book", contentValues, whereClause, whereArgs);
        db.close();
        return result;
    }
    public int deleteBook(int book_id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "book_id = ?";
        String whereArgs[] = {book_id + ""};
        int result = db.delete("Book", whereClause, whereArgs);
        db.close();
        return result;
    }
}
