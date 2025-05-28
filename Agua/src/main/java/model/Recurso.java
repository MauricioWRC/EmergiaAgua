package model;

public class Recurso {
    private String nome;
    private double energiaJoules;
    private double transformidadeSejPorJ;

    public Recurso(String nome, double energiaJoules, double transformidadeSejPorJ) {
        this.nome = nome;
        this.energiaJoules = energiaJoules;
        this.transformidadeSejPorJ = transformidadeSejPorJ;
    }

    public double calcularEmergia() {
        return energiaJoules * transformidadeSejPorJ;
    }

    public String getNome() {
        return nome;
    }

    public double getEnergiaJoules() {
        return energiaJoules;
    }

    public double getTransformidade() {
        return transformidadeSejPorJ;
    }
}
