package bbs.courseproject.com.network;

import bbs.courseproject.com.model.UserInfoModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiUserService {
    @POST("login")
    Call<UserInfoModel> checkUserInfo(@Body UserInfoModel userInfoModel);

    @POST("register")
    Call<UserInfoModel> insertUserInfo(@Body UserInfoModel userInfoModel);

    @GET("user")
    Call<UserInfoModel> userInfo(@Body UserInfoModel userInfoModel);
    @DELETE("user")
    Call<UserInfoModel> userDeleteInfo(@Body UserInfoModel userInfoModel);
}
