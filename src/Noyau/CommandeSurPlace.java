package Noyau;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CommandeSurPlace extends Command {
    private TypeTable table;

    public TypeTable getTable() {
        return table;
    }

    public CommandeSurPlace(Client client, int nbPersonne, LocalDate heure, List<Menu> menus, TypeTable table) {
        super(client, nbPersonne, heure, menus);
        this.table = table;

    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

    @Override
    public double CalculateMontant() {
        double montant=super.CalculateMontant();
        return (table==TypeTable.Exterieur)? montant+montant*0.05 : montant;
    }
}
