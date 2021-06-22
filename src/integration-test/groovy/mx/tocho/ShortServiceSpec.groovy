package mx.tocho

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ShortServiceSpec extends Specification {

    ShortService shortService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Short(...).save(flush: true, failOnError: true)
        //new Short(...).save(flush: true, failOnError: true)
        //Short short = new Short(...).save(flush: true, failOnError: true)
        //new Short(...).save(flush: true, failOnError: true)
        //new Short(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //short.id
    }

    void "test get"() {
        setupData()

        expect:
        shortService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Short> shortList = shortService.list(max: 2, offset: 2)

        then:
        shortList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        shortService.count() == 5
    }

    void "test delete"() {
        Long shortId = setupData()

        expect:
        shortService.count() == 5

        when:
        shortService.delete(shortId)
        sessionFactory.currentSession.flush()

        then:
        shortService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Short short = new Short()
        shortService.save(short)

        then:
        short.id != null
    }
}
