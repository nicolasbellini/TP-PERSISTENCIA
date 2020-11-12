package ar.edu.unq.eperdemic.evento

import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import org.bson.codecs.pojo.annotations.BsonDiscriminator

@BsonDiscriminator("EventoArribo")
class EventoArribo : Evento() {

    var idVector: Int? = null
    var nombreUbicacion: String? = null

    fun eventoVectorViajes(vector: Vector, ubicacion: Ubicacion): EventoArribo {
        this.log = "El vector con id: ${vector.id} se movio a la ubicacion: ${ubicacion.nombre}"
        this.idVector = vector.id
        this.nombreUbicacion = ubicacion.nombre
        return this
    }

}