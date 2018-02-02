package pt.escutismo.dravescoutcentre.utils;

import java.util.HashMap;

public interface IGlobalConstants {

  //NAVIGATION DRAWER
  int HOME_MENU_INDEX = 0;
  int PROGRAMS_MENU_INDEX = 1;
  int TRACKS_MENU_INDEX = 2;
  int BADGE_MENU_INDEX = 3;
  int ABOUTDRAVE_MENU_INDEX = 4;
  int ABOUTSCOUTCENTRE_MENU_INDEX = 5;
  int RULES_MENU_INDEX = 6;
  int CONTACTS_MENU_INDEX = 7;

  HashMap<Integer, String> DRAWER_ITEMS = new HashMap<Integer, String>() {{
    put(HOME_MENU_INDEX, "home");
    put(PROGRAMS_MENU_INDEX, "programs");
    put(TRACKS_MENU_INDEX, "tracks");
    put(BADGE_MENU_INDEX, "badge");
    put(ABOUTDRAVE_MENU_INDEX, "aboutdrave");
    put(ABOUTSCOUTCENTRE_MENU_INDEX, "aboutscoutcentre");
    put(RULES_MENU_INDEX, "rules");
    put(CONTACTS_MENU_INDEX, "contacts");
  }};
}
