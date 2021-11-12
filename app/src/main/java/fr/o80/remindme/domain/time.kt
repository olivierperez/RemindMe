package fr.o80.remindme.domain

fun Int.toTimeFormat(): String {
    return this.toString().padStart(2, '0')
}
