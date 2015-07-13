package com.grarak.kerneladiutor;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.grarak.kerneladiutor.utils.Utils;

/**
 * Created by willi on 09.07.15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set english as default language if option is enabled
        if (Utils.getBoolean("forceenglish", false, this)) Utils.setLocale("en_US", this);

        // Check if darktheme is in use and cache it as boolean
        if (Utils.DARKTHEME = Utils.getBoolean("darktheme", false, this))
            super.setTheme(getDarkTheme());

        if (getParentViewId() != 0) setContentView(getParentViewId());
        else if (getParentView() != null) setContentView(getParentView());

        Toolbar toolbar;
        if ((toolbar = getToolbar()) != null) {
            if (Utils.DARKTHEME) toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Dark);
            setSupportActionBar(toolbar);
        }

        setStatusBarColor();
    }

    public abstract int getParentViewId();

    public abstract View getParentView();

    public int getDarkTheme() {
        return R.style.AppThemeDark;
    }

    public abstract Toolbar getToolbar();

    public void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.color_primary_dark));
        }
    }

}
