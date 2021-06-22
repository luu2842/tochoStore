package mx.tocho

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CleatsController {

    CleatsService cleatsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond cleatsService.list(params), model:[cleatsCount: cleatsService.count()]
    }

    def show(Long id) {
        respond cleatsService.get(id)
    }

    def create() {
        respond new Cleats(params)
    }

    def save(Cleats cleats) {
        if (cleats == null) {
            notFound()
            return
        }

        try {
            cleatsService.save(cleats)
        } catch (ValidationException e) {
            respond cleats.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cleats.label', default: 'Cleats'), cleats.id])
                redirect cleats
            }
            '*' { respond cleats, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond cleatsService.get(id)
    }

    def update(Cleats cleats) {
        if (cleats == null) {
            notFound()
            return
        }

        try {
            cleatsService.save(cleats)
        } catch (ValidationException e) {
            respond cleats.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cleats.label', default: 'Cleats'), cleats.id])
                redirect cleats
            }
            '*'{ respond cleats, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        cleatsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cleats.label', default: 'Cleats'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cleats.label', default: 'Cleats'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
