package com.example.cassi.exercicio_series;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Inserir extends AppCompatActivity {

    private Button btnInserir;

    private RecyclerView rclSeries;
    private SerieDbHelper dbHelper;
    private SerieAdapter adapter;
    private EditText txtNomeSerie;
    private EditText txtNumeroTemporada;
    private EditText txtNumeroEpisodio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);


        dbHelper = new SerieDbHelper(getApplicationContext());

        rclSeries = (RecyclerView) findViewById(R.id.rclSeries);
        rclSeries.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SerieAdapter(getSeries());
        rclSeries.setAdapter(adapter);

        txtNomeSerie = (EditText) findViewById(R.id.nomeSerie);
        txtNumeroTemporada = (EditText) findViewById(R.id.numeroTemporada);
        txtNumeroEpisodio = (EditText) findViewById(R.id.numeroEpisodio);

        btnInserir = (Button) findViewById(R.id.btnInserir);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(SerieContract.Serie.COLUMN_NAME_NOME, txtNomeSerie.getText().toString());
                valores.put(SerieContract.Serie.COLUMN_NAME_TEMPORADA, txtNumeroTemporada.getText().toString());
                valores.put(SerieContract.Serie.COLUMN_NAME_EPISODIO, txtNumeroEpisodio.getText().toString());
                long id = db.insert(SerieContract.Serie.TABLE_NAME,null, valores);
                Intent intent = new Intent(Inserir.this, MainActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnClickListener(new SerieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                //Problema na exclusão
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String select = SerieContract.Serie._ID+" = ?";
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