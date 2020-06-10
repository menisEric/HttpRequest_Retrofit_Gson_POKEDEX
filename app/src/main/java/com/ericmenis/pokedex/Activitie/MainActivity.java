package com.ericmenis.pokedex.Activitie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.GridView;

import com.ericmenis.pokedex.API.API;
import com.ericmenis.pokedex.API.APIService.WeatherService;
import com.ericmenis.pokedex.Adapter.PokemonAdapter;
import com.ericmenis.pokedex.Models.Pokemon;
import com.ericmenis.pokedex.Models.PokemonRespuestas;
import com.ericmenis.pokedex.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private static PokemonAdapter adapter;

    private int offset;

    private boolean aptoparaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalitemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoparaCargar){
                        if ((visibleItemCount + pastVisibleItems) >= totalitemCount){
                            Log.i(API.TAG, "Llegamos al final.");

                            aptoparaCargar = false;
                            offset += 20;
                            ObtenerRespuestas(offset);
                        }
                    }
                }

            }
        });

        offset = 0;

        ObtenerRespuestas(offset);


    }

    private void ObtenerRespuestas(int offset) {
        WeatherService service = API.getApi().create(WeatherService.class);

        Call<PokemonRespuestas> pokemonRespuestasCall = service.obtenerListaPokemon(20, offset);

        pokemonRespuestasCall.enqueue(new Callback<PokemonRespuestas>() {
            @Override
            public void onResponse(Call<PokemonRespuestas> call, Response<PokemonRespuestas> response) {
                aptoparaCargar = true;
                if (response.isSuccessful()) {
                    PokemonRespuestas pokemonRespuestas = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonRespuestas.getResults();

                    adapter.adicionarListaPokemon(listaPokemon);

                } else {
                    Log.e(API.TAG, " onResponse" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuestas> call, Throwable t) {
                aptoparaCargar = true;
                Log.e(API.TAG, " onFailure " + t.getMessage());
            }
        });
    }
}
