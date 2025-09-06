import kotlinx.coroutines.*

sealed class EstadoValidacion{
    data class valida (val vehiculo : Vehiculo) : EstadoValidacion()
    data class noValida (val mensajeError : String) : EstadoValidacion()
}

suspend fun validarVehiculo(id : String, flota List<Vehiculo>) : EstadoValidacion{
    delay(2000L)
    val vehiculo = flota.find{it.idVehiculo == id}
    if(vehiculo != null){
        if(vehiculo.nivelBateria > 15){
            return EstadoValidacion.valida(vehiculo)
        } else{
            return EstadoValidacion.noValida("Bateria insuficiente: ${vehiculo.nivelBateria}")
        }
    } else{
        return EstadoValidacion.noValida("Vehiculo con id $id no encontrado")
    }
}

fun main(){
    val flota : List<Vehiculo> = ListOf(
        Scooter("S-001", "Xiaomi Pro", 80, 35, 5),
        Scooter("S-002", "Segway Ninebot", 45, 40, 6),
        Bicicleta("B-001", "Trek EBike", 95, 25, 7),
        Scooter("S-003", "Dualtron Thunder", 15, 50, 2), // Batería baja
        Bicicleta("B-002", "Specialized Turbo", 60, 30, 9),
        Scooter("S-004", "Kaabo Wolf", 75, 45, 2)
    )

    //INGRESOS TOTALES
    val ingresoTotal = flota.sumOf{it.precioMinuto*60}
    println("Ingreso total: $ingresoTotal")

    //SCOOTER POR NIVEL DE BATERIA
    val scooterDisponibles = flota.filter{auto -> auto is Scooter && auto.nivelBateria < 20}

    //LISTAR SCOOTERS
    scooterDisponibles.forEach{println(it.mostrarDetalles())}

    //FILTRADO PARA VEHICULOS QUE NECESITAN CARGA
    val vehiculosPorCargar = flota.filter{it.nivelBateria < 15}
    println("--- Vehiculos que necesitan carga ---")
    if(vehiculosPorCargar.isEmpty()){
        println("No hay vehiculos por cargar")
    } else{
        vehiculosPorCargar.forEach {println(it.mostrarDetalles()}
    }

    //CONTAR INVENTARIO
    val totalScooter = flota.count{it is Scooter}
    val totalBicicleta = flota.count{it is Bicicleta}
    println("--- Total inventario ---")
    println("Total scooter: $totalScooter \n Total bicicleta: $totalBicicleta Total de vehiculos: ${flota.size}")

    runBlocking{
        val resultado1 = validarVehiculo("S-001", flota)
        when(resultado1){
            is EstadoValidacion.valida -> println("Vehiculo válido: ${resultado1.vehiculo.modelo}")
            is EstadoValidacion.noValida -> println("Vehiculo no encontrado ${resultado1.mensajeError}")
        }
    }
}
