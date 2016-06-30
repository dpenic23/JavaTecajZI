package hr.fer.zemris.java.tecaj.zi;

import javax.swing.JLabel;

public class LabelaBrojKrugova extends JLabel implements PromatracModelaCrteza {

    private static final long serialVersionUID = 1L;

    @Override
    public void dogodilaSePromjena(ModelCrteza model) {
        this.setText("Broj krugova: " + String.valueOf(model.brojKrugova()));
    }

}
