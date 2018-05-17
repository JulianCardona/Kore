package com.pixelleafs.kore.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.pixelleafs.kore.KoreActivity;
import com.pixelleafs.kore.sample.databinding.ActivityHomeBinding;

public class ActivityHome extends KoreActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private ActivityHomeBinding binding;

    @Override
    protected void initVars() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }

    @Override
    protected void initViews() {
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
