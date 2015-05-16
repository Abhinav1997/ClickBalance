package com.aj.eb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class HelpFragment extends Fragment {

    private String carrier,country;

    public HelpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);

        final CardView card8 = (CardView) rootView.findViewById(R.id.c8);
        final CardView card9 = (CardView) rootView.findViewById(R.id.c9);
        final CardView card10 = (CardView) rootView.findViewById(R.id.c10);
        final CardView card6 = (CardView) rootView.findViewById(R.id.c6);

        TelephonyManager manager = (TelephonyManager)getActivity().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        carrier = manager.getNetworkOperatorName();
        country = manager.getNetworkCountryIso();
        country = country.toUpperCase();

        if(SettingsFragment.theme==1) {
            card8.setCardBackgroundColor(0xff424242);
            card9.setCardBackgroundColor(0xff424242);
            card10.setCardBackgroundColor(0xff424242);
            card6.setCardBackgroundColor(0xff424242);
        }

        RelativeLayout rating =(RelativeLayout) rootView.findViewById(R.id.rate);
        rating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.aj.eb");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        RelativeLayout support =(RelativeLayout) rootView.findViewById(R.id.mail);
        support.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:" + "abhinav.jhanwar.august2@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Operator Support Request");
                intent.putExtra(Intent.EXTRA_TEXT, "Operator: " + carrier + " , Country Code: " + country);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        return rootView;
    }
}
