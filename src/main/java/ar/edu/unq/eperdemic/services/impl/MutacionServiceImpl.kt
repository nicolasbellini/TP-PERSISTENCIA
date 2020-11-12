package ar.edu.unq.eperdemic.services.impl

import ar.edu.unq.eperdemic.evento.EventoMutacion
import ar.edu.unq.eperdemic.modelo.Mutacion
import ar.edu.unq.eperdemic.persistencia.dao.EspecieDAO
import ar.edu.unq.eperdemic.persistencia.dao.MutacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.mongodb.MongoFeedDAO
import ar.edu.unq.eperdemic.services.MutacionService
import ar.edu.unq.eperdemic.services.runner.TransactionRunner.runTrx

class MutacionServiceImpl(val mutacionDAO: MutacionDAO, val especieDAO: EspecieDAO) : MutacionService {

    val mongoDAO = MongoFeedDAO();

    override fun mutar(especieId: Int, mutacionId: Int){
        runTrx {
            var especie = especieDAO.recuperar(especieId)
            var mutacion = mutacionDAO.recuperar(mutacionId)
            especie.desbloquearMutacion(mutacion)
            especieDAO.actualizar(especie)
            mongoDAO.save(EventoMutacion().eventoEspecieMuto(especie.patogeno!!,especie))
        }
    }

    override fun crearMutacion(mutacion: Mutacion): Mutacion {
        runTrx {mutacionDAO.guardar(mutacion)}
        return mutacion
    }

    override fun recuperarMutacion(mutacionId: Int): Mutacion {
       return runTrx {mutacionDAO.recuperar(mutacionId)}
    }

    override fun recuperarTodas(): List<Mutacion>{
        return runTrx { mutacionDAO.recuperarATodos() }
    }
}