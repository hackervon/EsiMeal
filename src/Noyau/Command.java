package Noyau;

import java.time.LocalDate;
import java.util.*;

public class Command {
    protected  Client client;
    protected  int nbPersonne;
    protected  LocalDate heure;
    protected List<Menu> menus;
    protected int identifant;
    protected int reduction=0;


    public Command() {
    }



    public String getNom() {
        return client.nom;
    }
    public String getPrenom() {
        return client.pronom;
    }
    public String getNTelephone() {
        return client.nTelephone;
    }

    public String getMenuss() {
        String strig = new String();
        for (int i = 0; i < menus.size(); i++) {
            strig+="[";
            for (int j = 0; j < menus.get(i).getMenu().size(); j++) {
                strig+=menus.get(i).getMenu().get(j).nom+",";
            }
            strig+="] ";
        }
        return strig;
    }


    public Command(Client client, int nbPersonne, LocalDate heure, List<Menu> menus) {
        this.client = client;
        this.nbPersonne = nbPersonne;
        this.heure = heure;
        this.menus = menus;
        reduction=reduire();
    }

    public boolean isValid() {
        return true;
    }

    public double CalculateMontant(){
        double d=0;
        for(Menu m:menus) for(Mets r:m.menu) d+=r.prix;
        return d;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public void setHeure(LocalDate heure) {
        this.heure = heure;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public void setIdentifant(int identifant) {
        this.identifant = identifant;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public Client getClient() {
        return client;
    }

    public int getNbPersonne() {
        return nbPersonne;
    }

    public LocalDate getHeure() {
        return heure;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public int getIdentifant() {
        return identifant;
    }

    public int getReduction() {
        return reduction;
    }

    int reduire(){
        int reduction=0;
        if(client instanceof ClientFidele && client.nbCommandeEFfectue>1) reduction+=5;
        if(this instanceof CommandeLivre && nbPersonne>=4) reduction+=8;
        if(client.etutiant==true) reduction+=8;
        if(this instanceof Event && ((Event) this).nbPersonne>=50) reduction+=10;
        return reduction;
    }
}