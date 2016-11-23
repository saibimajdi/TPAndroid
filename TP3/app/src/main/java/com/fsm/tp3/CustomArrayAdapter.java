package com.fsm.tp3;

import android.content.Context;
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
 * Created by saibi on 11/10/2016.
 */

public class CustomArrayAdapter extends ArrayAdapter<Vector<String>> {

    ArrayList<Vector<String>> arrayList;
    LayoutInflater layoutInflater;

    public CustomArrayAdapter(Context _context,
                              ArrayList<Vector<String>> _arrayList,
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

        Vector<String> etudiant = arrayList.get(position);
        imgView.setImageResource(R.drawable.mypicture);
        tvNomPrenom.setText(etudiant.get(0) + " " + etudiant.get(1));
        tvFormation.setText(etudiant.get(2));

        return view;
    }
}

