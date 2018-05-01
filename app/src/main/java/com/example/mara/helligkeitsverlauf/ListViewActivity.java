package com.example.mara.helligkeitsverlauf;

import android.app.Activity;
import android.app.Service;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends Activity {

    private Repo repo;
    private ListView listView;
    private CameraManager cameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        cameraManager = (CameraManager) getSystemService(Service.CAMERA_SERVICE);

        repo = (Repo) getIntent().getSerializableExtra("helligkeiten");
        listView = findViewById(R.id.listView);
        ArrayAdapter<Helligkeit> arrayAdapter = new ArrayAdapter<Helligkeit>(this, android.R.layout.simple_list_item_1, repo.getListe());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Helligkeit helligkeit = repo.get(i);
                if (helligkeit.getLux() < 200) {
                    try {
                        cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], true);
                        Thread.sleep(3000);
                        cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("Meins", helligkeit.getLux()+ "");
            }
        });

    }


}
