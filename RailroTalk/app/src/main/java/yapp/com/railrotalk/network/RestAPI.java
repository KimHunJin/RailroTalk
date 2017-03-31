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
import yapp.com.railrotalk.dto.GetRailoNumber;
import yapp.com.railrotalk.dto.GetRoomNumber;
import yapp.com.railrotalk.dto.GetUserCheck;
import yapp.com.railrotalk.dto.SetKakaoInfoItem;

/**
 * Created by HunJin on 2017-02-25.
 */

public interface RestAPI {

//    @FormUrlEncoded
//    @POST("/users/up")
//    Observable<SetKakaoInfoItem> setItem(@FieldMap Map<String, String> item);

    @GET("/railo/{number}")
    Observable<GetRailoNumber> getRailo(@Path("number") int number);

    @GET("/room/{number}")
    Observable<GetRoomNumber> getRoom(@Path("number") int id);

    @GET("/user/{number}")
    Observable<GetUserCheck> getCheck(@Path("number") String number);

    @FormUrlEncoded
    @POST("/insert/")
    Observable<SetKakaoInfoItem> setUser(@FieldMap Map<String, String> item);

    @FormUrlEncoded
    @POST("/update")
    Observable<GetUserCheck> updateRoom(@FieldMap Map<String, String> item);

    @FormUrlEncoded
    @POST("/update/deleteRoom")
    Observable<GetUserCheck> deleteRoom(@FieldMap Map<String, String> item);

}
