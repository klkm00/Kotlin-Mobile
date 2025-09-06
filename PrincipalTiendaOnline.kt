fun main(){

    var inventario = mutableMapOf<String, Int>(
        "laptop" to 20,
        "mouse" to 30,
        "teclado" to 25,
        "webcam" to 10,
        "monitor" to 15
    )

    var compraFinalizada  = false

    var nombreProducto : String

    var cantidadProducto : Int

    var respuesta : String

    inventario.forEach { clave, valor ->
        println("Producto: $clave - Stock: $valor")
    }

    while (!compraFinalizada){
        try{
            println("Indica producto que deseas comprar: ")
            nombreProducto = readln().lowercase().trim()
            if(nombreProducto in inventario){
                println("Indica la cantidad que deseas comprar: ")
                cantidadProducto = readln().toInt()
                if(cantidadProducto <= inventario[nombreProducto]!!){
                    inventario[nombreProducto] = inventario[nombreProducto]!! - cantidadProducto
                    println("Compra con exito del producto $nombreProducto; El nuevo stock es ${inventario[nombreProducto]}")
                    println("¿Deseas realizar otra compra?")
                    respuesta = readln().trim().lowercase()
                    if(respuesta == "no"){
                        compraFinalizada = true
                    }
                } else{
                    throw Exception("Lo sentimos. Solo quedan ${inventario[nombreProducto]} unidades de $nombreProducto")
                }
            } else{
                throw Exception("El producto ingresado no existe")
            }
        }
        catch(e:Exception){
            println(e.message)
        }
    }

    println("Analisis de inventario")
    for((clave, valor) in inventario){
        when (valor){
            0 -> println("Alerta: la cantidad del producto $clave es $valor")
            in 1..5 -> println("Aviso: El stock de $clave es bajo, quedan $valor")
            else -> println("El inventario está bien, de $clave quedan $valor")
        }
    }
}