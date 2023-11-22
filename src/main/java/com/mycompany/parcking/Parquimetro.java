
package com.mycompany.parcking;
// importaciones 
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class Parquimetro {
    private final ArrayList<Vehiculo> vehiculos;
        private final double TARIFA_POR_MINUTOS = 35.0; // Tarifa por minutos en pesos chilenos

        //Constructor Parquimetro
            public Parquimetro() {
                vehiculos = new ArrayList<>();
    }

    //Método getVehiculos
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
    
    //Método registrarVehiculo
    public void registrarVehiculo(String placa) throws Exception {
        if (placa == null || placa.isEmpty()) {
            throw new Exception("La placa no puede estar vacía.");
            }
                Vehiculo vehiculo = new Vehiculo(placa);
                    vehiculos.add(vehiculo);
                       
    }

    
    //Método calcularMonto
    public double calcularMonto(String placa) throws Exception {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                long minutos = ChronoUnit.MINUTES.between(v.getHoraEntrada(), LocalDateTime.now());
                    return Math.max(minutos, 1) * TARIFA_POR_MINUTOS; // Cobra por minutos
                    }
                        }
                            throw new Exception("Vehículo no encontrado.");                                         
    }
}



