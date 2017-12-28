package pt.escutismo.dravescoutcentre.activity.base;

import com.akexorcist.localizationactivity.LocalizationActivity;

import pt.escutismo.dravescoutcentre.model.Settings;
import pt.escutismo.dravescoutcentre.utils.Utils;

public class DefaultActivity extends LocalizationActivity {

  @Override
  public void onStart() {
    super.onStart();
    Utils.visibleActivites++;
  }

  @Override
  public void onStop() {
    super.onStop();

    if (Utils.visibleActivites > 0)
      Utils.visibleActivites--;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    if (Utils.visibleActivites == 0) {
      // APP KILLED
      Settings.stopSettings();
    }
  }
}
