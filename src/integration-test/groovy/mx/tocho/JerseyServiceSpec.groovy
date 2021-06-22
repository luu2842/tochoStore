package mx.tocho

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class JerseyServiceSpec extends Specification {

    JerseyService jerseyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Jersey(...).save(flush: true, failOnError: true)
        //new Jersey(...).save(flush: true, failOnError: true)
        //Jersey jersey = new Jersey(...).save(flush: true, failOnError: true)
        //new Jersey(...).save(flush: true, failOnError: true)
        //new Jersey(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //jersey.id
    }

    void "test get"() {
        setupData()

        expect:
        jerseyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Jersey> jerseyList = jerseyService.list(max: 2, offset: 2)

        then:
        jerseyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        jerseyService.count() == 5
    }

    void "test delete"() {
        Long jerseyId = setupData()

        expect:
        jerseyService.count() == 5

        when:
        jerseyService.delete(jerseyId)
        sessionFactory.currentSession.flush()

        then:
        jerseyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Jersey jersey = new Jersey()
        jerseyService.save(jersey)

        then:
        jersey.id != null
    }
}
