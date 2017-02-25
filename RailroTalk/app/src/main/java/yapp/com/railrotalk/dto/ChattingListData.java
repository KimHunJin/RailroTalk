package yapp.com.railrotalk.dto;

/**
 * Created by HunJin on 2017-02-25.
 */

public class ChattingListData {
    private int mNumber;
    private String mRoomName;
    private String mRoomImageURL;

    public ChattingListData(int number, String roomName, String roomImageURL) {
        this.mNumber = number;
        this.mRoomName = roomName;
        this.mRoomImageURL = roomImageURL;
    }

    public int getmNumber() {
        return mNumber;
    }

    public String getmRoomName() {
        return mRoomName;
    }

    public String getmRoomImageURL() {
        return mRoomImageURL;
    }
}
