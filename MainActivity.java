package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_image;
    TextView txt_nodata;
    DatabaseHelper mydb;
    ArrayList<String> Id, name, Roll_number, Phone, Address;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        empty_image = findViewById(R.id.empty_image);
        txt_nodata = findViewById(R.id.txt_nodata);
        add_button = findViewById(R.id.floatingActionButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        mydb = new DatabaseHelper(MainActivity.this);
        Id = new ArrayList<>();
        name = new ArrayList<>();
        Roll_number = new ArrayList<>();
        Phone = new ArrayList<>();
        Address = new ArrayList<>();
        storedata();
        customAdapter = new CustomAdapter(MainActivity.this, MainActivity.this, Id, name, Roll_number, Phone, Address);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storedata() {
        Cursor cursor = mydb.readalldata();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                Roll_number.add(cursor.getString(2));
                Phone.add(cursor.getString(3));
                Address.add(cursor.getString(4));
                empty_image.setVisibility(View.GONE);
                txt_nodata.setVisibility(View.GONE);
            }
        }

        else
        {
            empty_image.setVisibility(View.VISIBLE);
            txt_nodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirm();

        }

        return super.onOptionsItemSelected(item);
    }

    void confirm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are you sure you want to delete all Data ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper myDb = new DatabaseHelper(MainActivity.this);

                myDb.deleteAll();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
