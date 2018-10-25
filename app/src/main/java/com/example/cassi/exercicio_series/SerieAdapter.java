package com.example.cassi.exercicio_series;

    import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

    class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.ViewHolder>{
        private Cursor cursor;
        private OnItemClickListener listener;

        public interface OnItemClickListener{
            void onItemClick(View view, int position);
        }

        public void setOnClickListener(OnItemClickListener listener)
        {
            this.listener = listener;
        }

        public SerieAdapter(Cursor c) {cursor = c;}

        public void setCursor(Cursor cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Context context = viewGroup.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View serieView = inflater.inflate(R.layout.serie_layout, viewGroup, false);
            ViewHolder holder = new ViewHolder(serieView);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull SerieAdapter.ViewHolder viewHolder, int i) {
            int idxNome = cursor.getColumnIndexOrThrow(SerieContract.Serie.COLUMN_NAME_NOME);
            int idxTemporada = cursor.getColumnIndexOrThrow(SerieContract.Serie.COLUMN_NAME_TEMPORADA);
            int idxNumero = cursor.getColumnIndexOrThrow(SerieContract.Serie.COLUMN_NAME_EPISODIO);
            cursor.moveToPosition(i);
            viewHolder.txtNome.setText(cursor.getString(idxNome));
            viewHolder.txtTemporada.setText(String.valueOf(cursor.getInt(idxTemporada)));
            viewHolder.txtEpisodio.setText(String.valueOf(cursor.getInt(idxNumero)));
        }

        @Override
        public int getItemCount() {
            return cursor.getCount();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView txtNome;
            public TextView txtTemporada;
            public TextView txtEpisodio;

            public ViewHolder(final View itemView) {
                super(itemView);
                txtNome = itemView.findViewById(R.id.nomeSerie);
                txtTemporada = itemView.findViewById(R.id.numeroTemporada);
                txtEpisodio = itemView.findViewById(R.id.numeroEpisodio);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null)
                        {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION)
                            {
                                listener.onItemClick(itemView, position);
                            }
                        }
                    }
                });
            }

            @Override
            public void onClick(View v)
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(v, position);
                }
            }
        }
    }
