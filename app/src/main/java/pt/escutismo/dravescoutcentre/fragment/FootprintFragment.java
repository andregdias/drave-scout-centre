package pt.escutismo.dravescoutcentre.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import pt.escutismo.dravescoutcentre.R;
import pt.escutismo.dravescoutcentre.activity.MainActivity;
import pt.escutismo.dravescoutcentre.utils.TypefaceSpan;


public class FootprintFragment extends android.support.v4.app.Fragment {

  EditText p1, p2, p3, p4, p5, p6, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20;
  Switch p7;
  TextView total, totalArvores;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragView = inflater.inflate(R.layout.fragment_footprint, container, false);

    if (((MainActivity) getActivity()).getSupportActionBar() != null) {
      SpannableString s = new SpannableString("Pegada Ecológica");
      s.setSpan(new TypefaceSpan(getContext(), "pfagoraslabpro-regular.ttf"), 0, s.length(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

      ((MainActivity) getActivity()).getSupportActionBar().setTitle(s);
    }

    p1 = fragView.findViewById(R.id.p1);
    p2 = fragView.findViewById(R.id.p2);
    p3 = fragView.findViewById(R.id.p3);
    p4 = fragView.findViewById(R.id.p4);
    p5 = fragView.findViewById(R.id.p5);
    p6 = fragView.findViewById(R.id.p6);
    p7 = fragView.findViewById(R.id.p7);
    p8 = fragView.findViewById(R.id.p8);
    p9 = fragView.findViewById(R.id.p9);
    p10 = fragView.findViewById(R.id.p10);
    p11 = fragView.findViewById(R.id.p11);
    p12 = fragView.findViewById(R.id.p12);
    p13 = fragView.findViewById(R.id.p13);
    p14 = fragView.findViewById(R.id.p14);
    p15 = fragView.findViewById(R.id.p15);
    p16 = fragView.findViewById(R.id.p16);
    p17 = fragView.findViewById(R.id.p17);
    p18 = fragView.findViewById(R.id.p18);
    p19 = fragView.findViewById(R.id.p19);
    p20 = fragView.findViewById(R.id.p20);

    total = fragView.findViewById(R.id.total);
    totalArvores = fragView.findViewById(R.id.totalArvores);

    TextWatcher textWatcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }

      @Override
      public void afterTextChanged(Editable editable) {
        calcTotal();
      }
    };

    p1.addTextChangedListener(textWatcher);
    p2.addTextChangedListener(textWatcher);
    p3.addTextChangedListener(textWatcher);
    p4.addTextChangedListener(textWatcher);
    p5.addTextChangedListener(textWatcher);
    p6.addTextChangedListener(textWatcher);
    p8.addTextChangedListener(textWatcher);
    p9.addTextChangedListener(textWatcher);
    p10.addTextChangedListener(textWatcher);
    p11.addTextChangedListener(textWatcher);
    p12.addTextChangedListener(textWatcher);
    p13.addTextChangedListener(textWatcher);
    p14.addTextChangedListener(textWatcher);
    p15.addTextChangedListener(textWatcher);
    p16.addTextChangedListener(textWatcher);
    p17.addTextChangedListener(textWatcher);
    p18.addTextChangedListener(textWatcher);
    p19.addTextChangedListener(textWatcher);
    p20.addTextChangedListener(textWatcher);

    p7.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calcTotal();
      }
    });

    calcTotal();

    return fragView;
  }

  private void calcTotal() {
    double numElem = p1.getText().toString().isEmpty() ? 0 : Double.parseDouble(p1.getText().toString());
    double numDias = p2.getText().toString().isEmpty() ? 0 : Double.parseDouble(p2.getText().toString());
    double dist = p3.getText().toString().isEmpty() ? 0 : Double.parseDouble(p3.getText().toString());
    double viat1 = p4.getText().toString().isEmpty() ? 0 : Double.parseDouble(p4.getText().toString());
    double viat2 = p5.getText().toString().isEmpty() ? 0 : Double.parseDouble(p5.getText().toString());
    double viat3 = p6.getText().toString().isEmpty() ? 0 : Double.parseDouble(p6.getText().toString());
    double transPub = p7.isChecked() ? 1 : 0;
    double gas = p8.getText().toString().isEmpty() ? 0 : Double.parseDouble(p8.getText().toString());
    double vaca = p9.getText().toString().isEmpty() ? 0 : Double.parseDouble(p9.getText().toString());
    double porco = p10.getText().toString().isEmpty() ? 0 : Double.parseDouble(p10.getText().toString());
    double aves = p11.getText().toString().isEmpty() ? 0 : Double.parseDouble(p11.getText().toString());
    double salsichas = p12.getText().toString().isEmpty() ? 0 : Double.parseDouble(p12.getText().toString());
    double fumadosEnchidos = p13.getText().toString().isEmpty() ? 0 : Double.parseDouble(p13.getText().toString());
    double manteiga = p14.getText().toString().isEmpty() ? 0 : Double.parseDouble(p14.getText().toString());
    double margarina = p15.getText().toString().isEmpty() ? 0 : Double.parseDouble(p15.getText().toString());
    double queijo = p16.getText().toString().isEmpty() ? 0 : Double.parseDouble(p16.getText().toString());
    double ovos = p17.getText().toString().isEmpty() ? 0 : Double.parseDouble(p17.getText().toString());
    double leite = p18.getText().toString().isEmpty() ? 0 : Double.parseDouble(p18.getText().toString());
    double fruta = p19.getText().toString().isEmpty() ? 0 : Double.parseDouble(p19.getText().toString());
    double pao = p20.getText().toString().isEmpty() ? 0 : Double.parseDouble(p20.getText().toString());

    double tot1 = dist * 2 * viat1 * 15 / 100;
    double tot2 = dist * 2 * viat2 * 30 / 100;
    double tot3 = dist * 2 * viat3 * 50 / 100;
    double tot4 = dist * 2 * transPub * numElem * 0.03;
    double tot5 = numElem * (numDias + 1);
    double tot6 = gas * 1.1;
    double tot7 = vaca * 13.3;
    double tot8 = porco * 3.25;
    double tot9 = aves * 3.5;
    double tot10 = salsichas * 8;
    double tot11 = fumadosEnchidos * 4.8;
    double tot12 = manteiga * 23.8;
    double tot13 = margarina * 1.35;
    double tot14 = queijo * 8.5;
    double tot15 = ovos * 1.95;
    double tot16 = leite * 0.95;
    double tot17 = fruta * 0.55;
    double tot18 = pao * 0.65;

    double tot = tot1 + tot2 + tot3 + tot4 + tot5 + tot6 + tot7 + tot8 + tot9 + tot10 + tot11 + tot12 + tot13 + tot14 + tot15 + tot16 + tot17 + tot18;

    total.setText(String.format("%.2f", tot));
    totalArvores.setText(String.format("%.0f", tot / 166));
  }
}
