
package com.mycompany.parcking;

//IMPORTACIONES
import java.time.LocalDate;
import java.time.LocalTime;




//Constructor Vehiculo
class Vehiculo {
    private final String placa;
    private final LocalTime horaEntrada;
    private final LocalDate fechaEntrada;
    public Vehiculo(String placa) {
    this.placa = placa;
    this.horaEntrada = LocalTime.now();
    this.fechaEntrada =LocalDate.now();
    
    }
    //Método getPlaca
    public String getPlaca() {
        return placa;
    }
    //Método getHoraEntrada
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
    //Método getfechaEntrada
    public LocalDate getfechaEntrada() {
        return fechaEntrada;
    }
    
    
}





    
   






