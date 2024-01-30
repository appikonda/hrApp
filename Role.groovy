package gov.ca.hcd.security

//import grails.databinding.BindingFormat
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString


/**
 * Used to store Role data
 */

/**
 * Used to store Role data
 */
@EqualsAndHashCode(includes = 'authority')
@ToString(includes = 'authority', includeNames = true, includePackage = false)
class Role implements  Serializable{
	private static final long serialVersionUID = 1
	String authority       //mapped from name column
	//@BindingFormat("UPPERCASE")
	//String name
	String displayValue
	String description
	Boolean externalRole = Boolean.FALSE       //indicates whether this role can be give to external users

	//static hasMany = [users: User, permissions: String, searchableDomains: BusinessDomain, visibleProgramTasks: Program]
	//static belongsTo = User

	static mapping = {
		table 'SEC_ROLES'
		cache true
		authority column: "name"
		version false
		id generator: 'sequence', params: [sequence: 'sec_role_seq']
		sort authority: 'asc'
		//searchableDomains joinTable: 'SEC_ROLES_SEC_DOMAINS'
		//permissions lazy: false
	}

	static constraints = {
		authority nullable: false, blank: false, unique: true, minSize: 1, maxSize: 50
		//authority nullable: false, blank: false, unique: true, minSize: 1, maxSize: 50
		//name(nullable: false, blank: false, unique: true, minSize: 1, maxSize: 50)
		displayValue(nullable: false, blank: false, unique: true, minSize: 1, maxSize: 50)
		description(blank: false, nullable: false, minSize: 1, maxSize: 255)
		externalRole(nullable: false)
		//searchableDomains(nullable: true)
	}


}
