package yapp.com.railrotalk.network;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import yapp.com.railrotalk.dto.GetUserCheck;
import yapp.com.railrotalk.dto.SetKakaoInfoItem;

/**
 * Created by HunJin on 2017-02-25.
 */

public interface RestAPI {

    @FormUrlEncoded
    @POST("/users/up")
    Observable<SetKakaoInfoItem> setItem(@FieldMap Map<String, String> item);

    @GET("/users/duplication")
    Observable<GetUserCheck> getCheck(@Query("kakao_id") String kakao_id);

}
