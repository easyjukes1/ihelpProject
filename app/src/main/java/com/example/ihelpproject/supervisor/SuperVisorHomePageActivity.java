package com.example.ihelpproject.supervisor;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ihelpproject.registerAndLogin.LoginActivity;
import com.example.ihelpproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class SuperVisorHomePageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisorhomepage);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Supervisor homepage");

        BottomNavigationView bottomNav;
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //by default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new SupervisorVolunteer_fragment()).commit();


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_signout) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.action_myVolunteer:
                            selectedFragment = new SupervisorVolunteer_fragment();
                            break;
                        case R.id.action_overview:
                            selectedFragment = new SupervisorOverView_fragment();
                            break;
                        default:
                            selectedFragment = new SupervisorVolunteer_fragment();
                            break;
                    }



                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
