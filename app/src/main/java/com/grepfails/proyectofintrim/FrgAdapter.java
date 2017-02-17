package com.grepfails.proyectofintrim;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.bitmap;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.grepfails.proyectofintrim.APIInfo.getApiBaseImg;
import static com.grepfails.proyectofintrim.APIInfo.getApiKey;
import static com.grepfails.proyectofintrim.APIInfo.getBaseUrl;

/**
 * Created by grep on 15/12/2016.
 */

public class FrgAdapter extends Fragment{

    private ListView lstView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub


        View view = inflater.inflate(R.layout.frg_serie, container, false);

        this.lstView = (ListView) view.findViewById(R.id.lvReparto);

        TabHost tabs = (TabHost) view.findViewById(android.R.id.tabhost);
        tabs.setup();

        TextView txtTitulo = (TextView) view.findViewById(R.id.tvTitulofrag);
        txtTitulo.setText(getArguments().getString("titulo"));

        TextView txtTitulo_org = (TextView) view.findViewById(R.id.tvTit_orig);
        txtTitulo_org.setText(getArguments().getString("titulo_orig"));

        TextView txtDescripcion = (TextView) view.findViewById(R.id.tvDescripcion);
        txtDescripcion.setText(getArguments().getString("descripcion"));

        TextView txtAnio = (TextView) view.findViewById(R.id.tvanio);
        txtAnio.setText(getArguments().getString("anio"));

        TextView txtVotos = (TextView) view.findViewById(R.id.tvVotos);
        txtVotos.setText("Nota: " + String.valueOf(getArguments().getDouble("votos")));

        TextView txtLeng_orig = (TextView) view.findViewById(R.id.tvLeng_orig);
        txtLeng_orig.setText("Leng. Original: " + getArguments().getString("leng_orig"));

        ImageView ivPortada = (ImageView) view.findViewById(R.id.ivPortada);
        new ImageDownloader(ivPortada).execute(getApiBaseImg() + getArguments().getString("portada"));

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        APIService service = APIClient.getClient(getBaseUrl()).create(APIService.class);
        final Call<RepartoResponse> repartoResponse= service.getRepartoById(getArguments().getInt("id"), getApiKey());
        repartoResponse.enqueue(new Callback<RepartoResponse>() {
            @Override
            public void onResponse(Call<RepartoResponse> call, Response<RepartoResponse> response) {
                List<Cast> listReparto = response.body().getCast();
                RepartoAdapter rptadpt = new RepartoAdapter(getContext(), listReparto);
                lstView.setAdapter(rptadpt);
            }

            @Override
            public void onFailure(Call<RepartoResponse> call, Throwable t) {

            }
        });

        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Detalle");
        tabs.addTab(spec);
        TabHost.TabSpec spec2 = tabs.newTabSpec("mitab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Reparto");
        tabs.addTab(spec2);
        tabs.setCurrentTab(0);

        return view;
    }

}
