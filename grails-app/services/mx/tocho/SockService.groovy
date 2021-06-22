package mx.tocho

import grails.gorm.services.Service

@Service(Sock)
interface SockService {

    Sock get(Serializable id)

    List<Sock> list(Map args)

    Long count()

    void delete(Serializable id)

    Sock save(Sock sock)

}