package yapp.com.railrotalk.activities.chatting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import yapp.com.railrotalk.R;
import yapp.com.railrotalk.network.NetworkRequest;
import yapp.com.railrotalk.network.RestAPI;
import yapp.com.railrotalk.network.RestAPIBuilder;
import yapp.com.railrotalk.utils.URL;

public class ChattingActivity extends AppCompatActivity {

    private WebView webViewFrame;
    private Toolbar mToolbar;
//    private EditText edtSendMessage;
//    private TextView txtBtnSend;


    private static final String TAG = "ChattingActivity";

    private String id, name;
    String script;
    private Subscription mSubscription;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();
        id = it.getExtras().getString("id");
        name = it.getExtras().getString("name");
        String param = it.getExtras().getString("param");

        Log.e(TAG, param);

        script = "javascript:start('" + param + "')";

        initialize();
        webViewSetting();
    }

    void initialize() {
        progressBar = (ProgressBar) findViewById(R.id.pgb_web_loading);
        webViewFrame = (WebView) findViewById(R.id.wv_chatting_frame);
    }

    void webViewSetting() {

        webViewFrame.clearCache(true);
        webViewFrame.clearHistory();
        webViewFrame.getSettings().setJavaScriptEnabled(true);
        webViewFrame.getSettings().setAllowContentAccess(true);
        webViewFrame.getSettings().setBlockNetworkLoads(false);
        webViewFrame.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewFrame.getSettings().setAppCacheEnabled(false);
        webViewFrame.getSettings().setLoadWithOverviewMode(true);
        webViewFrame.loadUrl(URL.WEB_VIEW_URL_CHAT);
        webViewFrame.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setVisibilityProgress(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setVisibilityProgress(false);
                view.loadUrl(script);
            }
        });

    }

    void setVisibilityProgress(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                RestAPI restAPI = RestAPIBuilder.buildRetrofirServiceNode();
                Map<String, String> map = new HashMap();
                map.put("id", id + "");
                mSubscription = NetworkRequest.performAsyncRequest(restAPI.deleteRoom(map), (data) -> {
                    Intent it = new Intent(ChattingActivity.this, MakingChattingActivity.class);
                    it.putExtra("id", id);
                    it.putExtra("name", name);
                    startActivity(it);
                    finish();
                }, (error) -> {
                    Log.e(TAG, "error : " + error);
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
