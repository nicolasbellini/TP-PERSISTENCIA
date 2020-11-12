package ar.edu.unq.eperdemic.evento

import org.bson.codecs.pojo.annotations.BsonDiscriminator
import org.bson.codecs.pojo.annotations.BsonProperty
import java.time.LocalDateTime

@BsonDiscriminator
open class Evento {

    @BsonProperty(value = "id")
    val id: String? = null

    lateinit var log: String
    lateinit var currentTime: String

    protected constructor(){
        setCurrentTime()
    }

    fun log(): String {
        return log
    }

    fun setCurrentTime(){
        this.currentTime = LocalDateTime.now().toString()
    }

}