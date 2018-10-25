package com.example.cassi.exercicio_series;


import android.provider.BaseColumns;

    public final class SerieContract {

        public final class Serie implements BaseColumns {
            public final static String TABLE_NAME = "Serie";
            public final static String COLUMN_NAME_NOME = "nome";
            public static final String COLUMN_NAME_TEMPORADA = "temporada";
            public static final String COLUMN_NAME_EPISODIO = "episodio";
            public final static String CREATE_SERIE  = "CREATE TABLE "+Serie.TABLE_NAME+" ("
                    + Serie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Serie.COLUMN_NAME_NOME+ " TEXT, "
                    + Serie.COLUMN_NAME_TEMPORADA+ " INTEGER,"
                    + Serie.COLUMN_NAME_EPISODIO+ " INTEGER"
                    +")";
            public final static String DROP_SERIE = "DROP TABLE IF EXISTS "+Serie.TABLE_NAME;
        }
    }

