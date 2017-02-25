package yapp.com.railrotalk.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import yapp.com.railrotalk.MainActivity;
import yapp.com.railrotalk.activities.login.LoginActivity;
import yapp.com.railrotalk.activities.login.MemberSettingActivity;
import yapp.com.railrotalk.activities.login.SignupActivity;
import yapp.com.railrotalk.utils.GlobalApplication;

/**
 * Created by HunJin on 2017-02-24.
 */

public class BaseActivity extends AppCompatActivity {
    protected static Activity self;

    private static final String TAG = "BaseActivity";

    @Override
    protected void onResume() {
        super.onResume();
        GlobalApplication.setCurrentActivity(this);
        self = BaseActivity.this;
    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences() {
        Activity currActivity = GlobalApplication.getCurrentActivity();
        if (currActivity != null && currActivity.equals(this)) {
            GlobalApplication.setCurrentActivity(null);
        }
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, SignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    protected void redirectMainActivity() {
        final Intent intent = new Intent(this, MemberSettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    /**
     * 카카오 회원 탈퇴
     */
    protected void unLink() {
        UserManagement.requestUnlink(new UnLinkResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(Long result) {
                Log.e(TAG, result + "");
                redirectLoginActivity();
            }
        });
    }

    protected void logout() {
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                redirectLoginActivity();
            }
        });
    }
}
