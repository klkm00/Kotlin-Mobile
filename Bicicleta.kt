class Bicicleta(id : String, modelo : String, nivelBateria : Int, precioMinuto : Int, var numeroMarchas : Int) :
    Vehiculo (id, modelo, nivelBateria, precioMinuto){

    override fun mostrarDetalles(): String {
        return "[Bicicleta] ${super.mostrarDetalles()}:, Marchas: $numeroMarchas"
    }

}