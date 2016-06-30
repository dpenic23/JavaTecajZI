package hr.fer.zemris.java.tecaj.zi.web;

public class Record {
    private String redak;

    public Record(String redak) {
        this.redak = redak;
    }

    public Record() {
    }

    public String getRedak() {
        return redak;
    }

    public void setRedak(String redak) {
        this.redak = redak;
    }

    @Override
    public String toString() {
        return redak;
    }
}
