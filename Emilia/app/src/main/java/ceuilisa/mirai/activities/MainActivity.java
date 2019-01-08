package ceuilisa.mirai.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tbruyelle.rxpermissions2.RxPermissions;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.FragmentCenter;
import ceuilisa.mirai.fragments.FragmentLeft;
import ceuilisa.mirai.fragments.FragmentRight;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;

public class MainActivity extends WithPanelActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        Common.showLog("同意了11111");
                    } else {
                        // Oups permission denied
                    }
                });
        rxPermissions
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        Common.showLog("同意了11111");
                    } else {
                        // Oups permission denied
                    }
                });
        /*if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_PHONE_STATE}, 1);
        }*/
    }



    @Override
    boolean hasImage() {
        return false;
    }

    @Override
    boolean hasProgress() {
        return false;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_main;
    }

    @Override
    void initView() {
        super.initView();


        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initFragments();
    }

    @Override
    void initData() {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }

    private void initFragments() {
        FragmentLeft fragmentLeft = new FragmentLeft();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragmentLeft)
                .show(fragmentLeft)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
