package com.pixelatedleaft.kore.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pixelatedleaft.kore.sample.databinding.ActivityHomeBinding;

public class ActivityHome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        setUpNavBar();
    }

    private void setUpNavBar(){
        binding.navBar.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                return true;
        }
        return false;
    }
}
