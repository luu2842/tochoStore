package mx.tocho

import grails.gorm.transactions.Transactional

@Transactional
class JerseyService {

    Jersey get(Serializable id){
       Jersey.get(id)
    }

    List<Jersey> list(Map args){
       Jersey.findAll(args)
    }

    Long count(){
       Jersey.count()
    }

    void delete(Serializable id){
       Jersey.delete(id)
    }

    Jersey save(Jersey jersey){
        jersey.save()
    }

}
