package bbs.courseproject.com.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit = null;

    public static String base_url = "https://devdata.daktarlagbe.com/api/test/";

    public static ApiUserService getRetrofit(){

        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(ApiUserService.class);
    }
}
