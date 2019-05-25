package com.example.ihelpproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class registerActivity extends AppCompatActivity {
    RadioGroup rg_userType;

    RelativeLayout layout1;
    LinearLayout Layout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_usertype);
        
        Button btn_continue;
        layout1 = findViewById(R.id.layoutWho);
        Layout2 = findViewById(R.id.layout2);

        btn_continue = findViewById(R.id.btn_continue);
        rg_userType = findViewById(R.id.rg_userType);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = rg_userType.getCheckedRadioButtonId();
                Fragment selectedFragment = null;
                switch (selectedId) {
                    case R.id.rb_charity:
                        selectedFragment = new RegisterCharityFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        layout1.setVisibility(View.GONE);
                        Layout2.setVisibility(View.GONE);

                        break;
                    case R.id.rb_employee:
                        selectedFragment = new RegisterEmployeeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        layout1.setVisibility(View.GONE);
                        Layout2.setVisibility(View.GONE);
                        break;
                    case R.id.rb_genralUser:
                        selectedFragment = new RegisterGenralUserFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        layout1.setVisibility(View.GONE);
                        Layout2.setVisibility(View.GONE);
                        break;
                    case R.id.rb_student:
                        selectedFragment = new RegisterStudentFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        layout1.setVisibility(View.GONE);
                        Layout2.setVisibility(View.GONE);
                        break;
                    case R.id.rb_supervisor:
                        selectedFragment = new RegisterSupervisorFragment();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                        layout1.setVisibility(View.GONE);
                        Layout2.setVisibility(View.GONE);
                        break;
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}