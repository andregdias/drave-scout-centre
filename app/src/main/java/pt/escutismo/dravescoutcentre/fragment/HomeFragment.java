package pt.escutismo.dravescoutcentre.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import pt.escutismo.dravescoutcentre.R;

public class HomeFragment extends android.support.v4.app.Fragment {
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragView = inflater.inflate(R.layout.fragment_home, container, false);

    ImageView banner1 = fragView.findViewById(R.id.banner1);
    ImageView banner2 = fragView.findViewById(R.id.banner2);
    ImageView banner3 = fragView.findViewById(R.id.banner3);

    Glide.with(this).load("http://drave.cne-escutismo.pt/app/app1.jpg").into(banner1);
    Glide.with(this).load("http://drave.cne-escutismo.pt/app/app2.jpg").into(banner2);
    Glide.with(this).load("http://drave.cne-escutismo.pt/app/app3.jpg").into(banner3);

    return fragView;
  }
}
