package hr.fer.zemris.java.tecaj.zi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class PlocaSKrugovima extends JComponent implements
        PromatracModelaCrteza {

    private static final long serialVersionUID = 1L;

    private ModelCrteza model;

    public PlocaSKrugovima(ModelCrteza model) {
        this.model = model;
        this.model.dodajPromatraca(this);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                int najblizi = model.najblizi((int) p.getX(), (int) p.getY());
                if(najblizi != -1){
                    model.postaviSelektirani(najblizi);
                }
            }

        });
    }

    @Override
    public void dogodilaSePromjena(ModelCrteza model) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Insets insets = getInsets();
        Dimension size = getSize();

        // Define rectangle for painting this component
        Rectangle rectangle = new Rectangle(insets.left, insets.top, size.width
                - insets.right - insets.left, size.height - insets.top
                - insets.bottom);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(rectangle.x, rectangle.y, rectangle.width,
                rectangle.height);

        int numberOfObjects = model.brojKrugova();

        for (int index = 0; index < numberOfObjects; index++) {
            model.dohvati(index).paintComponent(g2d);
        }

    }

}
