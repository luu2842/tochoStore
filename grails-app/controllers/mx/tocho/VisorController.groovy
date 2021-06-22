package mx.tocho

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class VisorController {

    VisorService visorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond visorService.list(params), model:[visorCount: visorService.count()]
    }

    def show(Long id) {
        respond visorService.get(id)
    }

    def create() {
        respond new Visor(params)
    }

    def save(Visor visor) {
        if (visor == null) {
            notFound()
            return
        }

        try {
            visorService.save(visor)
        } catch (ValidationException e) {
            respond visor.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'visor.label', default: 'Visor'), visor.id])
                redirect visor
            }
            '*' { respond visor, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond visorService.get(id)
    }

    def update(Visor visor) {
        if (visor == null) {
            notFound()
            return
        }

        try {
            visorService.save(visor)
        } catch (ValidationException e) {
            respond visor.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'visor.label', default: 'Visor'), visor.id])
                redirect visor
            }
            '*'{ respond visor, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        visorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'visor.label', default: 'Visor'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'visor.label', default: 'Visor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
