package pt.escutismo.dravescoutcentre.utils;

import java.util.HashMap;

public interface IGlobalConstants {

  //NAVIGATION DRAWER
  int PROGRAMS_MENU_INDEX = 0;
  int ABOUTDRAVE_MENU_INDEX = 1;
  int ABOUTSCOUTCENTRE_MENU_INDEX = 2;
  int DRAVECARD_MENU_INDEX = 3;
  int RULES_MENU_INDEX = 4;
  int SOCIALNETWORKS_MENU_INDEX = 5;
  int NOTIFICATIONS_MENU_INDEX = 6;


  HashMap<Integer, String> DRAWER_ITEMS = new HashMap<Integer, String>() {{
    put(PROGRAMS_MENU_INDEX, "programs");
    put(ABOUTDRAVE_MENU_INDEX, "aboutdrave");
    put(ABOUTSCOUTCENTRE_MENU_INDEX, "aboutscoutcentre");
    put(DRAVECARD_MENU_INDEX, "dravecard");
    put(RULES_MENU_INDEX, "rules");
    put(SOCIALNETWORKS_MENU_INDEX, "socialnetworks");
    put(NOTIFICATIONS_MENU_INDEX, "notifications");
  }};
}
