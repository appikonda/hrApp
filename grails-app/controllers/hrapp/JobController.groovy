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
class JobController {

    Job job;
    JobService  jobService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<Job> jobs =jobService.list(params)
        List<ResponseDto> responseDtos = []
        jobs?.eachWithIndex{ Job job, int i ->
            ResponseDto response = new ResponseDto(jobTitle: job.jobTitle, minSalary: job.minSalary, name: null)
            responseDtos.add(response)
        }
        def json = groovy.json.JsonOutput.toJson(responseDtos)
        respond json
    }

    def show(Long id) {
        Job job = jobService.get(id)
        ResponseDto responseDto = new ResponseDto(jobTitle:job.jobTitle, maxSalary: job.maxSalary, minSalary: job.minSalary)
        respond responseDto
    }

    @Transactional
    def save(Job job) {
        if (job == null) {
            render status: NOT_FOUND
            return
        }
        if (job.hasErrors()) {
            //transactionStatus.setRollbackOnly()
            respond job.errors, [status: BAD_REQUEST, view:"show"]
            return
        }

        try {
            jobService.save(job)
        } catch (ValidationException e) {
            respond job.errors
            return
        }

        respond job, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Job job) {
        if (job == null) {
            render status: NOT_FOUND
            return
        }
        if (job.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond job.errors
            return
        }

        try {
            jobService.save(job)
        } catch (ValidationException e) {
s            respond job.errors
            return
        }

        respond job, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        jobService.delete(id)

        render status: NO_CONTENT
    }
}
