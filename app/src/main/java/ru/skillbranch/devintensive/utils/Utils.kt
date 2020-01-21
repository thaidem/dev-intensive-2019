package ru.skillbranch.devintensive.utils

import java.lang.IllegalStateException

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return when {
            firstName != null && firstName != "" && lastName != null && lastName != "" -> firstName to lastName
            firstName != null && firstName != "" && lastName == null -> firstName to null
            firstName == "" || firstName == null && lastName == "" || lastName == null -> null to null
            else -> throw IllegalStateException("Ошибка имени")
        }

    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val iFirstName = iName(firstName)
        val iLastName = iName(lastName)
        return when {
            iFirstName != null && iLastName != null -> "$iFirstName$iLastName"
            iFirstName != null && iLastName == null -> iFirstName
            iFirstName == null && iLastName != null -> iLastName
            iFirstName == null && iLastName == null -> null
            else -> throw IllegalStateException("Ошибка инициалов")
        }

    }
}

private fun iName(name: String?): String? {
    return if (name != null && name != "" && name != " ") name?.first()?.toUpperCase().toString() else null
}

