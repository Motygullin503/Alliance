package ru.kpfu.itis.alliance.allianceAPI;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface allianceAPI {


    @POST("/request")
    Call<Object> sendData(@Query("email") String email,
                          @Query("phone") String phone,
                          @Query("who_you") String who_you,
                          @Query("platform") String platform,
                          @Query("total_sum") Double total_sum,
                          @Query("is_new") Boolean is_new);


}
