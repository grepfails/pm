package com.grepfails.proyectofintrim;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by grep on 12/12/2016.
 */

public interface APIService {

    @GET("tv/popular")
    Call<SeriesResponse> getPopularSeries(@Query("api_key") String apiKey);

    @GET("tv/{id}")
    Call<Serie> getSerieById(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/credits")
    Call<RepartoResponse> getRepartoById(@Path("id") int id, @Query("api_key") String apiKey);
}
