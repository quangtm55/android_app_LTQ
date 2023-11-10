package com.example.myhanoiver1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvLocation;
    private LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvLocation = findViewById(R.id.rcv_location);
        locationAdapter = new LocationAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvLocation.setLayoutManager(linearLayoutManager);

        locationAdapter.setData(getListLocation());
        rcvLocation.setAdapter(locationAdapter);
        
    }

    private List<Location> getListLocation() {
        List<Location> list = new ArrayList<>();
        list.add(new Location(R.drawable.anh_lang_bac, "Lăng chủ tịch Hồ Chí Minh"));
        list.add(new Location(R.drawable.anh_van_mieu, "Văn Miếu - Quốc Tử Giám"));
        list.add(new Location(R.drawable.anh_ho_guom, "Hồ Hoàn Kiếm"));
        list.add(new Location(R.drawable.anh_dhbkhn, "Đại học Bách Khoa Hà Nội"));
        return list;
    }
}