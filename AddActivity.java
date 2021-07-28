package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    TextView txtview;
    EditText editText, editText2,editText3,editText4;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        txtview = findViewById(R.id.textView2);
        add_button = findViewById(R.id.button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper mydb= new DatabaseHelper(AddActivity.this);
                mydb.addstudent(editText.getText().toString().trim(),
                        editText2.getText().toString().trim(),
                        editText3.getText().toString().trim(),
                        editText4.getText().toString().trim());
                txtview.setText("Added Successfully!");
            }
        });
    }
}
