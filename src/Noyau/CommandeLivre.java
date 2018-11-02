package Noyau;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class CommandeLivre extends Command {
    protected  String address;
    protected int distance;

    public CommandeLivre(Client client, int nbPersonne, LocalDate heure, List<Menu> menus, String address) {
        super(client, nbPersonne, heure, menus);
        this.address = address;
    }

    @Override
    public boolean isValid() {
        //Calendar calender = GregorianCalendar.getInstance();
       // calender.setTime(heure);
      /*  if((heure.getTime() -  LocalDateTime.now())>=3600000 && calender.get(Calendar.HOUR_OF_DAY) > 11 && address.distance<20)
        {
            return true;
        }else {
            return false;
        }*/
      return true;
    }

    @Override
    public double CalculateMontant() {
        return super.CalculateMontant()+(distance*20);
    }
}
