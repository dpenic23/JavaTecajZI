package hr.fer.zemris.java.tecaj.zi;

import hr.fer.zemris.java.tecaj.zi.naredbe.Diselektiraj;
import hr.fer.zemris.java.tecaj.zi.naredbe.Dodaj;
import hr.fer.zemris.java.tecaj.zi.naredbe.Naredba;
import hr.fer.zemris.java.tecaj.zi.naredbe.Obrisi;
import hr.fer.zemris.java.tecaj.zi.naredbe.Selektiraj;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String DATA_FILE_PATH = "krugovi.txt";

    private static Map<String, Naredba> naredbe = new HashMap<>();

    static {
        naredbe.put("obrisi", new Obrisi());
        naredbe.put("dodaj", new Dodaj());
        naredbe.put("selektiraj", new Selektiraj());
        naredbe.put("diselektiraj", new Diselektiraj());
    }

    private ModelCrteza model;

    public Main() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800, 550);
        setLocation(50, 50);
        setTitle("Krugovi");

        initGUI();
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPanel);

        model = new ModelCrtezaImpl();
        model.kreirajModelIzDatoteke(Paths.get(DATA_FILE_PATH));

        PlocaSKrugovima ploca = new PlocaSKrugovima(model);
        mainPanel.add(ploca, BorderLayout.CENTER);

        JPanel northPanel = new JPanel(new GridLayout(1, 2));
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        LabelaBrojKrugova labelaBroj = new LabelaBrojKrugova();
        labelaBroj.setText("Broj krugova: "
                + String.valueOf(model.brojKrugova()));
        LabelaSelektiraniKrug labelaSelektirani = new LabelaSelektiraniKrug();
        model.dodajPromatraca(labelaBroj);
        model.dodajPromatraca(labelaSelektirani);
        northPanel.add(labelaBroj);
        northPanel.add(labelaSelektirani);

        mainPanel.add(northPanel, BorderLayout.PAGE_START);

        JPanel southPanel = new JPanel(new BorderLayout(10, 10));
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea unosNaredbe = new JTextArea();
        JButton izvrsi = new JButton("Izvrsi");
        southPanel.add(unosNaredbe, BorderLayout.CENTER);
        southPanel.add(izvrsi, BorderLayout.LINE_END);

        izvrsi.addActionListener((e) -> {
            String[] params = unosNaredbe.getText().split("\\s+", 2);
            String imeNaredbe = params[0].trim().toLowerCase();
            String parametriNaredbe = "";

            if (params.length == 2) {
                parametriNaredbe = params[1].trim();
            }

            Naredba naredba = naredbe.get(imeNaredbe);
            if (naredba == null) {
                JOptionPane.showMessageDialog(Main.this,
                        "Naredba nije podrzana!", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }

            try {
                naredba.izvrsiNaredbu(parametriNaredbe, model);
                unosNaredbe.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Main.this, "Neispravna naredba!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        mainPanel.add(southPanel, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager
                        .getSystemLookAndFeelClassName());
            } catch (Exception ignorable) {
            }

            JFrame frame = new Main();
            frame.setVisible(true);
        });
    }

}
