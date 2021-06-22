package mx.tocho

import grails.gorm.services.Service

@Service(Leggin)
interface LegginService {

    Leggin get(Serializable id)

    List<Leggin> list(Map args)

    Long count()

    void delete(Serializable id)

    Leggin save(Leggin leggin)

}