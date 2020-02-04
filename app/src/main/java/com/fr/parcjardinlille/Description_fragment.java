package com.fr.parcjardinlille;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class Description_fragment  extends DialogFragment {


    private TextView descritpion;
    private String descriptionS;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.description_fragement,null);
        descritpion = (TextView)view.findViewById(R.id.descriptionText);

        descritpion.setText(descriptionS.toString());
        setCancelable(true);
        return view;
    }

    public void setDescription(String description){
        this.descriptionS = description;
    }
}
