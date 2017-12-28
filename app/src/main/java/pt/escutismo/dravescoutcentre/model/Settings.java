package pt.escutismo.dravescoutcentre.model;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Settings extends RealmObject {

  public static final RealmConfiguration config = new RealmConfiguration.Builder()
      .name("drave.realm")
      .deleteRealmIfMigrationNeeded()
      .schemaVersion(1)
      .build();

  private static RealmHelper realmHelper;

  @PrimaryKey
  private long id;
  private String language;

  public Settings() {
    this.id = 1;
    this.language = null;
  }

  public static void startSettings() {
    if (realmHelper != null)
      realmHelper.closeRealmInstance();

    realmHelper = new RealmHelper();
    realmHelper.startRealmInstance();
  }

  public static void stopSettings() {
    if (realmHelper != null)
      realmHelper.closeRealmInstance();
  }

  public static void createSettings() {
    final Settings s = new Settings();

    Realm realm = null;
    try {
      realm = Realm.getDefaultInstance();
      realm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
          realm.copyToRealmOrUpdate(s);
        }
      });
    } finally {
      startSettings();
      if (realm != null && !realm.isClosed()) {
        realm.close();
      }
    }
  }

  public static boolean noSettings() {
    boolean noSettings = true;

    Realm realm = null;
    try {
      realm = Realm.getDefaultInstance();
      realm.beginTransaction();

      Settings s = realm.where(Settings.class).equalTo("id", 1).findFirst();
      if (s != null) {
        noSettings = false;
      }

      realm.cancelTransaction();
    } finally {
      if (realm != null) {
        if (realm.isInTransaction())
          realm.cancelTransaction();

        realm.close();
      }
    }

    return noSettings;
  }

  public static String getLanguage() {
    String language = null;

    Realm realm = null;
    try {
      realm = Realm.getDefaultInstance();
      realm.beginTransaction();

      Settings s = realm.where(Settings.class).equalTo("id", 1).findFirst();
      if (s != null) {
        language = s.language;
      }

      realm.cancelTransaction();
    } finally {
      if (realm != null) {
        if (realm.isInTransaction())
          realm.cancelTransaction();

        realm.close();
      }
    }

    return language;
  }

  public static void setLanguage(final String language) {
    Realm realm = null;
    try {
      realm = Realm.getDefaultInstance();
      realm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
          Settings s = realm.where(Settings.class).equalTo("id", 1).findFirst();
          if (s != null) {
            s.language = language;
          }
        }
      });
    } finally {
      if (realm != null && !realm.isClosed()) {
        realm.close();
      }
    }
  }
}
