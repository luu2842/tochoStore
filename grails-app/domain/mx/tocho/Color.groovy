package mx.tocho

import groovy.transform.ToString

@ToString(includeNames=true, includeFields=true, includes="name")
class Color {

  String name

  static constraints = {
    name  unique: true
  }

}
