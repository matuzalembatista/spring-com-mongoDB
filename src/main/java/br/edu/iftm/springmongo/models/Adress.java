package br.edu.iftm.springmongo.models;

public class Adress {
    private String street;
    private Integer number;

    public Adress() {
    }

    public Adress(String street, Integer number) {
        this.street = street;
        this.number = number;
    }
}
