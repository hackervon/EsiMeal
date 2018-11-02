package Noyau;

import java.util.List;

public class Repas extends Mets{

    protected TypeRepas type;
    protected List<Supplement> sp;
    protected List<String> ingredients;

    public void setType(TypeRepas type) {
        this.type = type;
    }

    public Repas() {
    }


    public Repas(String nom) {
        super(nom);
    }
    public Repas(String nom, double prix, int nbCalories, boolean disponibilite) {
        super(nom, prix, nbCalories, disponibilite);
    }
    public Repas(String nom, double prix, int nbCalories, boolean disponibilite,TypeRepas type) {
        super(nom, prix, nbCalories, disponibilite);
        this.type=type;
    }

}
