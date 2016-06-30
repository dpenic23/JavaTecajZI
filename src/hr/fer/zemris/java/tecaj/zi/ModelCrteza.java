package hr.fer.zemris.java.tecaj.zi;

import java.awt.Color;
import java.nio.file.Path;

public interface ModelCrteza {

    // dodaje novi krug u model:
    void dodajKrug(int cx, int cy, int r, Color obrub, Color ispuna);

    // vraća krug koji model pamti pod navedenim rednim brojem:
    Krug dohvati(int index);

    // vraća broj krugova u modelu:
    int brojKrugova();

    // briše iz modela zadani krug:
    void ukloniKrug(int index);

    // postavi selektirani krug (samo jedan može biti selektiran); -1 postavlja
    // da ništa nije selektirano:
    void postaviSelektirani(int index);

    // vraća indeks selektiranog kruga ili -1 ako ništa nije selektirano:
    int dohvatiIndeksSelektiranogKruga();

    // dohvaća redni broj kruga najbližeg(1); vidi pojašnjenje ispod predanoj
    // točki:
    int najblizi(int tx, int ty);
    
    void dodajPromatraca(PromatracModelaCrteza promatrac);
    
    void ukloniPromatraca(PromatracModelaCrteza promatrac);
    
    void objaviPromjenu();
    
    void kreirajModelIzDatoteke(Path file);

}
