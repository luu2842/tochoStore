package mx.tocho

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CleatsServiceSpec extends Specification {

    CleatsService cleatsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Cleats(...).save(flush: true, failOnError: true)
        //new Cleats(...).save(flush: true, failOnError: true)
        //Cleats cleats = new Cleats(...).save(flush: true, failOnError: true)
        //new Cleats(...).save(flush: true, failOnError: true)
        //new Cleats(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cleats.id
    }

    void "test get"() {
        setupData()

        expect:
        cleatsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Cleats> cleatsList = cleatsService.list(max: 2, offset: 2)

        then:
        cleatsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        cleatsService.count() == 5
    }

    void "test delete"() {
        Long cleatsId = setupData()

        expect:
        cleatsService.count() == 5

        when:
        cleatsService.delete(cleatsId)
        sessionFactory.currentSession.flush()

        then:
        cleatsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Cleats cleats = new Cleats()
        cleatsService.save(cleats)

        then:
        cleats.id != null
    }
}
