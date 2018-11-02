package Noyau;
public class Boisson extends Mets {
    private TypeBoisson type;
    private String marque;
    private String gout;

    public Boisson() {
    }

    public Boisson(String nom, double prix, int nbCalories, boolean disponibilite) {
        super(nom, prix, nbCalories, disponibilite);
    }
    public Boisson(String nom) {
        super(nom);
    }
}
