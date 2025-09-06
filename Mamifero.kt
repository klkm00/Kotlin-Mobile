class Mamifero(id : Int, nombre : String, especie : String, edad : Int, var dieta : String) :
    Animal (id, nombre, especie, edad) {

    override fun mostrarDetalles(): String {
        return "[Mamifero]${super.mostrarDetalles()}, Dieta: $dieta"
    }

}