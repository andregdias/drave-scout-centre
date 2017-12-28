package pt.escutismo.dravescoutcentre.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import pt.escutismo.dravescoutcentre.activity.base.DefaultActivity;

public class QRCodeCaptureActivity extends DefaultActivity {
  private CaptureManager capture;
  private DecoratedBarcodeView barcodeScannerView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    barcodeScannerView = initializeContent();

    capture = new CaptureManager(this, barcodeScannerView);
    capture.initializeFromIntent(getIntent(), savedInstanceState);
    capture.decode();
  }

  /**
   * Override to use a different layout.
   *
   * @return the DecoratedBarcodeView
   */
  private DecoratedBarcodeView initializeContent() {
    setContentView(com.google.zxing.client.android.R.layout.zxing_capture);
    return (DecoratedBarcodeView) findViewById(com.google.zxing.client.android.R.id.zxing_barcode_scanner);
  }

  @Override
  public void onResume() {
    super.onResume();
    capture.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    capture.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    capture.onDestroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    capture.onSaveInstanceState(outState);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
    capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
  }

  @Override
  public void onStop() {
    super.onStop();
    finish();
  }
}
