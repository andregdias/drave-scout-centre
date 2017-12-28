package pt.escutismo.dravescoutcentre.model;

import android.util.Log;

import io.realm.Realm;

class RealmHelper {

  private Realm realm = null;

  void startRealmInstance() {
    try {
      this.realm = Realm.getDefaultInstance();
    } catch (Exception e) {
      Log.e("RealHelper", "FAILED TO GET REALM DEFAULT INSTANCE");
    }
  }

  protected Realm getRealmInstance() {
    return this.realm;
  }

  void closeRealmInstance() {
    try {
      if (this.realm != null)
        this.realm.close();
    } catch (Exception e) {
      Log.e("RealHelper", "FAILED TO CLOSE REALM INSTANCE");
    }

  }

}
