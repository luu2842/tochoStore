package mx.tocho

import grails.gorm.services.Service

@Service(Visor)
interface VisorService {

    Visor get(Serializable id)

    List<Visor> list(Map args)

    Long count()

    void delete(Serializable id)

    Visor save(Visor visor)

}