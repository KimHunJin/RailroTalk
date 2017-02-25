package yapp.com.railrotalk.dto;

/**
 * Created by HunJin on 2017-02-25.
 */

public class ChattingListData {
    private int mNumber;
    private String mRoomName;
    private String mRoomImageURL;
    private String mRoomRecentlyChat;
    private String mRoomDate;

    public ChattingListData(int number, String roomName, String roomImageURL, String roomRecentlyChat, String roomDate) {
        this.mNumber = number;
        this.mRoomName = roomName;
        this.mRoomImageURL = roomImageURL;
        this.mRoomRecentlyChat = roomRecentlyChat;
        this.mRoomDate = roomDate;
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

    public String getmRoomRecentlyChat() {
        return mRoomRecentlyChat;
    }

    public String getmRoomDate() {
        return mRoomDate;
    }
}
