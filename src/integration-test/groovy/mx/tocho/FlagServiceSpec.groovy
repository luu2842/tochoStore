package mx.tocho

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FlagServiceSpec extends Specification {

    FlagService flagService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Flag(...).save(flush: true, failOnError: true)
        //new Flag(...).save(flush: true, failOnError: true)
        //Flag flag = new Flag(...).save(flush: true, failOnError: true)
        //new Flag(...).save(flush: true, failOnError: true)
        //new Flag(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //flag.id
    }

    void "test get"() {
        setupData()

        expect:
        flagService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Flag> flagList = flagService.list(max: 2, offset: 2)

        then:
        flagList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        flagService.count() == 5
    }

    void "test delete"() {
        Long flagId = setupData()

        expect:
        flagService.count() == 5

        when:
        flagService.delete(flagId)
        sessionFactory.currentSession.flush()

        then:
        flagService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Flag flag = new Flag()
        flagService.save(flag)

        then:
        flag.id != null
    }
}
