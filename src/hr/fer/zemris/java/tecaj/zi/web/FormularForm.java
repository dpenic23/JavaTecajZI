package hr.fer.zemris.java.tecaj.zi.web;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class FormularForm {

    private static Set<String> naredbe = new HashSet<>();

    static {
        naredbe.add("obrisi");
        naredbe.add("dodaj");
        naredbe.add("selektiraj");
        naredbe.add("diselektiraj");
    }

    private String redak;

    Map<String, String> greske = new HashMap<>();

    public FormularForm() {
    }

    public String dohvatiPogresku(String ime) {
        return greske.get(ime);
    }

    public boolean imaPogresaka() {
        return !greske.isEmpty();
    }

    public boolean imaPogresku(String ime) {
        return greske.containsKey(ime);
    }

    public void popuniIzHttpRequesta(HttpServletRequest req) {
        this.redak = pripremi(req.getParameter("naredba"));
    }

    public void popuniIzRecorda(Record r) {
        this.redak = r.getRedak();
    }

    public void popuniURecord(Record r) {
        r.setRedak(redak);
    }

    public void validiraj() {
        greske.clear();

        if (this.redak.isEmpty()) {
            greske.put("naredba", "Naredba nije zadana!");
        } else {
            String[] params = redak.split("\\s+");
            if (!naredbe.contains(params[0].toLowerCase())) {
                greske.put("naredba", "Naredba \"" + redak + "\" nije korektna!");
            }
        }
    }

    private String pripremi(String s) {
        if (s == null)
            return "";
        return s.trim();
    }

    public String getRedak() {
        return redak;
    }

    public void setRedak(String redak) {
        this.redak = redak;
    }

}
