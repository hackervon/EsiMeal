package Noyau;

 abstract public class Mets {
    protected  String nom;
    protected double prix;
    protected  int nbCalories;
    protected boolean disponibilite;

     public Mets() {
     }

     public Mets(String nom) {
         this.nom=nom;
     }
     public String getNom() {
         return nom;
     }

     public void setNom(String nom) {
         this.nom = nom;
     }

     public double getPrix() {
         return prix;
     }

     public void setPrix(double prix) {
         this.prix = prix;
     }

     public int getNbCalories() {
         return nbCalories;
     }

     public void setNbCalories(int nbCalories) {
         this.nbCalories = nbCalories;
     }

     public boolean isDisponibilite() {
         return disponibilite;
     }

     public void setDisponibilite(boolean disponibilite) {
         this.disponibilite = disponibilite;
     }

     public Mets(String nom, double prix, int nbCalories, boolean disponibilite) {
         this.nom = nom;
         this.prix = prix;
         this.nbCalories = nbCalories;
         this.disponibilite = disponibilite;
     }
 }
