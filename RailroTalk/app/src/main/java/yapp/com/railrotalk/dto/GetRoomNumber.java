package yapp.com.railrotalk.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HunJin on 2017-03-31.
 */

public class GetRoomNumber {
    @SerializedName("err")
    String err;

    @SerializedName("room")
    String room;

    public String getErr() {
        return err;
    }

    public String getRoom() {
        return room;
    }
}
