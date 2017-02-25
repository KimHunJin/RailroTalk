package yapp.com.railrotalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import yapp.com.railrotalk.adapters.MainChattingListRecyclerViewAdapter;
import yapp.com.railrotalk.dto.ChattingListData;

/**
 * Created by HunJin on 2017-02-24.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvMainChattingList;
    private MainChattingListRecyclerViewAdapter mainChattingListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    void initialize() {
        rcvMainChattingList = (RecyclerView)findViewById(R.id.rcv_main_chatting_list);
        mainChattingListRecyclerViewAdapter = new MainChattingListRecyclerViewAdapter(getApplicationContext());
        rcvMainChattingList.setHasFixedSize(true);
        rcvMainChattingList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addData();
        rcvMainChattingList.setAdapter(mainChattingListRecyclerViewAdapter);
    }

    void addData() {

        mainChattingListRecyclerViewAdapter.addData(new ChattingListData(0,"방1","asdfasdf"));
        mainChattingListRecyclerViewAdapter.addData(new ChattingListData(1,"방2","asdfasdf"));
        mainChattingListRecyclerViewAdapter.addData(new ChattingListData(2,"방3","asdfasdf"));

    }
}
