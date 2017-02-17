package com.grepfails.proyectofintrim;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.principal_layout)
    RelativeLayout principalLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private APIService service;
    private APIInfo apiinfo;
    public List<Serie> listSeries;

    private RecyclerView recViewInfSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        recViewInfSerie = (RecyclerView) findViewById(R.id.rvSeries);
        recViewInfSerie.setHasFixedSize(true);

        apiinfo = new APIInfo("http://api.themoviedb.org/3/", "3ddb08776a514ff67fa19d94e037893d", "https://image.tmdb.org/t/p/w500");

        if (apiinfo.getApiKey().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        service = APIClient.getClient(apiinfo.getBaseUrl()).create(APIService.class);
        Call<SeriesResponse> call = service.getPopularSeries(apiinfo.getApiKey());

        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                listSeries = response.body().getResults();

                SeriesAdaptRec seriesAdaptRec = new SeriesAdaptRec(listSeries);
                seriesAdaptRec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int position = recViewInfSerie.getChildAdapterPosition(view);

                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaccion = manager.beginTransaction();

                        FrgAdapter fragment = new FrgAdapter();
                        transaccion.replace(R.id.principal_layout, fragment).addToBackStack(null);

                        transaccion.commit();

                        Bundle data = new Bundle();
                        data.putString("portada", listSeries.get(position).getPoster_path());
                        data.putInt("id", listSeries.get(position).getId());
                        data.putString("titulo", listSeries.get(position).getName());
                        data.putString("titulo_orig", listSeries.get(position).getOriginal_name());
                        data.putString("descripcion", listSeries.get(position).getOverview());
                        //data.putString("portada", listSeries.get(position).getPoster_path());
                        data.putString("anio", listSeries.get(position).getFirst_air_date());
                        data.putDouble("votos", listSeries.get(position).getVote_average());
                        data.putString("leng_orig", listSeries.get(position).getOriginal_language());
                        fragment.setArguments(data);
                    }
                });
                recViewInfSerie.setAdapter(seriesAdaptRec);
                recViewInfSerie.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Actualmente no admiten nuevas series", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}