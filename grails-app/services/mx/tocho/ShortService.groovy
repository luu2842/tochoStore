package mx.tocho

import grails.gorm.services.Service

@Service(Short)
interface ShortService {

    Short get(Serializable id)

    List<Short> list(Map args)

    Long count()

    void delete(Serializable id)

    Short save(Short shortt)

}
