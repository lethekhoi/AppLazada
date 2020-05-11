package com.example.applazada.View.ThanhToan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.applazada.R;

public class ThanhToanActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        toolbar = findViewById(R.id.toolbarThanhToan);
        setSupportActionBar(toolbar);
    }
}
