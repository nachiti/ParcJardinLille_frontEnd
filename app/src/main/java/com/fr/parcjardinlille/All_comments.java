package com.fr.parcjardinlille;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.fr.parcjardinlille.Services.Service;
import com.fr.parcjardinlille.models.Commentaire;
import com.fr.parcjardinlille.models.ParcJardin;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class All_comments extends AppCompatActivity {
    private double latitude;
    private double longitude;
    private String URL_GOOGLEMAPS = "https://www.google.com/maps/search/?api=1";
    private RatingBar ratingBarEtoile;
    FragmentManager fragmentManager;
    private  Button buttonReturn;
    String NameParcJardinSelectionner;
    String parcJardin_getDescription;
    Long parcJardin_getId;
    private Service service;
    //All_comments All_fragmenet;
    private static final String TAG = DetailParcJardin.class.getSimpleName();

    public Service URLretrofit() {
        Service service = new RestAdapter.Builder()
                .setEndpoint(Service.API_URL)
                .build()
                .create(Service.class);
        return service;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_comments);
        // initialisation du service
        service = URLretrofit();
        fragmentManager = getSupportFragmentManager();
        buttonReturn =(Button)findViewById(R.id.buttonReturn);
      /*  ImageView imageView = findViewById(R.id.imageView2);
        String imageUrl = "https://via.placeholder.com/500";
        Picasso.get().load(imageUrl).into(imageView);*/
        ratingBarEtoile = (RatingBar) findViewById(R.id.ratingBarComments);



        /*adresse = (TextView)findViewById(R.id.adresse);
        description = (TextView)findViewById(R.id.description);*/

        Intent intent = getIntent();
        NameParcJardinSelectionner = intent.getStringExtra("NameParcJardinSelectionner");
        System.out.println("Actualiser   <<<<<<<<<<  " + NameParcJardinSelectionner);
        Actualiser(NameParcJardinSelectionner);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  onCloseFragment();
                ......................
                getActivity().onBackPressed();*/
            }
        });


    }

    private void Actualiser(String nameParcJardinSelectionner) {

        service.getParcJardinnByName(nameParcJardinSelectionner, new Callback<ParcJardin>() {
            @Override
            public void success(ParcJardin parcJardin, Response response) {
                if (parcJardin == null) {
                    Toast.makeText(getApplication(), " Actualiser : Oops Parc Ou Jardin Selectioner n'existe pas  :( ", Toast.LENGTH_SHORT).show();
                }


                parcJardin_getId = parcJardin.getId();
                System.out.println("cccccccccccccccccccccccccc" + parcJardin.getId());
                getCommentaireJardinParc(parcJardin.getId());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(), "Oops Error get Détail Parc :( ", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getCommentaireJardinParc(Long idParcJardin) {

        Service service = URLretrofit();
        System.out.println("sssssssssssssssssss" + idParcJardin);
        service.getAllCommentaireOfParcJardinById(idParcJardin, new Callback<List<Commentaire>>() {


            @Override
            public void success(List<Commentaire> commentaires, Response response) {

                if (commentaires.size() == 0) {
                    Toast.makeText(getApplication(), " getCommentaireJardinParc : Oops Pas de Commentaire :(, cest le moment d'insere le votre :) ", Toast.LENGTH_SHORT).show();
                }

                System.out.println("test"+commentaires.size());

                //description = new TextView( All_comments.this);
                LinearLayout layoutCommentaire = (LinearLayout) findViewById(R.id.LinearLayoutAllComments);
                layoutCommentaire.removeAllViews(); //Ligne problèmatique
                layoutCommentaire.removeAllViewsInLayout();
                int numberStar = 0;
                for (int i = 0; i < commentaires.size(); i++) {
                    LinearLayout linearH = new LinearLayout(All_comments.this);
                    linearH.setOrientation(LinearLayout.HORIZONTAL);
                    //photo de l'utilisateur
                    ImageView img = new ImageView(All_comments.this);
                    img.setId(i);
                    String urlImage = "https://via.placeholder.com/500";
                    // Picasso.with(getBaseContext()).load(urlImage).into(img);   c est pour tester je le mette en commentaire


                    //  ===============>
                    Picasso.get().load(urlImage).into(img);


                    //img.setImageDrawable(getResources().getDrawable(R.drawable.poline));
                    img.setLayoutParams(new FrameLayout.LayoutParams(100, 100));

                    img.setScaleType(ImageView.ScaleType.FIT_START);
                    linearH.addView(img);

                    LinearLayout linear = new LinearLayout(All_comments.this);
                    linear.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams2.setMargins(20, 20, 0, 0);

                    //linear.removeAllViews(); //Ligne problèmatique
                    linear.removeAllViewsInLayout();

                    TextView d = new TextView(All_comments.this);
                    String note = String.valueOf(commentaires.get(i).getNote());
                    System.out.println("test note   "+note);

                    d.setText(note);
                    d.setRight(600);
                    linear.addView(d, layoutParams2);

                    RatingBar rating = new RatingBar(All_comments.this);
                    rating.setScaleX(0.5f);
                    rating.setScaleY(0.5f);
                    rating.setRating(commentaires.get(i).getNote());//.setNumStars(2);
                    numberStar += commentaires.get(i).getNote();
                    rating.setEnabled(false);
                    LinearLayout.LayoutParams layoutParamsRating = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParamsRating.setMarginStart(85);
                    linear.addView(rating, layoutParamsRating);

                    TextView d2 = new TextView(All_comments.this);
                    d2.setText(commentaires.get(i).getMessage());
                    d2.setTextSize(15);
                    d2.setTextColor(Color.BLACK);

                    d2.setPadding(40, 0, 1, 0);

                    linear.addView(d2);
                    System.out.println(" >>>>>>>>>>> 172   ");

                    linearH.addView(linear);
                    layoutCommentaire.addView(linearH);
                    System.out.println(" >>>>>>>>>>> 176   ");


                }
                try {
                    ratingBarEtoile.setRating(numberStar / commentaires.size());
                    System.out.println(" >>>>>>>>>>> 182   "+ numberStar / commentaires.size());
                } catch (ArithmeticException e) {
                    ratingBarEtoile.setRating(0);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("test error");
                Toast.makeText(getApplication(), " Oops probleme get Image !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}