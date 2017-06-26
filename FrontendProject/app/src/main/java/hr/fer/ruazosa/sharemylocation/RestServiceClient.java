package hr.fer.ruazosa.sharemylocation;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created on 18.6.2017..
 */

public interface RestServiceClient {

    @GET("loginUser")
    Call<User> loginUser(
            @Query("userName") String userName,
            @Query("userPassword") String userPassword
    );

    @POST("registerUser")
    Call<User> registerUser(
            @Body User user
    );

    @GET("resetPassword")
    Call<User> resetPassword(
            @Query("userName") String userName,
            @Query("userPassword") String userPassword
    );

    @POST("createGroup")
    Call<Group> createGroup(
            @Body Group group
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://sharemylocation.mybluemix.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
