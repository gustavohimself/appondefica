package com.appondefica;

public class Municipio {
    public int id;
    public String nome;
    public Microrregiao microrregiao;
    public RegiaoImediata regiaoImediata;

    private class Mesorregiao{
        public int id;
        public String nome;
        public UF UF;
    }

    private class Microrregiao{
        public int id;
        public String nome;
        public Mesorregiao mesorregiao;
    }

    private class Regiao{
        public int id;
        public String sigla;
        public String nome;
    }

    private class RegiaoImediata{
        public int id;
        public String nome;
        public RegiaoIntermediaria regiaoIntermediaria;
    }

    private class RegiaoIntermediaria{
        public int id;
        public String nome;
        public UF UF;
    }

    private class UF{
        public int id;
        public String sigla;
        public String nome;
        public Regiao regiao;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}


