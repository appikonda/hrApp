package gov.ca.hcd.security


import gov.ca.hcd.casas.util.MiscUtil
import gov.ca.hcd.common.CommonCode
import gov.ca.hcd.uif.FrameworkCode


/**
 * User domain class
 *
 * Used to store CASAS user information for the purpose of securing the site
 */

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * User domain class
 *
 * Used to store CASAS user information for the purpose of securing the
 * API
 */
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User  implements  Serializable{
    private static final long serialVersionUID = 1
    Boolean internalUser = true

    String username

    boolean accountExpired
    boolean passwordExpired


    String firstName

    String lastName
    String password

    String emailAddress
    Boolean emailOk         //if true, user has agreed to receive informational email from the department
    Boolean enabled = true
    Date terminationDate
    Integer failedLoginAttempts = 0
    Boolean accountLocked = false
    Date accountLockExpirationDate
    Location location
    FrameworkCode team      //Used by R&T to track users by team
    Program program
    FrameworkCode jobTitle //from JOB_TITLE in common codes
    String phoneNumber
    String phoneExtension
    Boolean onlineServicesAdmin

    def grailsApplication

   // static hasMany = [roles: Role]

   // static belongsTo = [location: Location, program: Program]

    static constraints = {
        internalUser(nullable: false)
        firstName(nullable: false, blank: false, minSize: 1, maxSize: 50)
        lastName(nullable: false, blank: false, minSize: 1, maxSize: 50)
        password(nullable: false, blank: false)
        emailAddress(nullable: true, blank: true, email: true, unique: true, minSize: 0, maxSize: 256, validator: { val, obj ->
            //only required for external accounts
            if (!obj.internalUser && !val) {
                return 'user.emailAddress.nullable'
            } else if (val) {
                List<String> blockedEmailDomains = CommonCode.list('BLOCKED_EMAIL_DOMAINS')*.displayValue
                blockedEmailDomains = blockedEmailDomains*.toUpperCase()
                String emailDomain = val.toUpperCase().substring(val.indexOf('@') + 1)
                if (blockedEmailDomains && emailDomain && blockedEmailDomains.contains(emailDomain)) {
                    return 'user.emailAddress.email.invalid'
                } else {
                    return true
                }
            } else {
                return true
            }
        })
        emailOk(nullable: true)
        enabled(nullable: false)
        terminationDate(nullable: true, validator: { val, obj ->
            if (val && val.after(MiscUtil.getEndOfToday())) {
                return 'user.terminationDate.future.date'
            } else {
                return true
            }
        })
        failedLoginAttempts(nullable: false)
        accountLocked(nullable: false)
        accountLockExpirationDate(nullable: true)
        location(nullable: true, validator: { val, obj ->
            if (obj.internalUser && !val) {
                return 'user.location.nullable'
            } else {
                return true
            }
        })
        team(nullable: true)
        program(nullable: true, validator: { val, obj ->
            if (obj.internalUser && !val) {
                return 'user.program.nullable'
            } else {
                return true
            }
        })
        jobTitle(nullable: true)
        phoneNumber(nullable: true, blank: true, minSize: 10, maxSize: 10)
        phoneExtension(nullable: true, blank: true, minSize: 0, maxSize: 10)
        onlineServicesAdmin(nullable: true)
    }

    static mapping = {
        table 'SEC_USERS'
        password column: 'password_hash'
        accountExpired column: 'ACCOUNT_LOCKED'
        passwordExpired column: 'INTERNAL_USER'
        version false
        firstName column: 'FIRST_NAME', index: 'SEC_USERS_FIRST_NAME_IDX'

        lastName column: 'LAST_NAME', index: 'SEC_USERS_LAST_NAME_IDX'
        location column: 'LOCATION_ID', index: 'SEC_USERS_LOCATION_IDX'
        team column: 'TEAM_ID', index: 'SEC_USERS_TEAM_IDX'
        program column: 'PROGRAM_ID', index: 'SEC_USERS_PROGRAM_IDX'
        id generator: 'sequence', params: [sequence: 'sec_user_seq']
        sort username: 'asc'
        //roles lazy: false
    }



}
