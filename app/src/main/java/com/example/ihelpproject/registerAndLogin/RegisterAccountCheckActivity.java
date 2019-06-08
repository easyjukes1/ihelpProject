package com.example.ihelpproject.registerAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ihelpproject.R;

public class RegisterAccountCheckActivity extends AppCompatActivity {
    Button it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account_check);
        it = findViewById(R.id.it);
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterAccountCheckActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
