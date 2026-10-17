package com.moezdorovie.app;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.JavascriptInterface;
import android.content.Intent;
public class MainActivity extends Activity {
 @Override public void onCreate(Bundle state) {
  super.onCreate(state);
  WebView web = new WebView(this);
  web.getSettings().setJavaScriptEnabled(true);
  web.getSettings().setDomStorageEnabled(true);
  web.setWebViewClient(new WebViewClient());
  web.addJavascriptInterface(new Object() {
   @JavascriptInterface public void share(String content) {
    runOnUiThread(() -> {
     Intent send = new Intent(Intent.ACTION_SEND);
     send.setType("text/plain");
     send.putExtra(Intent.EXTRA_TEXT, content);
     startActivity(Intent.createChooser(send,"Отправить отчёт"));
    });
   }
  }, "AndroidBridge");
  setContentView(web);
  web.loadUrl("file:///android_asset/index.html");
 }
}