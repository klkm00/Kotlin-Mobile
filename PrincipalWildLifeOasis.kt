import kotlinx.coroutines.*

sealed class EstadoAlimentacion{
    object Alimentando : EstadoAlimentacion()
    data class Alimentado(val animal: Animal) : EstadoAlimentacion()
    data class NoEncontrado(val id : Int, val mensaje : String) : EstadoAlimentacion()
}

suspend fun alimentarAnimal(id :Int, animales : List<Animal>) : EstadoAlimentacion {
    delay(2000L)
    val encontrado = animales.find{ it.id == id }
    if(encontrado!=null){
        return EstadoAlimentacion.Alimentado(encontrado)
    } else {
        return EstadoAlimentacion.NoEncontrado(id,"Animal con id $id no encontrado")
    }
}

fun main(){

    val animalesEnZoologico = listOf(
        Mamifero(1, "León", "León Africano", 5, "Carnívoro"),
        Ave(2, "Águila", "Águila Calva", 3, "Vuela alto"),
        Mamifero(3, "Koala", "Koala Gris", 4, "Herbívoro"),
        Ave(4, "Pingüino", "Pingüino Emperador", 6, "No vuela")
    )

    //CENSO -> CANTIDAD TOTAL DE ANIMALES
    val totalAnimales = animalesEnZoologico.size
    println("Total de animales: $totalAnimales")

    //CANTIDAD DE AVES QUE NO VUELAN
    //val avesNoVuelan =animalesEnZoologico.filter{ave -> ave is Ave && ave.capacidadVuelo.equals("No vuela")}.count()
    //println("Total de animales: $avesNoVuelan")
    val avesNoVuelan  = animalesEnZoologico.filterIsInstance<Ave>().count{it.capacidadVuelo.equals("No vuela", ignoreCase = true)}
    println("Total de animales: $avesNoVuelan")

    //MOSTRAR TODOS LOS ANIMALES
    animalesEnZoologico.forEach{println(it.mostrarDetalles())}

    runBlocking {
        val resultadoAnimal1 = alimentarAnimal(4, animalesEnZoologico)
        // val resultadoAnimal2 =alimentarAnimal(6, animalesEnZoologico)
        when (resultadoAnimal1){
            is EstadoAlimentacion.Alimentado -> {
                println("Animal alimentado: ${resultadoAnimal1.animal.mostrarDetalles()}")
            }
            is EstadoAlimentacion.NoEncontrado -> {
                println("El id ${resultadoAnimal1.id} --- ${resultadoAnimal1.mensaje}")
            }
            is EstadoAlimentacion.Alimentando -> {
                println("Alimentando al animal")
            }
        }
    }
}