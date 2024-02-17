package hrapp


import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class CommonCodeController {

    Job job
    JobService  jobService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]



    def show(Long id) {
        if (id > 15){
            respond new ErrorDto(status: 404, message: "categories must be provided"),  [status: NOT_FOUND, view:"show"]
            return
        }
        else
        {
            respond jobService.get(id)
        }

    }

    def commonCodes()   {
        if(!params.categories?.trim()){
            respond  new ErrorDto(status: 404, message: "categories must be provided"),  [status: NOT_FOUND, view:"commonCodes"]
            return
        }
    }

   /* commonCodes() {
        if(!params.categories?.trim()){
            render new ErrorDto(error: 400, message: "categories must be provided") as JSON
            return
        }

        def commonCodes = CommonCode.withCriteria {
            inList("category",(params.categories as String).tokenize( ',' ))
     }
    render commonCodes as JSON
    }*/
}

