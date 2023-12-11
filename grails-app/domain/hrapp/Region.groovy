package hrapp

class Region {
    String regionName;
    static constraints = {
    }
    static mapping = {
        id column: 'region_id'
        regionName column: 'region_name'
        version false
    }
}
