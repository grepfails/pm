package com.grepfails.proyectofintrim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by grep on 12/12/2016.
 */

public class RepartoAdapter extends ArrayAdapter<Cast> {
    private LayoutInflater inflater;
    private List<Cast> listCasts;


    public RepartoAdapter(Context context, List<Cast> casts) {

        super(context, R.layout.item_reparto, casts);
        this.listCasts = casts;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = LayoutInflater.from(getContext());

        convertView = inflater.inflate(R.layout.item_reparto, null);

        new ViewHolder(convertView, listCasts.get(position));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvNombreActor)
        TextView tvNombreActor;
        @BindView(R.id.tvNombPers)
        TextView tvNombPers;
        @BindView(R.id.it_Reparto)
        RelativeLayout itReparto;

        private Cast cast;

        ViewHolder(View view, Cast cast) {
            ButterKnife.bind(this, view);

            this.cast = cast;
            this.tvNombreActor.setText(cast.getName());
            this.tvNombPers.setText(cast.getCharacter());
        }
    }
}
