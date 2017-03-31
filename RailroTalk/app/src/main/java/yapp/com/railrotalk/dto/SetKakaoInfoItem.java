package yapp.com.railrotalk.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HunJin on 2017-02-25.
 */

public class SetKakaoInfoItem {
    @SerializedName("err")
    String err;

    public String getErr() {
        return err;
    }
}
