package Noyau;

import java.util.List;

public class Client {
    protected String nom;
    protected String pronom;
    protected String nTelephone;
    protected Panier panier;
    protected boolean etutiant;
    protected int nbCommandeEFfectue=0;

    public Client() {
    }




    public Client(String nom, String pronom, String nTelephone, boolean etutiant) {
        this.nom = nom;
        this.pronom = pronom;
        this.nTelephone = nTelephone;
        this.etutiant = etutiant;
    }

    public String getNom() {
        return nom;
    }

    public String getPronom() {
        return pronom;
    }

    public String getNTelephone() {
        return nTelephone;
    }

    public Panier getPanier() {
        return panier;
    }

    public boolean isEtutiant() {
        return etutiant;
    }

    public int getNbCommandeEFfectue() {
        return nbCommandeEFfectue;
    }

    public void choisirMenus(Menu[] menus){
        for(Menu m:menus) {
            panier.ajouterMenu(m);
        }
    }

    public void confirmerCmd(Command cmd){
        for(Menu m:panier.panier)
            cmd.menus.add(m);
        try {
            Main.esiMeal.RecevoirCommande(cmd);
            nbCommandeEFfectue++;
        }
        catch(nonValidCommandException e){
        }
    }

}
