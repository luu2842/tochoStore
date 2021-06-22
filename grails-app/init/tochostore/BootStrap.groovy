package tochostore

import mx.tocho.*

class BootStrap {

    def init = { servletContext ->
    
    5.times{ number ->
    new Color (name: "color${number}").save()
    new Size (name: "size${number}").save()
    
    }
    }
    def destroy = {
    }
}
