package ar.edu.unq.eperdemic.evento
import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import org.bson.codecs.pojo.annotations.BsonDiscriminator

@BsonDiscriminator("EventoContagio")
class EventoContagio : Evento() {


    var idVector: Long? = null
    var idPatogeno: String? = null
    var nombreUbicacion: String? = null

    fun eventoPandemia(patogeno: Patogeno, especie: Especie): EventoContagio {
        this.log = "La Especie: ${especie.nombre} perteneciento al Patogeno: ${patogeno.tipo} se ha convertido en pandemia"
        this.idPatogeno = patogeno.tipo
        return this
    }

    fun eventoEspecieEnNuevaUbicacion(especie: Especie, ubicacion: Ubicacion): EventoContagio {
        this.nombreUbicacion = ubicacion.nombre
        this.idPatogeno = especie.patogeno?.tipo
        this.log = "La Especie: ${especie.nombre} perteneciento al Patogeno: ${especie.patogeno!!.tipo} ahora se encuentra tambien en: ${ubicacion.nombre}"
        return this
    }

    fun eventoEnfermedadEsPadecidaPor(vector: Vector, patogeno: Patogeno): EventoContagio {
        this.log = "El Vector: ${vector.id} esta infectado por el patogeno: ${patogeno.tipo}"
        this.idVector = vector.id!!.toLong()
        this.idPatogeno = patogeno.tipo
        return this
    }
    fun elVectorPadeceUnaNuevaEnfermedad(vectorTransmisor: Vector, vectorContagiado: Vector): EventoContagio {
        this.log = "El Vector: ${vectorTransmisor.id} ha infectado al vector ${vectorContagiado.id}"
        this.idVector = vectorTransmisor.id!!.toLong()
        return this
    }
}