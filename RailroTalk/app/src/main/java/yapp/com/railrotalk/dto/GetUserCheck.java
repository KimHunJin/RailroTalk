package yapp.com.railrotalk.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HunJin on 2017-02-25.
 */

public class GetUserCheck {
    @SerializedName("result")
    String resutl;

    public String getResutl() {
        return resutl;
    }
}
