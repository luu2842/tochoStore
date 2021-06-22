package mx.tocho

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class JerseyController {

    JerseyService jerseyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond jerseyService.list(params), model:[jerseyCount: jerseyService.count()]
    }

    def show(Long id) {
        respond jerseyService.get(id)
    }

    def create() {
        respond new Jersey(params)
    }

    def save(Jersey jersey) {
        if (jersey == null) {
            notFound()
            return
        }
        
        if (!jersey.validate()){
           respond jersey.errors, view:'create'
           return
        }

        try {
            jerseyService.save(jersey)
        } catch (ValidationException e) {
            respond jersey.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'jersey.label', default: 'Jersey'), jersey.id])
                redirect jersey
            }
            '*' { respond jersey, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond jerseyService.get(id)
    }

    def update(Jersey jersey) {
        if (jersey == null) {
            notFound()
            return
        }
        
        if (!jersey.validate()){
           respond jersey.errors, view:'edit'
           return
        }

        try {
            jerseyService.save(jersey)
        } catch (ValidationException e) {
            respond jersey.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'jersey.label', default: 'Jersey'), jersey.id])
                redirect jersey
            }
            '*'{ respond jersey, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        jerseyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'jersey.label', default: 'Jersey'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'jersey.label', default: 'Jersey'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
