package com.appondefica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IBGEService {
    @GET("estados?orderBy=nome")
    Call<List<Estado>> obterEstados();

    @GET("estados/{estado}/municipios")
    Call<List<Municipio>> obterMunicipios(@Path("estado") String estado);

}
