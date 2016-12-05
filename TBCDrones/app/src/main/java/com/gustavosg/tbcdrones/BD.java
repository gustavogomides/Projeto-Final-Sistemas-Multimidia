package com.gustavosg.tbcdrones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class BD {
    private SQLiteDatabase bd;

    public BD(Context context) {
        BDCore auxBd = new BDCore(context);
        bd = auxBd.getWritableDatabase();
    }

    public void desconectar() {
        bd.close();
    }

    public long inserir(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("usuario", usuario.getUsuario());
        valores.put("senha", usuario.getSenha());
        valores.put("nomecompleto", usuario.getNomecompleto());
        valores.put("funcao", usuario.getFuncao());

        long retorno = bd.insert("login", null, valores);
        bd.close();
        return retorno;
    }


    public Usuario usuarioatual() {

        Usuario uatual = new Usuario();

        String sql = "SELECT * from usuarioatual";
        Cursor cursor = bd.rawQuery(sql, null);

        if (cursor.getCount() == 0) {
            return null;
        } else {
            cursor.moveToFirst();

            do {
                uatual.setUsuario(cursor.getString(0));
                uatual.setSenha(cursor.getString(1));
                uatual.setNomecompleto(cursor.getString(2));
                uatual.setFuncao(cursor.getString(3));
                Log.i("WHILE", "aq");
            } while (cursor.moveToNext());
        }

        cursor.close();

        return uatual;
    }

    public void inserirUsuarioAtual(String u) {
        bd.delete("usuarioatual", null, null);
        Usuario usuario = verificarUsuario(u);

        ContentValues valores = new ContentValues();
        valores.put("usuario", usuario.getUsuario());
        valores.put("senha", usuario.getSenha());
        valores.put("nomecompleto", usuario.getNomecompleto());
        valores.put("funcao", usuario.getFuncao());


        bd.insert("usuarioatual", null, valores);
    }

    public Usuario verificarUsuario(String nome) {
        Usuario u = new Usuario();

        String sql = "SELECT usuario, senha, nomecompleto, funcao from login where usuario=?";
        Cursor cursor = bd.rawQuery(sql, new String[]{nome});

        if (cursor.getCount() == 0) {
            return null;
        } else if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                u.setUsuario(cursor.getString(0));
                u.setSenha(cursor.getString(1));
                u.setNomecompleto(cursor.getString(2));
                u.setFuncao(cursor.getString(3));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return u;
    }

    public boolean verificaSenha(String nome, String senha) {
        Usuario u = new Usuario();

        String sql = "SELECT senha from login where usuario=?";
        Cursor cursor = bd.rawQuery(sql, new String[]{nome});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                u.setSenha(cursor.getString(0));

            } while (cursor.moveToNext());
        }

        cursor.close();

        if (senha.equals(u.getSenha())) {
            return true;
        } else {
            return false;
        }
    }

    public long inserirresposta(String est, String questao, String media) {
        Usuario u = usuarioatual();
        String usuario = u.getUsuario();
        long retorno;

        String sql = "SELECT q1, q2, q3, q4, q5 from questoes where usuario=?";
        Cursor cursor = bd.rawQuery(sql, new String[]{usuario});

        if (cursor.getCount() != 0 && questao.equals("q1")) {
            String table = "questoes";
            String whereClause = "usuario" + "=?";
            String[] whereArgs = new String[]{usuario};
            bd.delete(table, whereClause, whereArgs);
            Log.i("DELETE", "AQ");
        }

        if (questao.equals("q1")) {
            int estado = Integer.parseInt(est);
            ContentValues v = new ContentValues();
            v.put("usuario", usuario);
            v.put("q1", estado);
            retorno = bd.insert("questoes", null, v);
        } else if (questao.equals("desempenho")) {
            double mediafinal = Double.parseDouble(media);
            ContentValues valores = new ContentValues();
            valores.put("mediafinal", mediafinal);
            retorno = bd.update("questoes", valores, "usuario=?", new String[]{usuario});
        } else {
            int estado = Integer.parseInt(est);
            ContentValues valores = new ContentValues();
            valores.put(questao, estado);
            retorno = bd.update("questoes", valores, "usuario=?", new String[]{usuario});
        }
        return retorno;
    }

    public Resposta retornarrespostas() {
        Resposta resposta = new Resposta();

        Usuario u = usuarioatual();
        String usuario = u.getUsuario();

        String sql = "SELECT q1, q2, q3, q4, q5 from questoes where usuario=?";
        Cursor cursor = bd.rawQuery(sql, new String[]{usuario});

        if (cursor.getCount() == 0) {
            Log.i("Resposta", "null");
            return null;
        } else if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                resposta.setQ1(cursor.getInt(0));
                resposta.setQ2(cursor.getInt(1));
                resposta.setQ3(cursor.getInt(2));
                resposta.setQ4(cursor.getInt(3));
                resposta.setQ5(cursor.getInt(4));
            } while (cursor.moveToNext());
            Log.i("Resposta: ", usuario + " / " + resposta.getQ1() + " / " + resposta.getQ2() + " / "
                    + resposta.getQ3() + " / " + resposta.getQ4() + " / " + resposta.getQ5());
        }

        cursor.close();
        resposta.setUsuario(u);
        return resposta;
    }

    public ArrayList<Usuario> alunosCadastrados() {
        ArrayList<Usuario> arrayListUsuario = new ArrayList<>();
        String funcao = "aluno";
        String sql = "SELECT * from login where funcao=?";
        Cursor cursor = bd.rawQuery(sql, new String[]{funcao});

        if (cursor.getCount() == 0) {
            return null;
        } else if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                arrayListUsuario.add(new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return arrayListUsuario;
    }

    public ArrayList<Resposta> notasAlunos() {
        ArrayList<Resposta> arrayListRespostas = new ArrayList<>();
        String sql = "SELECT q1, q2, q3, q4, q5, usuario, mediafinal from questoes";

        Cursor cursor = bd.rawQuery(sql, null);
        ArrayList<Usuario> u = alunosCadastrados();

        if (cursor.getCount() == 0) {
            return null;
        } else if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String user = cursor.getString(5);
                Usuario use = null;
                for (Usuario us : u) {
                    if (us.getUsuario().equals(user)) {
                        use = us;
                    }
                }
                arrayListRespostas.add(new Resposta(cursor.getInt(0), cursor.getInt(1),
                        cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), use, cursor.getDouble(6)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return arrayListRespostas;
    }

    public long atualizarUsuario(String usuarioanterior, String novousuario) {
        ContentValues valores = new ContentValues();
        valores.put("usuario", novousuario);
        return bd.update("login", valores, "usuario=?", new String[]{usuarioanterior});

    }

    public long atualizarNome(String usuario, String novonome) {
        ContentValues valores = new ContentValues();
        valores.put("nomecompleto", novonome);
        return bd.update("login", valores, "usuario=?", new String[]{usuario});

    }

    public long atualizarSenha(String usuario, String novasenha) {
        ContentValues valores = new ContentValues();
        valores.put("senha", novasenha);
        return bd.update("login", valores, "usuario=?", new String[]{usuario});

    }

    public int deletarAluno(String usuario) {
        String table = "login";
        String whereClause = "usuario" + "=?";
        String[] whereArgs = new String[]{usuario};
        return bd.delete(table, whereClause, whereArgs);
    }
}

