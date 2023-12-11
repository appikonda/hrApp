package hrapp

class Country {
    String countryName;
    Region region;

    static constraints = {
    }
    static  mapping = {
        table:'country'
        id column: 'country_id', generator: 'assigned',  type: 'string'
        countryName column: 'country_name'
        version false
    }
}
