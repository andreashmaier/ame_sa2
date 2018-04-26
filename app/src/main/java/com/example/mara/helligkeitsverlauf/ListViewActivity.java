package com.example.mara.helligkeitsverlauf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends Activity {

    private Repo repo;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        repo = (Repo) getIntent().getSerializableExtra("helligkeiten");
        listView = findViewById(R.id.listView);
        ArrayAdapter<Helligkeit> arrayAdapter = new ArrayAdapter<Helligkeit>(this, android.R.layout.simple_list_item_1, repo.getListe());
        listView.setAdapter(arrayAdapter);

    }
}
