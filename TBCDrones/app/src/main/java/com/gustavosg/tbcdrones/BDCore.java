package com.gustavosg.tbcdrones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_DB = "tbcdrone";
    private static final int VERSAO_BD = 1;

    public BDCore(Context context) {
        super(context, NOME_DB, null, VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table login(usuario text primary key, senha text not null, nomecompleto text not null, funcao text not null);");
        bd.execSQL("create table questoes(id_questoes INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, usuario text not null," +
                " q1 int, q2 int, q3 int, q4 int, q5 int, mediafinal real);");
        bd.execSQL("create table usuarioatual(usuario text primary key, senha text not null, nomecompleto text not null, funcao text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        onCreate(bd);
    }
}

