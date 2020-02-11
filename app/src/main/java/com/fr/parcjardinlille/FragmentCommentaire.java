package com.fr.parcjardinlille;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.fr.parcjardinlille.Services.Service;
import com.fr.parcjardinlille.models.Commentaire;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FragmentCommentaire extends DialogFragment {

    private Long parcJardin_getId;
    private Button Publier;
    private Button Annuler;
    private RatingBar barEtoile;
    private EditText commentaire,NameUser;

    private String nameuserS,commentaireUser;
    private int nombreEtoile;

    public Service URLretrofit() {
        Service service = new RestAdapter.Builder()
                .setEndpoint(Service.API_URL)
                .build()
                .create(Service.class);
        return service;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public  View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){

        final View view = inflater.inflate(R.layout.add_commentaires,null);

        Publier = (Button)view.findViewById(R.id.Publier);
        Annuler =(Button)view.findViewById(R.id.Annuler);
        barEtoile = (RatingBar)view.findViewById(R.id.ratingBar2);
        NameUser =(EditText)view.findViewById(R.id.nameUser);
        commentaire = (EditText)view.findViewById(R.id.Commentaire);

        Publier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!NameUser.getText().toString().trim().equals("") && !commentaire.getText().toString().trim().equals("") ){
                    PublierCommentaire();
                    NameUser.setText("");
                    commentaire.setText("");
                    getActivity().finish();
                }
                else
                    Toast.makeText(getContext(),"Champ obligatoires vide :Veuillez remplir les champs",Toast.LENGTH_SHORT).show();

            }
        });
        Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();            }
        });


        return view;

    }

    public void setIdParcJardin(Long parcJardin_getId){
        this.parcJardin_getId = parcJardin_getId;
    }

    public void PublierCommentaire(){
        Service service = URLretrofit();
        nameuserS = NameUser.getText().toString().trim();
        commentaireUser =commentaire.getText().toString().trim();
        nombreEtoile = (int) barEtoile.getRating();


    //@POST("/addcommentaire/{idpj}/{nameUtilisateur}/{note}/{message}")
        service.addNewCommentaire(parcJardin_getId,nameuserS,nombreEtoile,commentaireUser, new Callback<Commentaire>() {
            @Override
            public void success(Commentaire c, Response response) {
                if(c == null){
                    Toast.makeText(getContext()," Actualiser : Oops  Erreur Commentaire   :( "+c.getId(),Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext()," Yes, Votre commentaire et bien Envoyer",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(),"Ooops error envoyer Commenataire: "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}













