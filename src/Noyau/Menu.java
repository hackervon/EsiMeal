package Noyau;

import java.util.List;

public class Menu {
    protected List<Mets> menu;

    public List<Mets> getMenu() {
        return menu;
    }

    void ajouterMet(Mets m){
        menu.add(m);
    }

    public void setMenu(List<Mets> menu) {
        this.menu = menu;
    }

    void supprimerMet(Mets m){
        menu.remove(m);
    }

    public Menu(List<Mets> menu) {
        this.menu = menu;
    }

    public Menu() {
    }

    double calculateMontantMenu(){
        double total=0;
        for(Mets m: menu ){
            total+=m.prix;
        }
        return total;
    }
    double calculateCaloriesMenu(){
        double total=0;
        for(Mets m: menu ){
            total+=m.nbCalories;
        }
        return total;
    }
}
