package com.fr.parcjardinlille.Services;

import com.fr.parcjardinlille.models.Commentaire;
import com.fr.parcjardinlille.models.ParcJardin;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface Service {

    public static final String API_URL = "http://parc-jardin-lille.herokuapp.com";//My ip machine :8080
   // public static final String API_URL = "http://192.168.0.18:8080";//My ip machine :8080



    /**
     * retourn Tous les ParcJardins
     * @param callback
     */
    @GET("/parcjardins")
    void getAllParc(Callback<List<ParcJardin>> callback);

    /**
     * Retourn un parcJardin de nom="nom"
     * @param parcjardinByName
     * @param callback
     */
    @GET("/parcjardin/{nom}")
    void getParcJardinnByName(@Path("nom") String parcjardinByName, Callback<ParcJardin> callback);

    /**
     * Retourn un parcJardin avec id ="id"
     * @param parcjardinById
     * @param callback
     */
    @GET("/parcjardin/parcjardinId/{id}")
    void getParcJardinById(@Path("id") Long parcjardinById,Callback<ParcJardin> callback);

    /**
     * return tous les parcJardin avec le nom de services = "monument `| etude | sport ....."
     * @param service
     * @param callback
     */
    @GET("/services/{nom}/parcjardins")
    void getParcJardinnService(@Path("nom") String service,Callback<List<ParcJardin>> callback);

    /**
     * Recherche par nom ou type ou adresse
     * @param search
     * @param callback
     */
    @GET("/parcjardins/{searchByName}")
    void getParcJardinnSearch(@Path("searchByName") String search, Callback<List<ParcJardin>> callback);

    /**
     * return les commentairs d'un parcjardin id = "id"
     * @param id
     * @param callback
     */
    @GET("/parcjardin/{id}/commentaires")
    void getAllCommentaireOfParcJardinById(@Path("id") Long id, Callback<List<Commentaire>> callback);


    /**
     *  insert dans la base de  donner un new commentaire
     *
     * @param id
     * @param utilisateur
     * @param note
     * @param message
     * @param callback
     */
    @POST("/addcommentaire/{idpj}/{nameUtilisateur}/{note}/{message}")
    void addNewCommentaire(@Path("idpj")Long id , @Path("nameUtilisateur")String utilisateur, @Path("note") int note,@Path("message")String message ,Callback<Commentaire> callback);



}
