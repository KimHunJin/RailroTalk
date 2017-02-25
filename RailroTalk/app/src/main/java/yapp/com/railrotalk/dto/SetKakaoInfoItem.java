package yapp.com.railrotalk.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HunJin on 2017-02-25.
 */

public class SetKakaoInfoItem {
    @SerializedName("result")
    String result;

    public String getResult() {
        return result;
    }
}
