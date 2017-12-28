package pt.escutismo.dravescoutcentre.activity;


import android.app.Application;

import io.realm.Realm;
import pt.escutismo.dravescoutcentre.model.Settings;

public class MyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
    Realm.setDefaultConfiguration(Settings.config);
  }
}