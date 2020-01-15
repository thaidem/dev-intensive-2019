package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.models.User
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
        val user2 = user.copy(id = "2", lastName = "Travolta")
        print("$user \n$user2")
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
            ${user4.lastVisit?.format()} 
        """.trimIndent())
    }
}
