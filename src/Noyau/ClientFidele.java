package Noyau;

import java.util.List;

public class ClientFidele extends Client {
    private int code;
    private List<String> addresses;

    public ClientFidele() {
    }



    public ClientFidele(String nom, String pronom, String nTelephone, boolean etutiant, List<String> li) {
        super(nom, pronom, nTelephone, etutiant);
    }

    public void confirmerCmd(Command cmd, boolean sauvgarde, String address) {
        if (cmd instanceof CommandeLivre) {
                if (sauvgarde) {
                    addresses.add(address);
                }
        }
        super.confirmerCmd(cmd);
    }
}
