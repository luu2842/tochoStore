package mx.tocho

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SockController {

    SockService sockService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sockService.list(params), model:[sockCount: sockService.count()]
    }

    def show(Long id) {
        respond sockService.get(id)
    }

    def create() {
        respond new Sock(params)
    }

    def save(Sock sock) {
        if (sock == null) {
            notFound()
            return
        }

        try {
            sockService.save(sock)
        } catch (ValidationException e) {
            respond sock.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sock.label', default: 'Sock'), sock.id])
                redirect sock
            }
            '*' { respond sock, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sockService.get(id)
    }

    def update(Sock sock) {
        if (sock == null) {
            notFound()
            return
        }

        try {
            sockService.save(sock)
        } catch (ValidationException e) {
            respond sock.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sock.label', default: 'Sock'), sock.id])
                redirect sock
            }
            '*'{ respond sock, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sockService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sock.label', default: 'Sock'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sock.label', default: 'Sock'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
