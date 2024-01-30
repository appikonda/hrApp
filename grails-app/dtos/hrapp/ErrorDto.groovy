package hrapp

import org.grails.datastore.mapping.model.types.conversion.IntegerToByteConverter
import org.springframework.http.HttpStatus

class ErrorDto {

    String message
    Integer status

    static constraints = {
    }
}