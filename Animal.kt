open class Animal(val id : Int, val nombre : String, val especie : String, var edad : Int){

    open fun mostrarDetalles() : String{
        return "Datos del animal \n Nombre: $nombre \n Especie: $especie \n Edad: $edad"
    }






}