package yapp.com.railrotalk;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import yapp.com.railrotalk.activities.chatting.ChattingActivity;
import yapp.com.railrotalk.adapters.MainChattingListRecyclerViewAdapter;
import yapp.com.railrotalk.dto.ChattingListData;

/**
 * Created by HunJin on 2017-02-24.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvMainChattingList;
    private MainChattingListRecyclerViewAdapter mainChattingListRecyclerViewAdapter;
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;
    private FloatingActionButton fbtMainMakingChatting;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    void initialize() {
        rcvMainChattingList = (RecyclerView) findViewById(R.id.rcv_main_chatting_list);
        mainChattingListRecyclerViewAdapter = new MainChattingListRecyclerViewAdapter(getApplicationContext());
        rcvMainChattingList.setHasFixedSize(true);
        rcvMainChattingList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        fbtMainMakingChatting = (FloatingActionButton) findViewById(R.id.fab_main_chatting_making);
        addData();
        rcvMainChattingList.setAdapter(mainChattingListRecyclerViewAdapter);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(toggle);

        fbtMainMakingChatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.requestMe(new MeResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {

                    }

                    @Override
                    public void onNotSignedUp() {

                    }

                    @Override
                    public void onSuccess(UserProfile result) {
                        Intent it = new Intent(getApplicationContext(),ChattingActivity.class);
                        it.putExtra("id",result.getNickname());
                        startActivity(it);
                    }
                });
            }
        });

    }

    void addData() {

        mainChattingListRecyclerViewAdapter.addData(new ChattingListData(0, "방1", "asdfasdf", "afasdf", "fasdfa"));
        mainChattingListRecyclerViewAdapter.addData(new ChattingListData(1, "방2", "asdfasdf", "fdsaff", "fsadf"));
        mainChattingListRecyclerViewAdapter.addData(new ChattingListData(2, "방3", "asdfasdf", "fsadfasdf", "gdsfg"));

    }
}
