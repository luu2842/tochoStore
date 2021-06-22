package mx.tocho

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class VisorServiceSpec extends Specification {

    VisorService visorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Visor(...).save(flush: true, failOnError: true)
        //new Visor(...).save(flush: true, failOnError: true)
        //Visor visor = new Visor(...).save(flush: true, failOnError: true)
        //new Visor(...).save(flush: true, failOnError: true)
        //new Visor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //visor.id
    }

    void "test get"() {
        setupData()

        expect:
        visorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Visor> visorList = visorService.list(max: 2, offset: 2)

        then:
        visorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        visorService.count() == 5
    }

    void "test delete"() {
        Long visorId = setupData()

        expect:
        visorService.count() == 5

        when:
        visorService.delete(visorId)
        sessionFactory.currentSession.flush()

        then:
        visorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Visor visor = new Visor()
        visorService.save(visor)

        then:
        visor.id != null
    }
}
