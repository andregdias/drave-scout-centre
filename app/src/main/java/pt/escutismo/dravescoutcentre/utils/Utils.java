package pt.escutismo.dravescoutcentre.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.escutismo.dravescoutcentre.activity.QRCodeCaptureActivity;

public class Utils {
  public static int visibleActivites = 0;

  public static boolean isOnline(final Context context) {
    final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    final NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return (netInfo != null && netInfo.isConnectedOrConnecting());
  }

  public static String decodeQRCode(Bitmap qrCode) {
    try {
      int[] pixelsArray = new int[qrCode.getWidth() * qrCode.getHeight()];
      qrCode.getPixels(pixelsArray, 0, qrCode.getWidth(), 0, 0, qrCode.getWidth(), qrCode.getHeight());

      RGBLuminanceSource source = new RGBLuminanceSource(qrCode.getWidth(), qrCode.getHeight(), pixelsArray);
      BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

      Reader reader = new MultiFormatReader();
      Result result = reader.decode(binaryBitmap);
      return result.getText();
    } catch (NotFoundException | ChecksumException | FormatException | NullPointerException e) {
      e.printStackTrace();
    }

    return "";
  }

  public static void startQRCodeScanning(Activity _activity) {
    startQRCodeScanning(_activity, null);
  }

  public static void startQRCodeScanning(Activity _activity, Fragment _fragment) {
    if (_fragment != null) {
      startQRCodeScanning(IntentIntegrator.forSupportFragment(_fragment));
    } else {
      startQRCodeScanning(new IntentIntegrator(_activity));
    }
  }

  private static void startQRCodeScanning(IntentIntegrator integrator) {
    if (integrator != null) {
      integrator.setCaptureActivity(QRCodeCaptureActivity.class);
      integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
      integrator.setPrompt("");
      integrator.setCameraId(0);  // Use a specific camera of the device
      integrator.setBeepEnabled(true);
      integrator.setBarcodeImageEnabled(true);
      integrator.initiateScan();
    }
  }

  public static String getBasicDate(long epoch) {
    return new java.text.SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new java.util.Date(epoch));
  }

  public static String getBasicDateWithClock(long epoch) {
    return new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(new java.util.Date(epoch));
  }

  public static JsonArray concatJSONArray(JsonArray... arrs) throws JsonIOException {
    JsonArray result = new JsonArray();
    for (JsonArray arr : arrs) {
      for (int i = 0; i < arr.size(); i++) {
        result.add(arr.get(i));
      }
    }
    return result;
  }

  public static String convertCoordinatesToAPIFormat(double latitude, double longitude) {
    String coordinates;
    if (latitude == 0 && longitude == 0)
      coordinates = null;
    else
      coordinates = latitude + "," + longitude;

    return coordinates;
  }

  public static String convertServerStringToPattern(String income) {
    String outcome = income.substring(1, income.length() - 1).replace(".", "\\.");
    Pattern p = Pattern.compile("%(\\w+|\\{.*?\\})", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(outcome);

    outcome = m.replaceAll("(.*?)");

    return outcome;
  }
}
