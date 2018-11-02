package Noyau;

import java.util.*;

public class EsiMeal {
    private static final int nombreDuTableDuRestaurant = 50;
    private static final int nombreDuChaiseDuRestaurant = 200;

    public HashMap<Integer,Client> client = new HashMap<Integer, Client>();
    protected int nbChaiseLibre=nombreDuChaiseDuRestaurant;
    protected int nbTableLibre=nombreDuTableDuRestaurant;
    public List<Mets> metsDisponible= new ArrayList<>();
    public List<Command> listAttente=new ArrayList<>();
    public Stack<Command> listEffectue= new Stack<>();


    public void RecevoirCommande(Command cmd) throws nonValidCommandException{
        if(cmd.isValid()) {
         //   cmd.identifant=;
            listAttente.add(cmd);
        } else throw new nonValidCommandException();
    }

    public void EffectuerCommande(){
        if(listAttente.isEmpty()) return;
        int minIdentifiant=listAttente.get(0).identifant;
        for(Command s:listAttente){
        if(s.identifant <minIdentifiant) minIdentifiant=s.identifant;
        }
        Command commandEffectue=null;
        for(Command s:listAttente){
            if(s.identifant==minIdentifiant){
                if(s instanceof CommandeLivre){
                    commandEffectue=s;
                    break;
                }
            }
        }
        if(commandEffectue==null){
            for(Command s:listAttente){
                if(s.identifant==minIdentifiant) {
                    commandEffectue = s;
                    break;
                }
            }
        }
        nbChaiseLibre-=commandEffectue.nbPersonne;
        nbTableLibre--;

        listAttente.remove(commandEffectue);
        listEffectue.push(commandEffectue);
    }

    public int nbCommandesEffectues(
    ){
        return listAttente.size();
    }
    public  double montantCommandesEffectues(){
    double sum=0;
    for (Command c:listAttente) sum+=c.CalculateMontant();
        return sum;
    }
    public int nbCommandesSurPlace(
    ){
        int sum=0;
        for (Command c:listAttente) if(c instanceof CommandeSurPlace) sum++ ;;
        return sum;
    }
    public  double montantCommandesSurPlace(
    ){
        double sum=0;
        for (Command c:listAttente) if(c instanceof CommandeSurPlace) sum+=c.CalculateMontant();
        return sum;

    }
    public int nbCommandesLivre(

    ){
        int sum=0;
        for (Command c:listAttente) if(c instanceof CommandeLivre) sum++ ;;
        return sum;

    }
    public  double montantCommandesLivre(

    ){
        double sum=0;
        for (Command c:listAttente) if(c instanceof CommandeLivre) sum+=c.CalculateMontant();
        return sum;

    }
    public int nbCommandesEvt(){
        return 0;
    }
    public  double montantCommandesEvt(){
        return 0;
    }
    public double montantTotaleReductions(){
        return 0;
    }
    public double montantTotaleReductionsFidelite(){
        return 0;
    }
    public double montantTotaleReductionsEtudiant(){
        return 0;
    }
    public double montantTotaleReductionsGroupe(){
        return 0;
    }
    public double montantTotaleReductionsEvent(){
        return 0;
    }
    public Mets metPlusCommande(){ return null;
    }
    public Mets metMoinCommande(){
        return null;
    }
    public Client clientPlusCommande(){
        return null;
    }
    public Client clientPlusCommandeSurPlace(){
        return null;
    }
    public Client clientPlusCommandeEvent(){
        return null;
    }
    public Client clientPlusCommandeLivre(){
        return null;
    }
    public  Client clientPlusReduction(){
        return  null;
    }
    public  Client clientPlusReductionFidelite(){
        return  null;
    }
    public  Client clientPlusReductionEtudiant(){
        return  null;
    }
    public  Client clientPlusReductionGroupe(){
        return  null;
    }
    public  Client clientPlusReductionEvt(){
        return  null;
    }

}
