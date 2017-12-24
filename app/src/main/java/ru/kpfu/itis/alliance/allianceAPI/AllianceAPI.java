package ru.kpfu.itis.alliance.allianceAPI;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.kpfu.itis.alliance.models.ResponseSuccess;


public interface AllianceAPI {


    @POST("/requests")
    Call<ResponseSuccess> sendData(@Query("phone") String phone,
                                   @Query("email") String email,
                                   @Query("object_name") String object,
                                   @Query("who_you") String who_you,
                                   @Query("platform") String platform,
                                   @Query("total_sum") Double total_sum,
                                   @Query("price_per_s") Double price,

                                   @Query("table") JSONArray table,
                                   @Query("is_new") Boolean is_new);


}
