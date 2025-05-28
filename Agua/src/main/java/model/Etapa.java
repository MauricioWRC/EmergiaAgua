package model;

import java.util.List;

public class Etapa {
    private String nome;
    private List<Recurso> recursos;

    public Etapa(String nome, List<Recurso> recursos) {
        this.nome = nome;
        this.recursos = recursos;
    }

    public double calcularEmergiaTotal() {
        return recursos.stream().mapToDouble(Recurso::calcularEmergia).sum();
    }

    public String getNome() {
        return nome;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }
}
	