package com.appondefica;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CEPService {
    @GET("{cep}/json")
    Call<CEP> buscaCEP(@Path("cep") String cep);
    @GET("/ws/")
    Call<CEP> buscaPessoa(@Query("estado") String estado, @Query("cidade") String cidade, @Query("logradouro") String logradouro);

}
