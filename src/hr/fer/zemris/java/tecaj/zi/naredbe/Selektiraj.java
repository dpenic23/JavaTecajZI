package hr.fer.zemris.java.tecaj.zi.naredbe;

import hr.fer.zemris.java.tecaj.zi.ModelCrteza;

public class Selektiraj implements Naredba {

    @Override
    public void izvrsiNaredbu(String parametri, ModelCrteza model) {
        int index = Integer.parseInt(parametri);
        int n = model.brojKrugova();
        if(index >= 0 && index < n){
            model.postaviSelektirani(index);
        }
    }

}
