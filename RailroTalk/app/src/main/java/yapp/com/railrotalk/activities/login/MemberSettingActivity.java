package yapp.com.railrotalk.activities.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import yapp.com.railrotalk.MainActivity;
import yapp.com.railrotalk.R;
import yapp.com.railrotalk.dto.GetUserCheck;
import yapp.com.railrotalk.network.NetworkRequest;
import yapp.com.railrotalk.network.RestAPI;
import yapp.com.railrotalk.network.RestAPIBuilder;

public class MemberSettingActivity extends AppCompatActivity {

    private Subscription mSubscription;
    private static final String TAG = "MembeSettingActivity";
    String check = "false";
    Map userInfo = new HashMap<String, String>();

    private ToggleButton tgbSameFriend, tgbOtherFriend;
    private Switch switchGender;
    private View btnSave;
    private EditText edtMemberSettingAnyWord;

    private String myGender = "0";
    private String sameGender = "1";
    private String otherGender = "1";
    private String myMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getProfile();
    }

    /**
     * profile로부터 가져와 현재 아이디가 디비에 저장되있는지를 체크
     */
    void getProfile() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(UserProfile result) {
                getNetwor(result.getId() + "");
            }
        });
    }

    /**
     * 만약 신규 유저일경우 레이아웃을 초기 세팅
     * 기존 유저일 경우 채팅방 리스트로 넘어감
     *
     * @param uid
     */
    private void getNetwor(String uid) {
        RestAPI restAPI = RestAPIBuilder.buildRetrofitService();
        mSubscription = NetworkRequest.performAsyncRequest(restAPI.getCheck(uid), (data) -> {
            check = displayPost(data);
            if (check.equals("true")) {
                setContentView(R.layout.activity_member_setting);
                // TODO: 2017-02-25 layout setting and event
                initialize();
//                setKakaoInfo();
            } else {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, (error) -> {
            Log.e(TAG, error.getMessage());
        });
    }

    void initialize() {
        switchGender = (Switch) findViewById(R.id.switch_member_setting_gender);
        tgbOtherFriend = (ToggleButton) findViewById(R.id.tgb_member_setting_other_friend);
        tgbSameFriend = (ToggleButton) findViewById(R.id.tgb_member_setting_same_friend);
        btnSave = findViewById(R.id.btn_member_setting_save);
        edtMemberSettingAnyWord = (EditText) findViewById(R.id.edt_member_setting_any_word);

        clickEvent();
    }

    void clickEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKakaoInfo();
            }
        });

        tgbSameFriend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sameGender = "1";
                } else {
                    sameGender = "0";
                }
            }
        });

        tgbOtherFriend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    otherGender = "1";
                } else {
                    otherGender = "0";
                }
            }
        });

        switchGender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myGender = "1";
                } else {
                    myGender = "0";
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sameGender.equals("0") && otherGender.equals("0")) {
                    Toast.makeText(getApplicationContext(), "적어도 한 타입의 친구를 선택하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    myMessage = edtMemberSettingAnyWord.getText().toString();
                    setKakaoInfo();
                }
            }
        });
    }

    /**
     * true or false 값을 가져옴.
     * 유저가 있는지 없는지 확인하는 메서드
     *
     * @param data
     * @return
     */
    String displayPost(GetUserCheck data) {
        return data.getResutl();
    }

    /**
     * 유저 정보를 데이터베이스에 저장
     */
    void setKakaoInfo() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(UserProfile result) {
                Log.e(TAG, "setKakaoInfo onSuccess");
                userInfo.put("kakao_id", result.getId() + "");
                userInfo.put("nickname", result.getNickname() + "");
                userInfo.put("gender", myGender);
                userInfo.put("same_gender", sameGender);
                userInfo.put("other_gender", otherGender);
                userInfo.put("say", myMessage);
                setNetwork();
            }
        });
    }

    private void setNetwork() {
        RestAPI restAPI2 = RestAPIBuilder.buildRetrofitService();
        mSubscription = NetworkRequest.performAsyncRequest(restAPI2.setItem(userInfo), (data) -> {
            Log.e(TAG, "success");
            Toast.makeText(getApplicationContext(), "저장했습니다.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, (error) -> {
            Log.e(TAG, error.getMessage() + "");
        });
    }
}
