open class Vehiculo(val id : String, val modelo : String, var nivelBateria : Int, var precioMinuto : Int) {

    open fun mostrarDetalles() : String {
        return "ID: $id; Modelo: $modelo, Nivel de batería: $nivelBateria, Precio por minuto: $precioMinuto"
    }

}
