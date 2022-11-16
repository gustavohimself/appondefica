package com.appondefica;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingService {

    @GET("json")
    Call<Geocoding> obterDadosEndereco(@Query("address") String address, @Query("components") String components, @Query("key") String key);

}
