package yapp.com.railrotalk.activities.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import yapp.com.railrotalk.MainActivity;
import yapp.com.railrotalk.R;
import yapp.com.railrotalk.activities.chatting.UserDetailActivity;
import yapp.com.railrotalk.activities.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 화면이 보이고 2초 뒤에 로그인 페이지로 넘어감
        Thread mTimer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent mIntro = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mIntro);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
