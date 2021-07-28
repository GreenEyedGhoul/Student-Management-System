package com.example.assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Update extends AppCompatActivity {
    EditText txt_name,txt_roll,txt_Phone,txt_Address;
    Button update, delete;
    TextView txtupdate;
    String Id,Roll_number,name,Phone,Address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        txt_name = findViewById(R.id.editText5);
        txt_roll = findViewById(R.id.editText6);
        txt_Phone = findViewById(R.id.editText7);
        txt_Address = findViewById(R.id.editText8);
        txtupdate = findViewById(R.id.textView3);
        update = findViewById(R.id.button2);
        delete = findViewById(R.id.button3);
        //Set data First
        getsetdata();
        ActionBar ab = getSupportActionBar();
        if(ab!=null)
        {
            ab.setTitle(Roll_number);
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatabaseHelper myDb= new DatabaseHelper(Update.this);
                    name = txt_name.getText().toString().trim();
                    Roll_number = txt_roll.getText().toString().trim();
                    Phone = txt_Phone.getText().toString().trim();
                    Address = txt_Address.getText().toString().trim();
                    myDb.updatedata(Id,name,Roll_number,Phone,Address);
                    txtupdate.setText("Updated Successfully!");
            }

        });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        confirm();
                }
            });
    }
    void getsetdata()
    {
        if(getIntent().hasExtra("Id") && getIntent().hasExtra("name") && getIntent().hasExtra("Roll_number")
                && getIntent().hasExtra("Phone") &&getIntent().hasExtra("Address") )
        {
            //Getting intent data
                    Id = getIntent().getStringExtra("Id");
                    name = getIntent().getStringExtra("name");
                    Roll_number = getIntent().getStringExtra("Roll_number");
                    Phone = getIntent().getStringExtra("Phone");
                    Address = getIntent().getStringExtra("Address");
             //Setting intent data
                    txt_name.setText(name);
                    txt_roll.setText(Roll_number);
                    txt_Phone.setText(Phone);
                    txt_Address.setText(Address);
        }
        else
        {
            txtupdate.setText("No data");
        }
    }
    void confirm()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+Roll_number+" ?");
        builder.setMessage("Are you sure you want to delete "+Roll_number+" ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper myDb = new DatabaseHelper(Update.this);
                myDb.delete(Id);

                txtupdate.setText("Deleted Successfully!");
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
