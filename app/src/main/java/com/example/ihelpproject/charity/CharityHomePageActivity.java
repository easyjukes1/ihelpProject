package com.example.ihelpproject.charity;

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

public class CharityHomePageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charityhomepage);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("charity homepage");


        BottomNavigationView bottomNav;
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CharityJobs_fragment()).commit();


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu, menu);
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
                        case R.id.action_job:
                            selectedFragment = new CharityJobs_fragment();
                            break;
                        case R.id.action_ourVolunteer:
                            selectedFragment = new CharityVolunteer_fragment();
                            break;
                        case R.id.action_requestedVolunteers:
                            selectedFragment = new CharityRequestedVolunteers_fragment();
                            break;
                        default:
                            selectedFragment = new CharityJobs_fragment();
                            break;


                    }
                    assert selectedFragment != null;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
