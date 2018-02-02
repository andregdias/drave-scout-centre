package pt.escutismo.dravescoutcentre.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import java.util.List;

import pt.escutismo.dravescoutcentre.R;
import pt.escutismo.dravescoutcentre.activity.base.DefaultActivity;
import pt.escutismo.dravescoutcentre.fragment.AboutDraveFragment;
import pt.escutismo.dravescoutcentre.fragment.AboutScoutCentreFragment;
import pt.escutismo.dravescoutcentre.fragment.BadgeFragment;
import pt.escutismo.dravescoutcentre.fragment.ContactsFragment;
import pt.escutismo.dravescoutcentre.fragment.HomeFragment;
import pt.escutismo.dravescoutcentre.fragment.ProgramsFragment;
import pt.escutismo.dravescoutcentre.fragment.RulesFragment;
import pt.escutismo.dravescoutcentre.fragment.TracksFragment;
import pt.escutismo.dravescoutcentre.utils.IGlobalConstants;
import pt.escutismo.dravescoutcentre.utils.TypefaceSpan;

public class MainActivity extends DefaultActivity {

  private static int navItemIndex;
  private static String CURRENT_TAG;
  private Toolbar toolbar;
  private NavigationView navigationView;
  private DrawerLayout drawer;
  private Fragment fragment;
  private String[] activityTitles;
  private Handler mHandler;
  private AppUpdater updater;
  private DefaultActivity context;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mHandler = new Handler();

    context = this;

    updater = new AppUpdater(this)
        .setUpdateFrom(UpdateFrom.GOOGLE_PLAY)
        .setButtonDoNotShowAgain("")
        .setButtonDismiss("");
    updater.start();

    drawer = findViewById(R.id.drawer_layout);
    navigationView = findViewById(R.id.nav_view);

    setUpNavigationView();

    if (savedInstanceState == null) {
      CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
      loadHomeFragment();
    }
  }

  private void loadHomeFragment() {
    selectNavMenu();
    setToolbarTitle();
    setToolbarColors(ContextCompat.getColor(this, R.color.colorPrimaryDark));

    // if user select the current navigation menu again, don't do anything
    // just close the navigation drawer
    if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
      drawer.closeDrawers();
      return;
    }

    Runnable mPendingRunnable = new Runnable() {
      @Override
      public void run() {
        fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
            android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
      }
    };

    mHandler.post(mPendingRunnable);

    drawer.closeDrawers();

    invalidateOptionsMenu();
  }

  private Fragment getHomeFragment() {
    switch (navItemIndex) {
      case IGlobalConstants.HOME_MENU_INDEX:
        return new HomeFragment();
      case IGlobalConstants.PROGRAMS_MENU_INDEX:
        return new ProgramsFragment();
      case IGlobalConstants.TRACKS_MENU_INDEX:
        return new TracksFragment();
      case IGlobalConstants.BADGE_MENU_INDEX:
        return new BadgeFragment();
      case IGlobalConstants.ABOUTDRAVE_MENU_INDEX:
        return new AboutDraveFragment();
      case IGlobalConstants.ABOUTSCOUTCENTRE_MENU_INDEX:
        return new AboutScoutCentreFragment();
      case IGlobalConstants.RULES_MENU_INDEX:
        return new RulesFragment();
      case IGlobalConstants.CONTACTS_MENU_INDEX:
        return new ContactsFragment();
      default:
        return new HomeFragment();
    }
  }

  private void setToolbarTitle() {
    if (getSupportActionBar() != null) {
      SpannableString s = new SpannableString(activityTitles[navItemIndex]);
      s.setSpan(new TypefaceSpan(this, "pfagoraslabpro-regular.ttf"), 0, s.length(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

      getSupportActionBar().setTitle(s);
    }
  }

  private void setToolbarColors(int colorDark) {
    if (getSupportActionBar() != null)
      getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.header_appbar));
    setStatusBarColor(colorDark);
  }

  private void setStatusBarColor(int color) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window w = getWindow();
      w.setStatusBarColor(color);
    }
  }

  private void selectNavMenu() {
    navigationView.getMenu().getItem(navItemIndex).setChecked(true);
  }

  private void setUpNavigationView() {
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

      // This method will trigger on item Click of navigation menu
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
          case R.id.nav_home:
            navItemIndex = IGlobalConstants.HOME_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
          case R.id.nav_programs:
            navItemIndex = IGlobalConstants.PROGRAMS_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
          case R.id.nav_tracks:
            navItemIndex = IGlobalConstants.TRACKS_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
          case R.id.nav_badge:
            navItemIndex = IGlobalConstants.BADGE_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
          case R.id.nav_aboutDrave:
            navItemIndex = IGlobalConstants.ABOUTDRAVE_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
          case R.id.nav_aboutScoutCentre:
            navItemIndex = IGlobalConstants.ABOUTSCOUTCENTRE_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
          case R.id.nav_rules:
            navItemIndex = IGlobalConstants.RULES_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
          case R.id.nav_contacts:
            navItemIndex = IGlobalConstants.CONTACTS_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
          default:
            navItemIndex = IGlobalConstants.HOME_MENU_INDEX;
            CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
            break;
        }

        loadHomeFragment();

        return true;
      }
    });

    ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);

        View view = context.getCurrentFocus();
        if (view != null) {
          InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
          imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
      }
    };

    drawer.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawers();
      return;
    } else {
      if (navItemIndex != IGlobalConstants.HOME_MENU_INDEX) {
        navItemIndex = IGlobalConstants.HOME_MENU_INDEX;
        CURRENT_TAG = IGlobalConstants.DRAWER_ITEMS.get(navItemIndex);
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
        loadHomeFragment();
        return;
      }
    }
    super.onBackPressed();
  }

  @Override
  public void onPause() {
    super.onPause();
    updater.stop();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    List<Fragment> fragments = getSupportFragmentManager().getFragments();
    if (fragments != null) {
      for (Fragment fragment : fragments) {
        if (fragment != null && requestCode == (short) fragment.getId()) {
          fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
          break;
        }
      }
    }
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  }
}
