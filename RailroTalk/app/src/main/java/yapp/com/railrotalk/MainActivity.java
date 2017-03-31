package yapp.com.railrotalk;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import rx.Subscription;
import yapp.com.railrotalk.activities.chatting.ChattingActivity;
import yapp.com.railrotalk.activities.chatting.MakingChattingActivity;
import yapp.com.railrotalk.adapters.MainChattingListRecyclerViewAdapter;
import yapp.com.railrotalk.dto.ChattingListData;
import yapp.com.railrotalk.network.NetworkRequest;
import yapp.com.railrotalk.network.RestAPI;
import yapp.com.railrotalk.network.RestAPIBuilder;

/**
 * Created by HunJin on 2017-02-24.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Subscription mSubscription;

    private RecyclerView rcvMainChattingList;
    private MainChattingListRecyclerViewAdapter mainChattingListRecyclerViewAdapter;
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;
    private FloatingActionButton fbtMainMakingChatting;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    String name;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    void initialize() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(UserProfile result) {
                name = result.getNickname();
                id = result.getId() + "";
                checkRoom();
            }
        });
    }

    void checkRoom() {
        RestAPI getRoom = RestAPIBuilder.buildRetrofirServiceNode();

        mSubscription = NetworkRequest.performAsyncRequest(
                getRoom.getRoom(Integer.parseInt(id)
                ), (data) -> {
                    String err = data.getErr();
                    if (err.equals("404")) {
                        Intent it = new Intent(getApplicationContext(), MakingChattingActivity.class);
                        it.putExtra("id", id);
                        it.putExtra("name", name);
                        startActivity(it);
                        finish();
                    } else {
                        String number = data.getRoom();
                        Intent it = new Intent(getApplicationContext(), ChattingActivity.class);
                        String param =
                                "{" +
                                        "\"train_room_num\":" + number + "," +
                                        "\"user_list\":[" +
                                        "{" +
                                        "\"kakao_id\":\"dlwoen1\"," +
                                        "\"nickname\":\"haha\"" +
                                        "}," +
                                        "{" +
                                        "\"kakao_id\":\"" + id + "\"," +
                                        "\"nickname\":\"" + name + "\"" +
                                        "}" +
                                        "]" +
                                        "}";
                        it.putExtra("name",name);
                        it.putExtra("id",id);
                        it.putExtra("param", param);
                        startActivity(it);
                        finish();
                    }
//            Toast.makeText(getApplicationContext(), "저장했습니다.", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
                }, (error) -> {
                    Log.e(TAG, "error : " + error.getMessage() + "");
                });
    }
}
