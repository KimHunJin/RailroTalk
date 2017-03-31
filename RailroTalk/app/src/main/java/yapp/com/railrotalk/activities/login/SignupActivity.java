package yapp.com.railrotalk.activities.login;

import android.os.Bundle;
import android.util.Log;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Subscription;
import yapp.com.railrotalk.activities.BaseActivity;
import yapp.com.railrotalk.network.NetworkRequest;
import yapp.com.railrotalk.network.RestAPI;
import yapp.com.railrotalk.network.RestAPIBuilder;

/**
 * Created by HunJin on 2017-02-24.
 * <p>
 * signup using kakao
 */
public class SignupActivity extends BaseActivity {

    String TAG = "SignupActivity";
    private Subscription mSubscription;

    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     *
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "before requestMe");
        requestMe();
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e(TAG, "Failed");
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    Log.e(TAG, ErrorCode.CLIENT_ERROR_CODE + "");
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e(TAG, "on session closed");
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.e(TAG, "Set Profile");
//                setProfile(userProfile.getId(), userProfile.getNickname(), userProfile.getThumbnailImagePath());
//                redirectSignupActivity();

                redirectMemberSettingActivity();
            }

            @Override
            public void onNotSignedUp() {
                Log.e(TAG, "not singed up");
            }
        });
    }


    void setProfile(long id, String nickname, String image) {
        Map<String, String> map = new HashMap();
        map.put("id",id+"");
        map.put("name",nickname);
        map.put("image",image);
        RestAPI restAPI = RestAPIBuilder.buildRetrofirServiceNode();
        mSubscription = NetworkRequest.performAsyncRequest(
                restAPI.setUser(map), (data)->{
                    redirectMainActivity();
        }, (error) -> {

        });


    }
}