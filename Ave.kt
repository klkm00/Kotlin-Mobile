class Ave(id: Int, nombre : String, especie : String, edad : Int, var capacidadVuelo : String) :
    Animal (id, nombre, especie, edad) {

    override fun mostrarDetalles(): String {
        return "[Ave] ${super.mostrarDetalles()}, Capacidad de vuelo: $capacidadVuelo"
    }
}