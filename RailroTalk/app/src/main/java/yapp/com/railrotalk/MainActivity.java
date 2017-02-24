package yapp.com.railrotalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yapp.com.railrotalk.activities.chatting.ChattingActivity;

/**
 * Created by HunJin on 2017-02-24.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), ChattingActivity.class));
    }
}
