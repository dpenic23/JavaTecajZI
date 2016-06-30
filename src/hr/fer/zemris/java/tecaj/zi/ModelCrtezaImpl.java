package hr.fer.zemris.java.tecaj.zi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ModelCrtezaImpl implements ModelCrteza {

    private List<PromatracModelaCrteza> promatraci = new ArrayList<>();

    private List<Krug> krugovi = new ArrayList<>();
    private int selektirani = -1;

    // dodaje novi krug u model:
    public void dodajKrug(int cx, int cy, int r, Color obrub, Color ispuna) {
        Krug krug = new Krug(cx, cy, r, obrub, ispuna);
        krugovi.add(krug);

        objaviPromjenu();
    }

    // vraća krug koji model pamti pod navedenim rednim brojem:
    public Krug dohvati(int index) {
        return krugovi.get(index);
    }

    // vraća broj krugova u modelu:
    public int brojKrugova() {
        return krugovi.size();
    }

    // briše iz modela zadani krug:
    public void ukloniKrug(int index) {
        krugovi.remove(index);

        objaviPromjenu();
    }

    // postavi selektirani krug (samo jedan može biti selektiran); -1 postavlja
    // da ništa nije selektirano:
    public void postaviSelektirani(int index) {
        if (selektirani != -1) {
            krugovi.get(selektirani).diselektiraj();
        }

        selektirani = index;
        if (index != -1) {
            krugovi.get(index).selektiraj();
        }

        objaviPromjenu();
    }

    // vraća indeks selektiranog kruga ili -1 ako ništa nije selektirano:
    public int dohvatiIndeksSelektiranogKruga() {
        return selektirani;
    }

    // dohvaća redni broj kruga najbližeg(1); vidi pojašnjenje ispod predanoj
    // točki:
    public int najblizi(int tx, int ty) {
        Point current = new Point(tx, ty);
        int udaljenost = 6;
        int index = -1;
        
        for(int i = 0; i < krugovi.size(); i++){
            Krug krug = krugovi.get(i);
            Point center = new Point(krug.getCx(), krug.getCy());
            int tmpUdalj = (int) center.distance(current);
            if(tmpUdalj <= 5 && tmpUdalj < udaljenost){
                udaljenost = tmpUdalj;
                index = i;
            }
        }
        
        return index;
    }

    @Override
    public void dodajPromatraca(PromatracModelaCrteza promatrac) {
        promatraci.add(promatrac);
    }

    @Override
    public void ukloniPromatraca(PromatracModelaCrteza promatrac) {
        promatraci.remove(promatrac);
    }

    @Override
    public void objaviPromjenu() {
        for (PromatracModelaCrteza p : promatraci) {
            p.dogodilaSePromjena(this);
        }
    }

    @Override
    public void kreirajModelIzDatoteke(Path file) {
        try {
            List<String> lines = Files.readAllLines(file);

            for (String line : lines) {
                try {
                    Krug krug = Krug.parseKrug(line);
                    krugovi.add(krug);
                } catch (Exception ignorable) {
                    ignorable.printStackTrace();
                }
            }
        } catch (IOException ignorable) {
        }
    }

    public byte[] generirajSliku(Dimension size) throws IOException {
        BufferedImage image = new BufferedImage(size.width, size.height,
                BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(232, 232, 232));
        g2d.fillRect(0, 0, size.width, size.height);

        int numberOfObjects = this.brojKrugova();

        for (int index = 0; index < numberOfObjects; index++) {
            this.dohvati(index).paintComponent(g2d);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);

        return baos.toByteArray();
    }

}
