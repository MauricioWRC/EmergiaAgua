package controller;

public class EmergiaCalculator {
	
    public double calcularEmergiaAguaTratada(double volumeLitros) {
        return (AguaTratadaUrbana.emergiaPorLitro() + AguaChuva.emergiaPorLitro()) * volumeLitros;
    }

    public double calcularEmergiaAguaSubterranea(double volumeLitros) {
        return (AguaSubterranea.emergiaPorLitro() + AguaChuva.emergiaPorLitro()) * volumeLitros;
    }

    public double calcularEmergiaAguaChuva(double volumeLitros) {
        return AguaChuva.emergiaPorLitro() * volumeLitros;
    }
}

class AguaTratadaUrbana {
    public static double emergiaPorLitro() {
        return 1.2e4;
    }
}

class AguaSubterranea {
    public static double emergiaPorLitro() {
        return 8.5e3;
    }
}

class AguaChuva {
    public static double emergiaPorLitro() {
        return 3.1e3;
    }

}
