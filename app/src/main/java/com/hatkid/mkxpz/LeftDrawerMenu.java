package com.hatkid.mkxpz;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.example.backup.BackupUtils;

public class LeftDrawerMenu {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Context context;

    public LeftDrawerMenu(Context context, DrawerLayout drawerLayout, NavigationView navigationView) {
        this.context = context;
        this.drawerLayout = drawerLayout;
        this.navigationView = navigationView;
        setupMenu();
    }

    private void setupMenu() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_backup) {
                    BackupUtils.backupAppFiles(context);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                // ...existing code for other menu items...
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
}
