package com.appondefica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CEPService {
    @GET("{cep}/json")
    Call<CEP> buscaCEP(@Path("cep") String cep);
    @GET("{estado}/{cidade}/{logradouro}/json")
    Call<List<CEP>> buscaEndereco(@Path("estado") String estado, @Path("cidade") String cidade, @Path("logradouro") String logradouro);

}
