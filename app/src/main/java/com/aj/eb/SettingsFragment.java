package com.aj.eb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsFragment extends Fragment {

    static int theme;
    static int tom = 0;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        final CheckBox themed = (CheckBox) rootView.findViewById(R.id.dark_ed);
        final CardView card7 = (CardView) rootView.findViewById(R.id.c7);

        themed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences sharedPreferences;
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(themed.isChecked())
                {
                    editor.putInt("theme", 1);
                    theme=1;
                }
                else
                {
                    editor.putInt("theme", 0);
                    theme=0;
                }
                editor.commit();
                if(MainActivity.rt!=theme) {
                    tom = 1;
                    Intent intent = new Intent(SettingsFragment.this.getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });

        if (theme==1) {
            themed.setChecked(true);
            card7.setCardBackgroundColor(0xff424242);
        }

        return rootView;
    }
}
