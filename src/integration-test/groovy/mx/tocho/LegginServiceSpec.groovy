package mx.tocho

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LegginServiceSpec extends Specification {

    LegginService legginService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Leggin(...).save(flush: true, failOnError: true)
        //new Leggin(...).save(flush: true, failOnError: true)
        //Leggin leggin = new Leggin(...).save(flush: true, failOnError: true)
        //new Leggin(...).save(flush: true, failOnError: true)
        //new Leggin(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //leggin.id
    }

    void "test get"() {
        setupData()

        expect:
        legginService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Leggin> legginList = legginService.list(max: 2, offset: 2)

        then:
        legginList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        legginService.count() == 5
    }

    void "test delete"() {
        Long legginId = setupData()

        expect:
        legginService.count() == 5

        when:
        legginService.delete(legginId)
        sessionFactory.currentSession.flush()

        then:
        legginService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Leggin leggin = new Leggin()
        legginService.save(leggin)

        then:
        leggin.id != null
    }
}
