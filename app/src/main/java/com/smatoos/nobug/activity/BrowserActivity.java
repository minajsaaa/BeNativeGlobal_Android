package com.smatoos.nobug.activity;

import android.view.MenuItem;
import android.webkit.WebView;

import com.smatoos.b2b.R;
import com.smatoos.nobug.constant.property.BaseIntentExtryProperty;
import com.smatoos.nobug.webview.Browser;
import com.smatoos.nobug.webview.IBrowserClientEvent;

public class BrowserActivity extends PendingActivity implements IBrowserClientEvent {

    protected Browser browser;
    protected String title;

    //  =========================================================================================

    @Override
    public void onBackPressed() {
        historyBack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home: {
                historyBack();
                return false;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    //  =========================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.activity_browser;
    }

    @Override
    public void createChildren() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        browser = (Browser) findViewById(R.id.browser);
        browser.setEventListener(this);
    }

    @Override
    public void setProperties() {
        if (getIntent() != null) {
            loadUrl(getIntent().getStringExtra(BaseIntentExtryProperty.URL));
            title = getIntent().getStringExtra(BaseIntentExtryProperty.TITLE);

            if (title != null) {
                getSupportActionBar().setTitle(title);
            }

        }
    }

    //  ========================================================================================

    @Override
    public void onReceivedTitle(WebView webview, String webviewTitle) {
        if (title == null) {
            getSupportActionBar().setTitle(webviewTitle);
        }
    }

    //  ========================================================================================

    protected void historyBack() {
        if (browser.canGoBack()) {
            browser.goBack();
            return;
        }

        super.onBackPressed();
    }

    private void loadUrl(String url) {
        if (url != null) {
            browser.loadUrl(url);
        }
    }

}
