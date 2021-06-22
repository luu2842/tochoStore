package mx.tocho

import grails.gorm.services.Service

@Service(Cleats)
interface CleatsService {

    Cleats get(Serializable id)

    List<Cleats> list(Map args)

    Long count()

    void delete(Serializable id)

    Cleats save(Cleats cleats)

}