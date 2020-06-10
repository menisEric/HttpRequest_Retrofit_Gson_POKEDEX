package com.ericmenis.pokedex.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ericmenis.pokedex.API.API;
import com.ericmenis.pokedex.Models.Pokemon;
import com.ericmenis.pokedex.R;

import java.util.ArrayList;


public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private Context context;

    public PokemonAdapter(Context context){
        this.context = context;
        pokemons = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.nombreTextView.setText(pokemon.getName());

        Glide.with(context)
                .load(API.URL_IMAGE + pokemon.getNumber() + API.EXTENSION_ICONS)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        pokemons.addAll(listaPokemon);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView fotoImageView;
        private TextView nombreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImageView = (ImageView) itemView.findViewById(R.id.imagePokemonView);
            nombreTextView = (TextView) itemView.findViewById(R.id.textPokemonView);
        }

    }
}
