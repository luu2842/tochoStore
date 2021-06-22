package mx.tocho

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FlagController {

    FlagService flagService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond flagService.list(params), model:[flagCount: flagService.count()]
    }

    def show(Long id) {
        respond flagService.get(id)
    }

    def create() {
        respond new Flag(params)
    }

    def save(Flag flag) {
        if (flag == null) {
            notFound()
            return
        }

        try {
            flagService.save(flag)
        } catch (ValidationException e) {
            respond flag.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'flag.label', default: 'Flag'), flag.id])
                redirect flag
            }
            '*' { respond flag, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond flagService.get(id)
    }

    def update(Flag flag) {
        if (flag == null) {
            notFound()
            return
        }

        try {
            flagService.save(flag)
        } catch (ValidationException e) {
            respond flag.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'flag.label', default: 'Flag'), flag.id])
                redirect flag
            }
            '*'{ respond flag, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        flagService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'flag.label', default: 'Flag'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'flag.label', default: 'Flag'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
