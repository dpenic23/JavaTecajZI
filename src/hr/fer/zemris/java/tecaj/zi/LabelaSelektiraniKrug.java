package hr.fer.zemris.java.tecaj.zi;

import javax.swing.JLabel;

public class LabelaSelektiraniKrug extends JLabel implements
        PromatracModelaCrteza {

    private static final long serialVersionUID = 1L;

    public LabelaSelektiraniKrug() {
        this.setText("Selektirani krug:-");
    }

    @Override
    public void dogodilaSePromjena(ModelCrteza model) {
        int index = model.dohvatiIndeksSelektiranogKruga();
        String text = index == -1 ? "-" : String.valueOf(index);

        this.setText("Selektirani krug: " + text);
    }

}
