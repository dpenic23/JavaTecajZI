package hr.fer.zemris.java.tecaj.zi.naredbe;

import hr.fer.zemris.java.tecaj.zi.ModelCrteza;

public class Diselektiraj implements Naredba {

    @Override
    public void izvrsiNaredbu(String parametri, ModelCrteza model) {
        model.postaviSelektirani(-1);
    }

}
