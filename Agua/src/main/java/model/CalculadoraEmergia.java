package model;

import java.util.Arrays;
import java.util.List;

public class CalculadoraEmergia {

    public double calcularEmergiaAguaChuva() {
        List<Etapa> etapas = Arrays.asList(
            new Etapa("Captação", Arrays.asList(new Recurso("Gravidade", 9.81, 8.89e3))),
            new Etapa("Armazenamento", Arrays.asList(new Recurso("Bomba", 435600, 8.89e3))),
            new Etapa("Distribuição", Arrays.asList(new Recurso("Bomba", 720, 8.89e3)))
        );
        return etapas.stream().mapToDouble(Etapa::calcularEmergiaTotal).sum();
    }

    public double calcularEmergiaAguaTratada() {
        List<Etapa> etapas = Arrays.asList(
            new Etapa("Captação + Tratamento", Arrays.asList(new Recurso("Estação pública", 3600, 8.89e3))),
            new Etapa("Armazenamento", Arrays.asList(new Recurso("Bomba", 435600, 8.89e3))),
            new Etapa("Distribuição", Arrays.asList(new Recurso("Bomba", 75.6, 8.89e3)))
        );
        return etapas.stream().mapToDouble(Etapa::calcularEmergiaTotal).sum();
    }

    public double calcularEmergiaAguaSubterranea() {
        List<Etapa> etapas = Arrays.asList(
            new Etapa("Extração", Arrays.asList(new Recurso("Bomba", 3600, 8.89e3))),
            new Etapa("Tratamento", Arrays.asList(new Recurso("Cloração/Filtração", 403.2, 8.89e3))),
            new Etapa("Armazenamento", Arrays.asList(new Recurso("Bomba", 435600, 8.89e3))),
            new Etapa("Distribuição", Arrays.asList(new Recurso("Bomba", 75.6, 8.89e3)))
        );
        return etapas.stream().mapToDouble(Etapa::calcularEmergiaTotal).sum();
    }
    
    public List<Etapa> getEtapasAguaChuva() {
        return Arrays.asList(
            new Etapa("Captação", Arrays.asList(new Recurso("Gravidade", 9.81, 8.89e3))),
            new Etapa("Armazenamento", Arrays.asList(new Recurso("Bomba", 435600, 8.89e3))),
            new Etapa("Distribuição", Arrays.asList(new Recurso("Bomba", 720, 8.89e3)))
        );
    }

    public List<Etapa> getEtapasAguaTratada() {
        return Arrays.asList(
            new Etapa("Captação + Tratamento", Arrays.asList(new Recurso("Estação pública", 3600, 8.89e3))),
            new Etapa("Armazenamento", Arrays.asList(new Recurso("Bomba", 435600, 8.89e3))),
            new Etapa("Distribuição", Arrays.asList(new Recurso("Bomba", 75.6, 8.89e3)))
        );
    }

    public List<Etapa> getEtapasAguaSubterranea() {
        return Arrays.asList(
            new Etapa("Extração", Arrays.asList(new Recurso("Bomba", 3600, 8.89e3))),
            new Etapa("Tratamento", Arrays.asList(new Recurso("Cloração/Filtração", 403.2, 8.89e3))),
            new Etapa("Armazenamento", Arrays.asList(new Recurso("Bomba", 435600, 8.89e3))),
            new Etapa("Distribuição", Arrays.asList(new Recurso("Bomba", 75.6, 8.89e3)))
        );
    }

}
