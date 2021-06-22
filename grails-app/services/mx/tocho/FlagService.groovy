package mx.tocho

import grails.gorm.services.Service

@Service(Flag)
interface FlagService {

    Flag get(Serializable id)

    List<Flag> list(Map args)

    Long count()

    void delete(Serializable id)

    Flag save(Flag flag)

}