package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage (
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String?

) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String = "id:$id ${from?.firstName} " +
            "${if(isIncoming) "получил" else "отправил"} сообщение \"$text\" " +
            "${date.add(-2, TimeUnits.DAY).humanizeDiff()}"

}