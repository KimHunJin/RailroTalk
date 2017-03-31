package yapp.com.railrotalk.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HunJin on 2017-03-30.
 */

public class GetRailoNumber {

    @SerializedName("err")
    String err;
    String number;

    public String getErr() {
        return err;
    }

    public String getNumber() {
        return number;
    }
}
