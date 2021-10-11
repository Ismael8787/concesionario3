package es.melit.concesionario3.service.dto;

import java.util.ArrayList;
import java.util.List;

public class CriteriaDTO {

    String marca;

    public CriteriaDTO() {}

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getArray(int i) {
        String[] elementos = this.marca.split(" ");

        return elementos[i];
    }

    public int buscarLongitud() {
        return this.marca.split(" ").length;
    }
}
