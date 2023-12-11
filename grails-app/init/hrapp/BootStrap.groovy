package hrapp

import grails.gorm.transactions.Transactional
import groovy.sql.Sql

class BootStrap {
    def init = {

        addTestUser()
       // createHrDatabase();
       insertHrData();

        new Animal(species: 'Dog').save()
        new Animal(species: 'Fox').save()
        new Animal(species: 'Alpaca').save()
    }

    @Transactional
    void addTestUser() {
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        def testUser = new User(username: 'me', password: 'password').save()

        UserRole.create testUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 1
        assert Role.count() == 1
        assert UserRole.count() == 1
    }



    @Transactional
    void createHrDatabase(){

        sql.execute(' CREATE TABLE regions (\n' +
                '\tregion_id INT (11) AUTO_INCREMENT PRIMARY KEY,\n' +
                '\tregion_name VARCHAR (25) DEFAULT NULL\n' +
                ')');
    }

   void insertHrData()
    {
        def db = [url:'jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE', user:'sa', password:'', driver:'org.h2.Driver']
        def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)

        def userDir=System.getProperty('user.dir')
        def sqlDir = userDir+'\\sql\\hr.sql'
        print(sqlDir)
        def myFile = new File(sqlDir)

        def lines = myFile.readLines()
        lines.each {line ->
            sql.executeInsert(line)
        }

    }
}