package pt.escutismo.dravescoutcentre.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pt.escutismo.dravescoutcentre.R;


public class ProgramsFragment extends android.support.v4.app.Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragView = inflater.inflate(R.layout.fragment_programs, container, false);
    setHasOptionsMenu(true);

    Button b1 = fragView.findViewById(R.id.button1);
    b1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FootprintFragment nextFrag = new FootprintFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
            android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, nextFrag, "FOOTPRINT");
        fragmentTransaction.commitAllowingStateLoss();
      }
    });
    Button b2 = fragView.findViewById(R.id.button2);
    b2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.stardroid");
        if (launchIntent != null) {
          startActivity(launchIntent);
        } else {
          Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.stardroid"));
          startActivity(viewIntent);
        }
      }
    });
    Button b3 = fragView.findViewById(R.id.button3);
    b3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getContext(), "Em Breve", Toast.LENGTH_SHORT).show();
      }
    });
    Button b4 = fragView.findViewById(R.id.button4);
    b4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getContext(), "Em Breve", Toast.LENGTH_SHORT).show();
      }
    });
    Button b5 = fragView.findViewById(R.id.button5);
    b5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getContext(), "Em Breve", Toast.LENGTH_SHORT).show();
      }
    });
    Button b6 = fragView.findViewById(R.id.button6);
    b6.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getContext(), "Em Breve", Toast.LENGTH_SHORT).show();
      }
    });

    return fragView;
  }
}
