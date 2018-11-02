package Noyau;

public enum TypeTable {
    Interieur,Exterieur;

    @Override
    public String toString() {
        return (this==TypeTable.Interieur) ?"Interieur":"Exterieur" ;
    }
}
