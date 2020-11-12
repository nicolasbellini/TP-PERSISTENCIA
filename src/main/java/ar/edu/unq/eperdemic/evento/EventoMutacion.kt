package ar.edu.unq.eperdemic.evento

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import org.bson.codecs.pojo.annotations.BsonDiscriminator

@BsonDiscriminator("EventoMutacion")
class EventoMutacion : Evento() {

    var idPatogeno: String? = null

    fun eventoEspecieCreada(patogeno: Patogeno, especie: Especie): EventoMutacion{
        this.log = "Se creo la Especie: ${especie.nombre} en el Patogeno:  ${patogeno.tipo}"
        this.idPatogeno = patogeno.tipo
        return this
    }

    fun eventoEspecieMuto(patogeno: Patogeno, especie: Especie): EventoMutacion {
       this.log = "La Especie: ${especie.nombre} perteneciento al Patogeno: ${patogeno.tipo} ha mutado"
       this.idPatogeno = patogeno.tipo
       return this
    }






}