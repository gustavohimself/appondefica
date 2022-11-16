package com.appondefica;

import java.io.Serializable;

public class CEP implements Serializable {
    public String cep;
    public String logradouro;
    public String complemento;
    public String bairro;
    public String localidade;
    public String uf;
    public String ibge;
    public String gia;
    public String ddd;
    public String siafi;

    @Override
    public String toString() {
        return logradouro +  ", " + bairro + ", " + localidade + " - " + uf + ", " + cep;
    }
}
