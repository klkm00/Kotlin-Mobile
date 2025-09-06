class Scooter(id : String, modelo : String, nivelBateria : Int, precioMinuto : Int, var potenciaMotor : Int) :
    Vehiculo (id, modelo, nivelBateria, precioMinuto) {

    override fun mostrarDetalles(): String {
        return "[Scooter] ${super.mostrarDetalles()}, Potencia del motor (kW): $potenciaMotor"
    }
}