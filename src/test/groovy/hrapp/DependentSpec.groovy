package hrapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DependentSpec extends Specification implements DomainUnitTest<Dependent> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
