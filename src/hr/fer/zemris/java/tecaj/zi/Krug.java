package hr.fer.zemris.java.tecaj.zi;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Krug extends JComponent {

    private static final long serialVersionUID = 1L;

    private int cx;
    private int cy;
    private int r;
    private Color obrub;
    private Color ispuna;
    private boolean selektiran;

    public Krug(int cx, int cy, int r, Color obrub, Color ispuna) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        this.obrub = obrub;
        this.ispuna = ispuna;
        this.selektiran = false;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    public int getR() {
        return r;
    }

    public Color getObrub() {
        return obrub;
    }

    public Color getIspuna() {
        return ispuna;
    }

    public void selektiraj() {
        this.selektiran = true;
    }

    public void diselektiraj() {
        this.selektiran = false;
    }

    public static Krug parseKrug(String parameters)
            throws NumberFormatException {
        String[] params = parameters.split("\\s+");

        if (params.length != 5) {
            throw new NumberFormatException(
                    "Invalid number of arguments, expected 5: " + params.length);
        }

        int centerX = Integer.parseInt(params[0]);
        int centerY = Integer.parseInt(params[1]);
        int radius = Integer.parseInt(params[2]);

        Color obrub = hex2Rgb(params[3]);
        Color ispuna = null;
        if (params[4].equals("-")) {
            ispuna = null;
        } else {
            ispuna = hex2Rgb(params[4]);
        }

        return new Krug(centerX, centerY, radius, obrub, ispuna);
    }

    private static Color hex2Rgb(String colorStr) {
        return new Color(Integer.valueOf(colorStr.substring(0, 2), 16),
                Integer.valueOf(colorStr.substring(2, 4), 16), Integer.valueOf(
                        colorStr.substring(4, 6), 16));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (ispuna != null) {
            Color ci = selektiran ? getComplement(ispuna) : ispuna;
            g.setColor(ci);
            g.fillOval(cx - r, cy - r, 2 * r, 2 * r);
        }

        Color co = selektiran ? getComplement(obrub) : obrub;
        g.setColor(co);
        g.drawOval(cx - r, cy - r, 2 * r, 2 * r);
    }

    private static Color getComplement(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(),
                255 - color.getBlue());
    }
    
    private static String colorToHex(Color color){
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
    
    @Override
    public String toString() {
        String cx = String.valueOf(this.cx);
        String cy = String.valueOf(this.cy);
        String r = String.valueOf(this.r);
        
        String ob = colorToHex(obrub);
        String is = ispuna == null ? "-" : colorToHex(ispuna);
        
        return cx + " " + cy + " " + r + " " + ob + " " + is;
    }
    

}
