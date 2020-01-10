package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.models.User

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
}
