package hr.fer.zemris.java.tecaj.zi.naredbe;

import hr.fer.zemris.java.tecaj.zi.Krug;
import hr.fer.zemris.java.tecaj.zi.ModelCrteza;

public class Dodaj implements Naredba {

    @Override
    public void izvrsiNaredbu(String parametri, ModelCrteza model) {
        Krug krug = Krug.parseKrug(parametri);
        model.dodajKrug(krug.getCx(), krug.getCy(), krug.getR(),
                krug.getObrub(), krug.getIspuna());
    }

}
