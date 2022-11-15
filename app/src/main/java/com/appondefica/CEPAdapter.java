package com.appondefica;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CEPAdapter extends ArrayAdapter<CEP> {
    private int layout;
    public CEPAdapter(@NonNull Context context, int resource, @NonNull List<CEP> objects) {
        super(context, resource, objects);
        layout=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout, parent, false);

        }
        Log.e("Exibir", ""+position);
        TextView tvEstados = (TextView) convertView.findViewById(R.id.tvEstados);
        TextView tvCidades = (TextView) convertView.findViewById(R.id.tvCidades);
        TextView tvRua = (TextView) convertView.findViewById(R.id.tvRua);
        TextView tvBairro = (TextView) convertView.findViewById(R.id.tvBairro);
        TextView tvNumero = (TextView) convertView.findViewById(R.id.tvNumero);
        TextView tvLatitude = (TextView) convertView.findViewById(R.id.tvLat);
        TextView tvLongitude = (TextView) convertView.findViewById(R.id.tvLong);

        CEP cep = (CEP) this.getItem(position);

        tvEstados.setText(cep.uf);
        tvCidades.setText(cep.localidade);
        tvRua.setText(cep.logradouro);
        tvBairro.setText(cep.bairro);
        tvNumero.setText("");
        tvLatitude.setText("");
        tvLongitude.setText("");

        return convertView;
    }
}
