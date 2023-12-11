package hrapp

class Location {
    String address
    String zipCode;
    String city;
    String state;
    Country country
    static constraints = {
    }
    static mapping = {
        id column: 'location_id'
        address column: 'street_address'
        zipCode column: 'zip_code'
        version false

    }
}
