package com.example.cassi.exercicio_series;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class Remover extends AppCompatActivity {

    private RecyclerView rclSeries;
    private SerieDbHelper dbHelper;
    private SerieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover);
        dbHelper = new SerieDbHelper(getApplicationContext());

        rclSeries = (RecyclerView) findViewById(R.id.rclSeries);
        rclSeries.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SerieAdapter(getSeries());
        rclSeries.setAdapter(adapter);



        adapter.setOnClickListener(new SerieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String select = SerieContract.Serie._ID+" = ?+1";
                String [] selectArgs = {String.valueOf(position)};
                db.delete(SerieContract.Serie.TABLE_NAME, select, selectArgs);
                adapter.setCursor(getSeries());
                adapter.notifyItemRemoved(position);
            }
        });
    }

    private Cursor getSeries()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String []visao = {
                SerieContract.Serie.COLUMN_NAME_NOME,
                SerieContract.Serie.COLUMN_NAME_TEMPORADA,
                SerieContract.Serie.COLUMN_NAME_EPISODIO,
        };
        String sort = SerieContract.Serie.COLUMN_NAME_NOME+ " ASC";
        return db.query(SerieContract.Serie.TABLE_NAME, visao,null,null,null,null, sort);
    }
}