package com.aj.eb;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements DrawerFragment.DrawerListener {

    Drawable oldDrawable;
    DrawerFragment mFragment;
    Toolbar mToolbar;
    View oldView;
    static int rt;

    int[] resources = {R.drawable.ic_device_selected, R.drawable.ic_action_settings_selected, R.drawable.ic_action_help_selected};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SettingsFragment.theme = sharedPreferences.getInt("theme", 0);
        rt = SettingsFragment.theme;

        FontsOverride.setDefaultFont(this, "MONOSPACE", "Roboto-Medium.ttf");
        if (SettingsFragment.theme == 1) {
            setTheme(R.style.DarkAppTheme);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mFragment.init(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mFragment.setListener(this);
        if (SettingsFragment.tom == 1) {
            displayView(1);
            SettingsFragment.tom = 0;
        } else {
            displayView(0);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        if (oldView != null) {
            ImageView oldImage = (ImageView) oldView.findViewById(R.id.nav_icon);
            TextView oldText = (TextView) oldView.findViewById(R.id.title);
            oldImage.setImageDrawable(oldDrawable);
            if (SettingsFragment.theme==1) {
                oldText.setTextColor(0xffffffff);
            }
            else {
                oldText.setTextColor(0xff000000);
            }
        }

        oldView = view;
        ImageView imageView = (ImageView) view.findViewById(R.id.nav_icon);
        TextView textView = (TextView) view.findViewById(R.id.title);
        oldDrawable = imageView.getDrawable();
        imageView.setImageResource(resources[position]);
        textView.setTextColor(0xff009688);
        mToolbar.setTitle(textView.getText());
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new BalanceFragment();
                break;
            case 1:
                fragment = new SettingsFragment();
                break;
            case 2:
                fragment = new HelpFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }
}
