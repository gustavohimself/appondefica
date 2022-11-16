package com.appondefica;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Estado> estados;
    private List<Municipio> municipios;
    private List<CEP> enderecos;
    private Spinner spinnerEstados;
    private Spinner spinnerMunicipios;
    private Button btBuscar;
    private TextView tvLocalidade;
    private ListView listView;
    private ArrayAdapter<CEP> adapter;
    private int estadoSelecionado;
    private int municipioSelecionado;

    private void getEstados()
    {
        Call<List<Estado>> call = new RetrofitConfig("https://servicodados.ibge.gov.br/api/v1/localidades/").getIBGEService().obterEstados();
        call.enqueue(new Callback<List<Estado>>() {
            @Override
            public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                MainActivity.this.estados = response.body();

                ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, MainActivity.this.estados);

                spinnerEstados.setAdapter(aa);
                spinnerEstados.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                MainActivity.this.estadoSelecionado = position;
                                MainActivity.this.getMunicipios();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        }
                );

            }

            @Override
            public void onFailure(Call<List<Estado>> call, Throwable t) {
            }
        });
    }

    private void getMunicipios()
    {
        Call<List<Municipio>> call = new RetrofitConfig("https://servicodados.ibge.gov.br/api/v1/localidades/").getIBGEService().obterMunicipios(this.estados.get(this.estadoSelecionado).sigla);
        call.enqueue(new Callback<List<Municipio>>() {
            @Override
            public void onResponse(Call<List<Municipio>> call, Response<List<Municipio>> response) {
                MainActivity.this.municipios = response.body();


                ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, MainActivity.this.municipios);

                spinnerMunicipios.setAdapter(aa);
                spinnerMunicipios.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                MainActivity.this.municipioSelecionado = position;
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        }
                );
            }

            @Override
            public void onFailure(Call<List<Municipio>> call, Throwable t) {
            }
        });
    }

    private void buscarEnderecos()
    {
        if(this.estados == null || this.municipios == null) return;
        Call<List<CEP>> call = new RetrofitConfig("https://viacep.com.br/ws/").getCEPService().buscaEndereco(this.estados.get(this.estadoSelecionado).sigla, this.municipios.get(this.municipioSelecionado).toString(), this.tvLocalidade.getText().toString());
        call.enqueue(new Callback<List<CEP>>() {
            @Override
            public void onResponse(Call<List<CEP>> call, Response<List<CEP>> response) {

                MainActivity.this.enderecos = response.body();
                if(MainActivity.this.enderecos != null)
                {
                    MainActivity.this.adapter = new CEPAdapter(MainActivity.this, R.layout.item_listview, MainActivity.this.enderecos);
                    MainActivity.this.listView.setAdapter(MainActivity.this.adapter);
                }
                else
                    return;

            }

            @Override
            public void onFailure(Call<List<CEP>> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.spinnerEstados = findViewById(R.id.spEstado);
        this.spinnerMunicipios = findViewById(R.id.spCidade);
        this.btBuscar = findViewById(R.id.btBuscar);
        this.listView = findViewById(R.id.listView);
        this.tvLocalidade = findViewById(R.id.tvLocalidade);
        btBuscar.setOnClickListener(e->{buscarEnderecos();});
        this.getEstados();

        this.listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, MapsDetalhesActivity.class);
            intent.putExtra("addressDetails", MainActivity.this.enderecos.get(i));
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.itSair:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }




}