package com.gustavosg.tbcdrones;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Projeto: Calculando
 * Criado por Gustavo em 20/07/2016.
 */
public class MainFrament extends Fragment {

    private TextView textView;

    // *************************************************************************************************
    // onCreateView
    // Retorna o view principal
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_main, container, false);
        PlayGifView pGif = (PlayGifView) rootView.findViewById(R.id.viewGif);
        pGif.setImageResource(R.drawable.dronegif);

        textView = (TextView) rootView.findViewById(R.id.titulomain);
        usuario();
        return rootView;
    }

    public void usuario() {
        BD bd = new BD(getActivity());
        Usuario u = bd.usuarioatual();
        String usuario = u.getNomecompleto();
        textView.setText("Bem-Vindo " + usuario + "!");
    }
}
