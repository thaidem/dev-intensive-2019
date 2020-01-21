package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user2 = User("2", "John", "Travolta" )

    }

    @Test
    fun test_factory() {
//        val user = User.makeUser("John Travolta")
//        val user2 = User.makeUser("John Cohen")
        val user = User.makeUser("John Malkovich")
        val user3 = User.makeUser("John")
        val user4 = User.makeUser("")
        val user5 = User.makeUser(" ")
        val user6 = User.makeUser(null)
        val user2 = user.copy(id = "2", lastName = "Travolta")
        print("$user \n$user3 \n$user4 \n$user5 \n$user6 \n$user2")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Malkovich")

        fun getUserInfo() = user
        val(id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Malkovich")
        val user2 = user.copy(lastVisit = Date())
        val user3 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user4 = user.copy(lastName = "Cena",lastVisit = Date().add(3, TimeUnits.HOUR))

        println("""
            ${user.lastVisit?.format()} 
            ${user2.lastVisit?.format()} 
            ${user3.lastVisit?.format()} 
            ${user4.lastVisit?.format("HH:mm")}
             
        """.trimIndent())
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Дмитрий Козлов")
        val newUser = user.copy(lastVisit = Date().add(-2, TimeUnits.DAY))
        println(newUser)

        val userView = user.toUserView()

        userView.printMe()
    }

    @Test
    fun test_toInitials() {
        println("""
            ${Utils.toInitials("john" ,"doe")}
            ${Utils.toInitials("John", null)}
            ${Utils.toInitials(null, "John")}
            ${Utils.toInitials(null, null)}
            ${Utils.toInitials(" ", "")}

        """.trimIndent())

    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Дмитрий Козлов")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "here is any text", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "here is image url", type = "image")

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())

    }
}
