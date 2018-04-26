package com.example.mara.helligkeitsverlauf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btn_save;
    private Button btn_show;
    private EditText editTextOrt;
    private double lux;

    private Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repo = new Repo();

        btn_save = findViewById(R.id.btn_save);
        btn_show = findViewById(R.id.btn_show);
        editTextOrt = findViewById(R.id.editText);


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                intent.putExtra("helligkeiten", repo);
                startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ort = editTextOrt.getText().toString();
                if (ort.length() <= 0) {
                    return;
                }
                repo.add(new Helligkeit(27, ort, new Date()));
                editTextOrt.setText("");
            }
        });
    }
}
