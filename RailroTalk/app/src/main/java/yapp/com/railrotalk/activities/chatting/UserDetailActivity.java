package yapp.com.railrotalk.activities.chatting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import jp.wasabeef.blurry.Blurry;
import yapp.com.railrotalk.R;

public class UserDetailActivity extends AppCompatActivity {

    ImageView imgUserDetailBlur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        imgUserDetailBlur = (ImageView) findViewById(R.id.img_user_detail_info_blur);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // TODO Auto-generated method stub
                Blurry.with(UserDetailActivity.this)
                        .radius(10)
                        .sampling(8)
                        .async()
                        .capture(findViewById(R.id.img_user_detail_info_blur))
                        .into((ImageView) findViewById(R.id.img_user_detail_info_blur));

            }
        }).start();

//        imgUserDetailBlur.bringToFront();
//        imgUserDetailThumbNail.bringToFront();

    }
}
