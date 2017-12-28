package pt.escutismo.dravescoutcentre.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import pt.escutismo.dravescoutcentre.R;
import pt.escutismo.dravescoutcentre.model.Settings;

public class SplashScreenActivity extends AppCompatActivity {

  private static final int SPLASH_TIME_OUT = 2000;

  private void setStatusBarTranslucent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    setStatusBarTranslucent();

    if (Settings.noSettings())
      Settings.createSettings();
    else
      Settings.startSettings();

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
      }
    }, SPLASH_TIME_OUT);
  }
}
