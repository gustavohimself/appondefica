package com.appondefica;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsDetalhesActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private Marker marker;
    private LatLng latLng = null;
    private MarkerOptions markerOptions = new MarkerOptions();
    private static final String MAP_VIEW_BUNDLE_KEY="MapViewBundleKey";
    private CEP endereco;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_detalhes);
        mapView = findViewById(R.id.mapView);
        mapView.setClickable(true);
        Bundle mapViewBundle =null;
        if(savedInstanceState!=null)
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        endereco = (CEP) getIntent().getSerializableExtra("addressDetails");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle (MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle (MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }
        mapView.onSaveInstanceState(mapViewBundle);
    }

    private void obterLatLng()
    {
        Call<Geocoding> call = new RetrofitConfig("https://maps.google.com/maps/api/geocode/").getGeocodingService().obterDadosEndereco(this.endereco.toString(), "country:BR", getString(R.string.API_KEY));
        call.enqueue(new Callback<Geocoding>() {
            @Override
            public void onResponse(Call<Geocoding> call, Response<Geocoding> response) {

            Geocoding geocoding = response.body();
            MapsDetalhesActivity.this.latLng = new LatLng(geocoding.results.get(0).geometry.location.lat, geocoding.results.get(0).geometry.location.lng);



            mudarPosicao();

            }

            @Override
            public void onFailure(Call<Geocoding> call, Throwable t) {
            }
        });
    }

    private void mudarPosicao() {
        UiSettings ponto =
                googleMap.getUiSettings();
        ponto.setIndoorLevelPickerEnabled(true);

        ponto.setMyLocationButtonEnabled(true);
        ponto.setMapToolbarEnabled(true);
        ponto.setCompassEnabled(true);
        ponto.setZoomControlsEnabled(true);

        if(latLng == null)
            latLng= new LatLng(-22.1332654, -51.4051404);
        markerOptions.position(latLng);
        marker=googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap=googleMap;
        this.googleMap.setMinZoomPreference(12);
        this.googleMap.setIndoorEnabled(true);
        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                marker.setPosition(latLng);

            }
        });
        obterLatLng();

    }
}
