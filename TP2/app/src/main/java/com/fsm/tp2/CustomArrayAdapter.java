package com.fsm.tp2;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by saibi on 11/03/2016.
 */

public class CustomArrayAdapter extends ArrayAdapter<Etudiant> {

    ArrayList<Etudiant> arrayList;
    LayoutInflater layoutInflater;

    public CustomArrayAdapter(Context _context,
                              ArrayList<Etudiant> _arrayList,
                              LayoutInflater _layoutInflater){
        super(_context, -1, _arrayList);
        this.arrayList = _arrayList;
        this.layoutInflater = _layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.list_row, parent, false);

        ImageView imgView = (ImageView) view.findViewById(R.id.listRowImageView);
        TextView tvNomPrenom = (TextView) view.findViewById(R.id.listRowNomPrenom);
        TextView tvFormation = (TextView) view.findViewById(R.id.listRowFormation);

        Etudiant etudiant = arrayList.get(position);
        imgView.setImageResource(R.mipmap.ic_launcher);
        tvNomPrenom.setText(etudiant.Nom + " " + etudiant.Prenom);
        tvFormation.setText(etudiant.Formation);

        return view;
    }
}
