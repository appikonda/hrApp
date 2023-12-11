package hrapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AnimalSpec extends Specification implements DomainUnitTest<Animal> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
