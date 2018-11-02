package Noyau;

import java.time.LocalDate;
import java.util.List;

public class Event extends Command {
    TypeEvenement typeEvent;
    Menu menuEvent;

    public Event(Client client, int nbPersonne, LocalDate heure, List<Menu> menus, TypeEvenement typeEvent, Menu menuEvent) {
        super(client, nbPersonne, heure, menus);
        this.typeEvent = typeEvent;
        this.menuEvent = menuEvent;
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }
}
