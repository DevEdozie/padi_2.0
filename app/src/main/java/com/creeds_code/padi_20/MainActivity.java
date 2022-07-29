package com.creeds_code.padi_20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.creeds_code.padi_20.databinding.ActivityMainBinding;
import com.creeds_code.padi_20.databinding.ContentMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser user;
    ContentMainBinding secondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        secondBinding = ContentMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        mAuth = FirebaseAuth.getInstance();
        setNavigationDrawer();
        //Default Fragment Implementation
        displayDefaultPage();
    }

    private void displayDefaultPage(){
        Fragment fragment = new ScheduleFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_layout, fragment);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = mAuth.getCurrentUser();
//        if (user == null) {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        }
    }

    public void setNavigationDrawer() {
        //add drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.openDrawer, R.string.closeDrawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //navigation view
        binding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Handle navigation view item clicks
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.action_schedule) {
            fragment = new ScheduleFragment();
        } else if (id == R.id.action_calendar) {
            fragment = new CalendarFragment();
        } else if (id == R.id.action_sign_out) {
            displayDialog();
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        builder.setTitle(R.string.logout_dialog_title)
                .setMessage(R.string.logout_dialog_message)
                .setIcon(R.drawable.ic_action_signout)
                .setPositiveButton(R.string.action_ok_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logoutUser();
                    }
                })
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //SYSTEM DISMISSES DIALOG
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void logoutUser() {
        mAuth.signOut();
        Toast.makeText(MainActivity.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}