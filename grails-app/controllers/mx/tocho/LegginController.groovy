package mx.tocho

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LegginController {

    LegginService legginService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond legginService.list(params), model:[legginCount: legginService.count()]
    }

    def show(Long id) {
        respond legginService.get(id)
    }

    def create() {
        respond new Leggin(params)
    }

    def save(Leggin leggin) {
        if (leggin == null) {
            notFound()
            return
        }

        try {
            legginService.save(leggin)
        } catch (ValidationException e) {
            respond leggin.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'leggin.label', default: 'Leggin'), leggin.id])
                redirect leggin
            }
            '*' { respond leggin, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond legginService.get(id)
    }

    def update(Leggin leggin) {
        if (leggin == null) {
            notFound()
            return
        }

        try {
            legginService.save(leggin)
        } catch (ValidationException e) {
            respond leggin.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'leggin.label', default: 'Leggin'), leggin.id])
                redirect leggin
            }
            '*'{ respond leggin, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        legginService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'leggin.label', default: 'Leggin'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'leggin.label', default: 'Leggin'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
