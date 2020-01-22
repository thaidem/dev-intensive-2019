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
        return payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюя АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ]"))
        { letter ->
            when (letter.value) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                " " -> divider
                "А" -> "A"
                "Б" -> "B"
                "В" -> "V"
                "Г" -> "G"
                "Д" -> "D"
                "Е" -> "E"
                "Ё" -> "E"
                "Ж" -> "ZH"
                "З" -> "Z"
                "И" -> "I"
                "Й" -> "I"
                "К" -> "K"
                "Л" -> "L"
                "М" -> "M"
                "Н" -> "N"
                "О" -> "O"
                "П" -> "P"
                "Р" -> "R"
                "С" -> "S"
                "Т" -> "T"
                "У" -> "U"
                "Ф" -> "F"
                "Х" -> "H"
                "Ц" -> "C"
                "Ч" -> "CH"
                "Ш" -> "SH"
                "Щ" -> "SH"
                "Ъ" -> ""
                "Ы" -> "I"
                "Ь" -> ""
                "Э" -> "E"
                "Ю" -> "YU"
                "Я" -> "YA"
                else -> letter.value
            }
        }

//        var word: String = ""
//        for (letter in payload) {
//            if (letter.toString() != " ") {
//                when(letter.toString()) {
//                    "а" -> "a"
//                    "б" -> "b"
//                    "в" -> "v"
//                    "г" -> "g"
//                    "д" -> "d"
//                    "е" -> "e"
//                    "ё" -> "e"
//                    "ж" -> "zh"
//                    "з" -> "z"
//                    "и" -> "i"
//                    "й" -> "i"
//                    "к" -> "k"
//                    "л" -> "l"
//                    "м" -> "m"
//                    "н" -> "n"
//                    "о" -> "o"
//                    "п" -> "p"
//                    "р" -> "r"
//                    "с" -> "s"
//                    "т" -> "t"
//                    "у" -> "u"
//                    "ф" -> "f"
//                    "х" -> "h"
//                    "ц" -> "c"
//                    "ч" -> "ch"
//                    "ш" -> "sh"
//                    "щ" -> "sh'"
//                    "ъ" -> ""
//                    "ы" -> "i"
//                    "ь" -> ""
//                    "э" -> "e"
//                    "ю" -> "yu"
//                    "я" -> "ya"
//                }
//            }
//        }
//        return word
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

