package yapp.com.railrotalk.activities.chatting;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import yapp.com.railrotalk.R;
import yapp.com.railrotalk.network.NetworkRequest;
import yapp.com.railrotalk.network.RestAPI;
import yapp.com.railrotalk.network.RestAPIBuilder;

public class MakingChattingActivity extends AppCompatActivity {

    private static final String TAG = MakingChattingActivity.class.getSimpleName();

    private int year;
    private int month;
    private int day;

    private TextView txtRoomDate;
    private EditText edtRailoNumber;

    private Subscription mSubscription;

    String id;
    String name;
    String dateFormat = "";
    String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_chatting);

        Intent it = getIntent();
        id = it.getExtras().getString("id");
        name = it.getExtras().getString("name");

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        txtRoomDate = (TextView) findViewById(R.id.txt_make_start_date);
        edtRailoNumber = (EditText) findViewById(R.id.edt_make_railo_number);

    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_make_select_date: {
                new DatePickerDialog(MakingChattingActivity.this, dateSetListener, year, month, day).show();
                break;
            }
            case R.id.btn_make_room_finder: {
                isCheckRoom();
                break;
            }
        }
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String date = String.format("%d / %d / %d", year, month + 1, dayOfMonth);
            if ((month + 1) < 10) {
                dateFormat = "" + year + "0" + (month + 1);
            } else {
                dateFormat = "" + year + (month + 1);
            }

            if (dayOfMonth < 10) {
                dateFormat += "0" + dayOfMonth;
            } else {
                dateFormat += dayOfMonth;
            }

            txtRoomDate.setText(date);
        }
    };


    void isCheckRoom() {
        RestAPI getRoom = RestAPIBuilder.buildRetrofirServiceNode();
        mSubscription = NetworkRequest.performAsyncRequest(
                getRoom.getRailo(
                        Integer.parseInt(edtRailoNumber.getText().toString())
                ), (data) -> {

                    Log.e(TAG, "success");
                    Log.e(TAG, "number : " + data.getNumber());
                    Log.e(TAG, "err : " + data.getErr());
                    int number = Integer.parseInt(data.getNumber());
                    if (number > 0 && txtRoomDate.getText().toString().length() > 1) {
                        intent();
                    } else if (txtRoomDate.getText().toString().trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), "날짜를 선택하세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "열차번호가 잘못됐습니다.", Toast.LENGTH_SHORT).show();
                        edtRailoNumber.setText("");
                    }

//            Toast.makeText(getApplicationContext(), "저장했습니다.", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
                }, (error) -> {
                    Log.e(TAG, "error : " + error.getMessage() + "");
                });
    }

    void intent() {
        room = dateFormat + edtRailoNumber.getText().toString().trim();
        Map<String, String> map = new HashMap();
        map.put("id", id + "");
        map.put("room", room);
        RestAPI restAPI = RestAPIBuilder.buildRetrofirServiceNode();
        mSubscription = NetworkRequest.performAsyncRequest(
                restAPI.updateRoom(map), (data) -> {
                    nextPage();
                }, (error) -> {
                    Log.e(TAG, "error : " + error);
                });

    }

    void nextPage() {
        Log.e(TAG,"nextPage");
        Intent it = new Intent(MakingChattingActivity.this, ChattingActivity.class);
        String param =
                "{" +
                        "\"train_room_num\" : " + room + "," +
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

        it.putExtra("id", id+"");
        it.putExtra("name", name+"");
        it.putExtra("param", param);
        startActivity(it);
//        finish();
//        this.finish();
        MakingChattingActivity.this.finish();

    }
}
