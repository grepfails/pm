package com.grepfails.proyectofintrim;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by grep on 18/01/2017.
 */

public class SeriesAdaptRec extends RecyclerView.Adapter<SeriesAdaptRec.SeriesVH> implements View.OnClickListener {

    private List<Serie> listaSeries;
    private View.OnClickListener listener;

    public SeriesAdaptRec(List<Serie> listaSeries) {
        this.listaSeries = listaSeries;
    }

    public static class SeriesVH extends RecyclerView.ViewHolder {
        private TextView tvDescription;
        private TextView tvPoints;
        private TextView tvTitulo;
        private ImageView imageView2;

        public SeriesVH(View view) {
            super(view);

            this.tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            this.tvPoints = (TextView) view.findViewById(R.id.tvPoints);
            this.tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
            this.imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        }

        public void bindSeries(Serie serie) {
            this.tvTitulo.setText(serie.getName());
            this.tvDescription.setText(serie.getOriginal_name());
            this.tvPoints.setText("Nota: " + String.valueOf(serie.getVote_average()));
            new ImageDownloader(this.imageView2).execute(APIInfo.getApiBaseImg() + serie.getPoster_path());
        }
    }

    @Override
    public SeriesVH onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_serie, viewGroup, false);

        view.setOnClickListener(this);
        SeriesVH svh = new SeriesVH(view);

        return svh;
    }

    @Override
    public void onBindViewHolder(SeriesVH seriesVH, int position) {
        seriesVH.bindSeries(this.listaSeries.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listaSeries.size();
    }

    public void setOnClickListener( View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }
}
