package Noyau;

import java.util.List;

public class Panier {
    protected List<Menu> panier;

    public  void ajouterMenu(Menu s){
        panier.add(s);
    }
    public  void SupprimerMenu(Menu s){
        panier.remove(s);

    }
    public double montantTotal(){
        double total=0;
        for(Menu m:panier) {
            total+=m.calculateMontantMenu();
        }
        return total;
    }
    public double montantSepare(int indice){
        return panier.get(indice).calculateCaloriesMenu();
    }
    public double calorieTotal(){
        double total=0;
        for(Menu m:panier) {
            total+=m.calculateMontantMenu();
        }
        return total;
    }
    public double calorieSepare(int indice){
        return panier.get(indice).calculateCaloriesMenu();
    }
}
