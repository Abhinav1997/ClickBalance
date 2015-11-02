package com.aj.eb;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BalanceFragment extends Fragment {

    private String phn, g2, g3, g4, dnd, carrier1, shrt, txt, carrier, country;
    View rootView;
    int phoneCheck, smsCheck;
    private static final int PERMISSIONS_REQUEST_CALL_PHONE = 0, PERMISSIONS_REQUEST_SEND_SMS = 1;

    public BalanceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_balance, container, false);

        phoneCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
        smsCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS);

        final CardView card1 = (CardView) rootView.findViewById(R.id.c1);
        final CardView card2 = (CardView) rootView.findViewById(R.id.c2);
        final CardView card3 = (CardView) rootView.findViewById(R.id.c3);
        final CardView card4 = (CardView) rootView.findViewById(R.id.c4);
        final CardView card5 = (CardView) rootView.findViewById(R.id.c5);
        final CardView card6 = (CardView) rootView.findViewById(R.id.c6);
        final TextView net = (TextView) rootView.findViewById(R.id.nname);


        if (SettingsFragment.theme == 1) {
            card1.setCardBackgroundColor(0xff424242);
            card2.setCardBackgroundColor(0xff424242);
            card3.setCardBackgroundColor(0xff424242);
            card4.setCardBackgroundColor(0xff424242);
            card5.setCardBackgroundColor(0xff424242);
            card6.setCardBackgroundColor(0xff424242);
        }

        getnetwork();

        net.setText(carrier);
        getcodes();
        showdetails();
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void getcodes() {
        if (country.contains("IN")) {
            if (carrier1.contains("DEA")) {
                phn = "*121#";
                g2 = g3 = "*125#";
                dnd = "1909";
            } else if (carrier1.contains("RELIANCE")) {
                phn = "*367#";
                g2 = g3 = "*111*1*3*#";
                dnd = "1909";
            } else if (carrier1.contains("AIRCEL")) {
                phn = "*125#";
                g2 = "*126*1#";
                g3 = "*126*4#";
                dnd = "1909";
                shrt = "arcl";
            } else if (carrier1.contains("UNINOR")) {
                phn = "*222*2#";
                g2 = g3 = "*222#";
                dnd = "1909";
            } else if (carrier1.contains("MTNL") || carrier1.contains("DOLPHIN")) {
                phn = "*444#";
                g2 = g3 = "*446#";
                dnd = "1909";
            } else if (carrier1.contains("TATA") || carrier1.contains("T24")) {
                phn = "*111#";
                g2 = g3 = "*111*1#";
                dnd = "1909";
            } else if (carrier1.contains("AIRTEL") || carrier1.contains("BHARTI")) {
                phn = "*123#";
                g2 = "*123*10#";
                g3 = "*123*11#";
                dnd = "1909";
                shrt = "artl";
            } else if (carrier1.contains("VODA") || carrier1.contains("HUTCH")) {
                phn = "*141#";
                g2 = g3 = "*111*6*2#";
                dnd = "1909";
            } else if (carrier1.contains("VIDEOCON")) {
                phn = g2 = g3 = "*123#";
                dnd = "1909";
            } else if (carrier1.contains("BSNL") || carrier1.contains("CELLONE")) {
                phn = "*123#";
                g2 = g3 = "*112*2#";
                dnd = "1909";
            } else if (carrier1.contains("BPL") || carrier1.contains("LOOP")) {
                phn = "*100#";
                g2 = g3 = "800";
                dnd = "1909";
            }
        } else if (country.contains("US")) {
            if (carrier1.contains("T-MOBILE")) {
                phn = "#225#";
                g2 = g3 = g4 = "#932#";
            } else if (carrier1.contains("SPRINT")) {
                phn = "*4";
                g3 = g4 = "1311";
                shrt = "sprnt";
                txt = "USAGE";
            } else if (carrier1.contains("VERIZON")) {
                phn = "*#225";
                g2 = g3 = g4 = "#3282";
            } else if (carrier1.contains("&")) {
                //phn = "*#225";
                phn = "*777#";
                //g2 = g3 = g4 = "#3282";
                g2 = g3 = g4 = "*777*3#";
            }
        } else if (country.contains("GB") || country.contains("UK")) {
            if (carrier1.contains("GIFFGAFF")) {
                phn = "*100#";
                g2 = g3 = g4 = "43030";
                shrt = "gff";
                txt = "PLAN";
            } else if (carrier1.contains("O2")) {
                phn = "*10#";
                g2 = g3 = g4 = "20202";
                shrt = "o2uk";
                txt = "BALANCE";
            } else if (carrier1.contains("3") || carrier1.contains("THREE")) {
                phn = g3 = g4 = "333";
            }
        } else if (country.contains("BR")) {
            if (carrier1.contains("TIM")) {
                phn = g2 = g3 = g4 = "*222#";
            } else if (carrier1.contains("VIVO")) {
                phn = g2 = g3 = g4 = "*8000";
            } else if (carrier1.contains("CLARO")) {
                phn = g2 = g3 = g4 = "*544#";
            } else if (carrier1.contains("OI")) {
                phn = g2 = g3 = g4 = "*805";
            }
        } else if (country.contains("IT") || country.contains("39")) {
            if (carrier1.contains("VODA")) {
                phn = "404";
                g2 = g3 = g4 = "http://www.vodafone.it/portal/Privati/Tariffe-e-Prodotti/Prodotti/sim-piani-internet";
                shrt = "vodait";
            } else if (carrier1.contains("WIND")) {
                phn = g2 = g3 = g4 = "*123#";
            }
        } else if (country.contains("RU")) {
            if (carrier1.contains("MEGA")) {
                phn = "*100#";
                g2 = g3 = g4 = "*217#";
            }
        } else if (country.contains("DE")) {
            if (carrier1.contains("NETZCLUB")) {
                phn = g2 = g3 = g4 = "*101#";
            } else if (carrier1.contains("MEDION")) {
                phn = g2 = g3 = g4 = "*100#";
            }
        } else if (country.contains("BE")) {
            if (carrier1.contains("MOBISTAR")) {
                phn = g2 = g3 = g4 = "#123#";
            } else if (carrier1.contains("O2")) {
                phn = "*101#";
                g2 = g3 = g4 = "*100#";
            }
        } else if (country.contains("AU")) {
            if (carrier1.contains("VODAFONE")) {
                phn = g2 = g3 = g4 = "1512";
            }
        }
    }

    private void showdetails() {
        RelativeLayout phone = (RelativeLayout) rootView.findViewById(R.id.pba);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAirplaneModeOn(getActivity().getApplicationContext())) {
                    airplane();
                } else if (phn == null) {
                    nosupport();
                } else {
                    sphn(phn);
                }
            }
        });

        RelativeLayout data2 = (RelativeLayout) rootView.findViewById(R.id.dba);
        data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAirplaneModeOn(getActivity().getApplicationContext())) {
                    airplane();
                } else if (phn == null) {
                    nosupport();
                } else if (g2 == null) {
                    opnosupport("2G data");
                } else if (shrt == "gff") {
                    smsg(g2);
                } else if (shrt == "vodait") {
                    net(g2);
                } else {
                    sphn(g2);
                }
            }
        });

        RelativeLayout data3 = (RelativeLayout) rootView.findViewById(R.id.dba3);
        data3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAirplaneModeOn(getActivity().getApplicationContext())) {
                    airplane();
                } else if (phn == null) {
                    nosupport();
                } else if (shrt == "sprnt" || shrt == "gff") {
                    smsg(g3);
                } else if (shrt == "vodait") {
                    net(g3);
                } else {
                    sphn(g3);
                }
            }
        });

        RelativeLayout data4 = (RelativeLayout) rootView.findViewById(R.id.dba4);
        data4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAirplaneModeOn(getActivity().getApplicationContext())) {
                    airplane();
                } else if (phn == null) {
                    nosupport();
                } else if (shrt == "artl" || shrt == "arcl") {
                    Toast.makeText(getActivity(), carrier + " 4G isn't properly supported", Toast.LENGTH_SHORT).show();
                } else if (g4 == null) {
                    opnosupport("4G data");
                } else if (shrt == "sprnt" || shrt == "gff") {
                    smsg(g4);
                } else if (shrt == "vodait") {
                    net(g4);
                } else {
                    sphn(g4);
                }
            }
        });

        RelativeLayout dnds = (RelativeLayout) rootView.findViewById(R.id.dndl);
        dnds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAirplaneModeOn(getActivity().getApplicationContext())) {
                    airplane();
                } else if (phn == null) {
                    nosupport();
                } else if (dnd == null) {
                    opnosupport("DND");
                } else {
                    sphn(dnd);
                }
            }
        });
    }

    private void nosupport() {
        Toast.makeText(getActivity(), "We do not support this operator currently", Toast.LENGTH_SHORT).show();
    }

    private void opnosupport(String s) {
        Toast.makeText(getActivity(), "This operator does not support " + s, Toast.LENGTH_SHORT).show();
    }

    private void airplane() {
        Toast.makeText(getActivity(), "Kindly turn off airplane mode", Toast.LENGTH_SHORT).show();
    }

    private void smsg(String num) {
        if (smsCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity().getApplicationContext(), "SMS permissions not provided.", Toast.LENGTH_LONG).show();
            permissionCheck(1);
        } else {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(num, null, txt, null, null);
                Toast.makeText(getActivity(), "SMS for data usage sent", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "SMS sending failed", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    private void sphn(String phne) {
        if (phoneCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity().getApplicationContext(), "Telephone permissions not provided.", Toast.LENGTH_LONG).show();
            permissionCheck(0);
        } else {
            Uri uri = Uri.parse("tel:" + Uri.encode(phne));
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            startActivity(intent);
        }
    }

    private void net(String gx) {
        Uri uri = Uri.parse(gx);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void getnetwork() {
        phn = g2 = g3 = g4 = dnd = null;
        TelephonyManager manager = (TelephonyManager) getActivity().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        carrier = manager.getNetworkOperatorName();
        country = manager.getNetworkCountryIso();
        carrier1 = carrier.toUpperCase();
        country = country.toUpperCase();
    }

    private static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    private void permissionCheck(int check) {
        switch (check) {
            case 0: {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE);
                break;
            }
            case 1: {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);
                break;
            }
        }
    }
}