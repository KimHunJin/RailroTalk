package yapp.com.railrotalk.activities.chatting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yapp.com.railrotalk.R;
import yapp.com.railrotalk.dto.ChatData;

public class ChattingActivity extends AppCompatActivity {

    private TextView webViewFrame;
    private EditText edtSendMessage;
    private TextView txtBtnSend;

    private String mUserName = "";

    private String roomName = "wonho";


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        webViewSetting();
        initialize();

        Intent it = getIntent();


        mUserName = it.getExtras().getString("id");

        clickEvent();

        databaseReference.child(roomName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                webViewFrame.setText(webViewFrame.getText().toString()+chatData.getUserName()+" : " + chatData.getMessage()+"\n");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    


    void clickEvent() {
        txtBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData(mUserName, edtSendMessage.getText().toString().trim());
                databaseReference.child(roomName).push().setValue(chatData);
                edtSendMessage.setText("");
            }
        });
    }

    void initialize() {
        edtSendMessage = (EditText)findViewById(R.id.edt_chatting_send_message);
        txtBtnSend = (TextView)findViewById(R.id.txt_chatting_send_button);
    }

    void webViewSetting() {
        webViewFrame = (TextView)findViewById(R.id.wv_chatting_frame);
//        webViewFrame.setWebViewClient(new WebViewClient());
//        webViewFrame.clearCache(true);
//        webViewFrame.clearHistory();
//        webViewFrame.getSettings().setJavaScriptEnabled(true);
//        webViewFrame.getSettings().setAllowContentAccess(true);
//        webViewFrame.getSettings().setBlockNetworkLoads(false);
//        webViewFrame.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webViewFrame.getSettings().setAppCacheEnabled(false);
//        webViewFrame.loadUrl(URL.WEB_VIEW_URL);
    }
}
