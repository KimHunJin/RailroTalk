package yapp.com.railrotalk.activities.chatting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import yapp.com.railrotalk.R;

public class MakingChattingActivity extends AppCompatActivity {

    private Subscription mSubscription;

    private static final String TAG = "MakingChattingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_chatting);
    }
}
