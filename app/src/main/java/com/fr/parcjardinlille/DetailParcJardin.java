package com.fr.parcjardinlille;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

;

public class DetailParcJardin extends AppCompatActivity {
    private double latitude;
    private double longitude;
    private String URL_GOOGLEMAPS ="https://www.google.com/maps/search/?api=1";
    private RatingBar ratingBarEtoile;
    FragmentManager fragmentManager ;
    TextView plusSuite,nomParcJardin,adresse,description,horaireTextView;
    String NameParcJardinSelectionner;
    String parcJardin_getDescription;
    Long parcJardin_getId;
    private Service service;
    //All_comments All_fragmenet;
    private static final String TAG = DetailParcJardin.class.getSimpleName();
    public Service URLretrofit(){
        Service service = new RestAdapter.Builder()
                .setEndpoint(Service.API_URL)
                .build()
                .create(Service.class);
        return service;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailparcjardin);
        // initialisation du service
        service = URLretrofit();
        fragmentManager = getSupportFragmentManager();

      /*  ImageView imageView = findViewById(R.id.imageView2);
        String imageUrl = "https://via.placeholder.com/500";
        Picasso.get().load(imageUrl).into(imageView);*/

        plusSuite = (TextView)findViewById(R.id.plusSuite);
        ratingBarEtoile = (RatingBar) findViewById(R.id.ratingBar);
        String htmlString = "<u>Plus</u>";
        plusSuite.setText(Html.fromHtml(htmlString));

        nomParcJardin = (TextView)findViewById(R.id.nomParcJardin);
        horaireTextView = (TextView) findViewById(R.id.horaire);
        adresse = (TextView)findViewById(R.id.adresse);
        description = (TextView)findViewById(R.id.description);

        Intent intent = getIntent();
        NameParcJardinSelectionner = intent.getStringExtra("NameParcJardinSelectionner");
        System.out.println("Actualiserrrrrrr"+NameParcJardinSelectionner);
        Actualiser(NameParcJardinSelectionner);


    }

    private void Actualiser(String nameParcJardinSelectionner) {

        service.getParcJardinnByName(nameParcJardinSelectionner, new Callback<ParcJardin>() {
            @Override
            public void success(ParcJardin parcJardin, Response response) {
                if(parcJardin == null){
                    Toast.makeText(getApplication()," Actualiser : Oops Parc Ou Jardin Selectioner n'existe pas  :( ",Toast.LENGTH_SHORT).show();
                }

                latitude = parcJardin.getLatitude();
                longitude=parcJardin.getLongitude();
               // nomParcJardin = parcJardin.getNom();
                nomParcJardin.setText(parcJardin.getNom());
                nomParcJardin.setTextSize(20);

                adresse.setText("Adresse :"+parcJardin.getAdresse());
                adresse.setTextSize(15);
                description.setText("Description :"+parcJardin.getDescription());
                description.setTextSize(15);
                horaireTextView.setText("Horaire   :"+parcJardin.getHoraire());
                //horaireTextView.setTextSize(15);
                parcJardin_getDescription = parcJardin.getDescription();
                parcJardin_getId = parcJardin.getId();

                System.out.println("cccccccccccccccccccccccccc"+parcJardin.getId());
                getCommentaireJardinParc(parcJardin.getId());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Oops Error get Détail Parc :( ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCommentaireJardinParc(Long idParcJardin){

        Service service = URLretrofit();
        System.out.println("sssssssssssssssssss"+idParcJardin);
        service.getAllCommentaireOfParcJardinById(idParcJardin, new Callback<List<Commentaire>>() {


            @Override
            public void success(List<Commentaire> commentaires, Response response) {

                if(commentaires.size() == 0){
                    Toast.makeText(getApplication()," getCommentaireJardinParc : Oops Pas de Commentaire :(, cest le moment d'insere le votre :) ",Toast.LENGTH_SHORT).show();
                }

                System.out.println("test");

                description = new TextView( DetailParcJardin.this);
                LinearLayout layoutCommentaire = (LinearLayout) findViewById(R.id.LinearLayoutCommentaires);
                layoutCommentaire.removeAllViews(); //Ligne problèmatique
                layoutCommentaire.removeAllViewsInLayout();
                int numberStar = 0;
                for(int i=0;i<commentaires.size();i++){
                    LinearLayout linearH = new LinearLayout(DetailParcJardin.this);
                    linearH.setOrientation(LinearLayout.HORIZONTAL);
                    //photo de l'utilisateur
                    ImageView img = new ImageView(DetailParcJardin.this);
                    img.setId(i);
                    String urlImage = "https://via.placeholder.com/500";
                   // Picasso.with(getBaseContext()).load(urlImage).into(img);   c est pour tester je le mette en commentaire


                    //  ===============>
                    Picasso.get().load(urlImage).into(img);


                    //img.setImageDrawable(getResources().getDrawable(R.drawable.poline));
                    img.setLayoutParams(new FrameLayout.LayoutParams(100,100));

                    img.setScaleType(ImageView.ScaleType.FIT_START);
                    linearH.addView(img);

                    LinearLayout linear = new LinearLayout(DetailParcJardin.this);
                    linear.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams2.setMargins(20, 20, 0, 0);

                    linear.removeAllViews(); //Ligne problèmatique
                    linear.removeAllViewsInLayout();

                    TextView d = new TextView( DetailParcJardin.this);
                    String  note= String.valueOf(commentaires.get(i).getNote());
                    d.setText(note);
                    d.setRight(600);
                    linear.addView(d,layoutParams2);

                    RatingBar rating = new RatingBar(DetailParcJardin.this);
                    rating.setScaleX(0.5f);
                    rating.setScaleY(0.5f);
                    rating.setRating(commentaires.get(i).getNote());//.setNumStars(2);
                    numberStar += commentaires.get(i).getNote();
                    rating.setEnabled(false);
                    LinearLayout.LayoutParams layoutParamsRating = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParamsRating.setMarginStart(85);
                    linear.addView(rating,layoutParamsRating);

                    TextView d2 = new TextView( DetailParcJardin.this);
                    d2.setText(commentaires.get(i).getMessage());
                    d2.setTextSize(15);

                    d2.setPadding(40,0,1,0);

                    linear.addView(d2);



                    linearH.addView(linear);
                    layoutCommentaire.addView(linearH);



                }
                try{
                    ratingBarEtoile.setRating(numberStar/commentaires.size());
                }catch( ArithmeticException e){
                    ratingBarEtoile.setRating(0);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("test error");
                Toast.makeText(getApplication()," Oops probleme get Image !!",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean DonnerAvis(View v){
        FragmentCommentaire dfragmenet = new FragmentCommentaire();
        dfragmenet.setIdParcJardin(parcJardin_getId);
        dfragmenet.show(fragmentManager,"Commentaire : ");

        return true;
    }

    public boolean GetAllComments(View v){

        //All_comments description_fragment = new All_comments();
     //   description_fragment.setIdParcJardin(parcJardin_getId);
        //description_fragment.setDescription(description.getText().toString());

        //description_fragment.setDescription(parcJardin_getDescription);

       // description_fragment.show(fragmentManager,"description : ");


        Intent intent = new Intent(DetailParcJardin.this, All_comments.class);
        intent.putExtra("NameParcJardinSelectionner", NameParcJardinSelectionner);
        System.out.println("NameParcJardinSelectionner >>>>>>>>>> "+ NameParcJardinSelectionner);
        startActivity(intent);
        return false;
      //  System.out.println("vvvvvv GetAllComments vvvvvvvvv"+description.getText().toString());

    }


    /**
     * on click sur button itineraire return maps
     * @param v
     */
    public void itineraire(View v){

        Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(URL_GOOGLEMAPS+"&query="+this.latitude+","+this.longitude));
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void Suite(View v){
        Description_fragment description_fragment = new Description_fragment();

        description_fragment.setDescription(description.getText().toString());

        description_fragment.setDescription(parcJardin_getDescription);

        description_fragment.show(fragmentManager,"description : ");
        System.out.println("vvvvvvvvvvvvvvv"+description.getText().toString());
    }

}
