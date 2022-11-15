package com.appondefica;

public class Estado {
    public int id;
    public String sigla;
    public String nome;
    public Regiao regiao;

    private class Regiao {
        public int id;
        public String sigla;
        public String nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
