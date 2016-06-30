package hr.fer.zemris.java.tecaj.zi.naredbe;

import hr.fer.zemris.java.tecaj.zi.ModelCrteza;

public class Obrisi implements Naredba {

    @Override
    public void izvrsiNaredbu(String parametri, ModelCrteza model) {
        if(parametri.isEmpty()){
            int selektirani = model.dohvatiIndeksSelektiranogKruga();
            if(selektirani >= 0){
                model.ukloniKrug(selektirani);
            }
        }else{
            model.ukloniKrug(Integer.parseInt(parametri));
        }
    }

}
