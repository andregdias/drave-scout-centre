package pt.escutismo.dravescoutcentre.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.escutismo.dravescoutcentre.R;

public class RulesFragment extends android.support.v4.app.Fragment {

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_rules, container, false);
  }
}
