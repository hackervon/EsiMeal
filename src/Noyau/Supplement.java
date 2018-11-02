package Noyau;

public class Supplement {
    private String nom;
    private  double prix;
    private int nbCalorie;

    public Supplement(String nom, double prix, int nbCalorie) {
        this.nom = nom;
        this.prix = prix;
        this.nbCalorie = nbCalorie;
    }

    @Override
    public String toString() {
        return nom+"  "+prix+"  "+nbCalorie;
    }
}
