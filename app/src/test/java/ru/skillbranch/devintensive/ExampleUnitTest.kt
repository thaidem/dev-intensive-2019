package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
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
            ${user2.lastVisit?.format("dd.MM HH:mm")} 
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
    fun test_abstract_factory() {
        val user = User.makeUser("Дмитрий Козлов")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "here is any text", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "here is image url", type = "image")

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())

    }

    @Test
    fun transliteration_test() {
        /* skillBranch tests */
        assertEquals("Zhenya Stereotipov", Utils.transliteration("Женя Стереотипов"))
        assertEquals("Amazing_Petr", Utils.transliteration("Amazing Петр","_"))

        /* additional tests */
        assertEquals("iVan Stereotizhov", Utils.transliteration("иВан Стереотижов"))
        assertEquals("Amazing_PeZhr", Utils.transliteration("Amazing ПеЖр", "_"))
        assertEquals("aAbBvVgGdDeEeEzhZhzZiIiIkKlL", Utils.transliteration("аАбБвВгГдДеЕёЁжЖзЗиИйЙкКлЛ"))
        assertEquals("mMnNoOpPrRsStTuUfFhHcCshShsh'Sh'", Utils.transliteration("мМнНоОпПрРсСтТуУфФхХцЦшШщЩ"))
        assertEquals("eEyuYuyaYa", Utils.transliteration("ъЪьЬэЭюЮяЯ"))
        assertEquals("123|!,^-=+><|english", Utils.transliteration("123 !,^-=+>< english", "|"))
        assertEquals("Zhizha ZhiZhnaYa", Utils.transliteration("Жижа ЖиЖнаЯ"))
        assertEquals("Sobaka is a dog", Utils.transliteration("Собака dog", " is a "))
    }

    @Test
    fun parseFullNameTest(){
        /* skillBranch tests */
        assertEquals(null to null, Utils.parseFullName(null))
        assertEquals(null to null, Utils.parseFullName(""))
        assertEquals(null to null, Utils.parseFullName(" "))
        assertEquals("John" to null, Utils.parseFullName("John"))
        assertEquals("John" to "Lennon", Utils.parseFullName("John Lennon"))
        /* additional tests */
        assertEquals(null to null, Utils.parseFullName("     "))
        assertEquals("null" to null, Utils.parseFullName("null"))
        assertEquals("John" to null, Utils.parseFullName("John      "))
        assertEquals("John" to null, Utils.parseFullName(" John     "))
    }

    @Test
    fun toInitialsTest(){
        /* skillBranch tests */
        assertEquals("JD", Utils.toInitials("john", "doe"))
        assertEquals("J", Utils.toInitials("John", null))
        assertEquals(null, Utils.toInitials(null, null))
        assertEquals(null, Utils.toInitials(" ", ""))

        /* additional tests */
        assertEquals(null, Utils.toInitials(" ", null))
        assertEquals(null, Utils.toInitials(null, ""))
        assertEquals("T", Utils.toInitials(null, "  tommy"))
        assertEquals("ST", Utils.toInitials("  samuel  ", "  tommy"))
        assertEquals("J", Utils.toInitials(null, "John"))
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
    fun formatTest(){
        val calendar = Calendar.getInstance()
        calendar.set(1988, 0, 12, 17,50, 0)
        val date = Date.from(calendar.toInstant())
        /* skillBranch tests */
        assertEquals("17:50:00 12.01.88", date.format())
        assertEquals("17:50", date.format("HH:mm"))

        /* additional tests */
        assertEquals("50:00", date.format("mm:ss"))
        assertEquals("01.12.1988", date.format("MM.dd.yyyy"))
        assertEquals("12.1.1988", date.format("d.M.Y"))
        assertEquals("5:50:0", date.format("h:m:s"))
    }

    @Test
    fun addTest(){
        val calendar = Calendar.getInstance()
        calendar.set(1988, 0, 2, 11,50, 0)
        val date = Date.from(calendar.toInstant())
        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale("ru"))
        assertEquals("Сб янв 02 11:50:02 1988", dateFormat.format(date.add(2, TimeUnits.SECOND)))
        assertEquals("Вт дек 29 11:50:02 1987", dateFormat.format(date.add(-4, TimeUnits.DAY)))
    }

    @Test
    fun humanizeDiffTest() {
        val messageDate = Date()
        val currDate = Date()
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.add(-1, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.add(1, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("несколько секунд назад", messageDate.add(-2, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через несколько секунд", messageDate.add(2, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("несколько секунд назад", messageDate.add(-45, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через несколько секунд", messageDate.add(45, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("минуту назад", messageDate.add(-46, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через минуту", messageDate.add(46, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("минуту назад", messageDate.add(-75, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через минуту", messageDate.add(75, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 минуту назад", messageDate.add(-76, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 минуту", messageDate.add(76, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 2 минуты", messageDate.add(2, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("3 минуты назад", messageDate.add(-3, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("45 минут назад", messageDate.add(-45, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 45 минут", messageDate.add(45, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("час назад", messageDate.add(-46, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через час", messageDate.add(46, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("час назад", messageDate.add(-75, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через час", messageDate.add(75, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 час назад", messageDate.add(-76, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 час", messageDate.add(76, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("4 часа назад", messageDate.add(-4, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 4 часа", messageDate.add(4, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("6 часов назад", messageDate.add(-6, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 6 часов", messageDate.add(6, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("11 часов назад", messageDate.add(-11, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 11 часов", messageDate.add(11, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("22 часа назад", messageDate.add(-22, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 22 часа", messageDate.add(22, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("день назад", messageDate.add(-23, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через день", messageDate.add(23, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("день назад", messageDate.add(-26, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через день", messageDate.add(26, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 день назад", messageDate.add(-27, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 день", messageDate.add(27, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("12 дней назад", messageDate.add(-12, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 12 дней", messageDate.add(12, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("112 дней назад", messageDate.add(-112, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 112 дней", messageDate.add(112, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("360 дней назад", messageDate.add(-360, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 360 дней", messageDate.add(360, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более года назад", messageDate.add(-361, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более чем через год", messageDate.add(361, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более года назад", messageDate.add(-12345, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более чем через год", messageDate.add(12345, TimeUnits.DAY).humanizeDiff(currDate))
    }

    @Test
    fun test_plural() {
        assertEquals("0 секунд", TimeUnits.SECOND.plural(0))
        assertEquals("1 секунду", TimeUnits.SECOND.plural(1))
        assertEquals("2 секунды", TimeUnits.SECOND.plural(2))
        assertEquals("7 секунд", TimeUnits.SECOND.plural(7))
        assertEquals("14 секунд", TimeUnits.SECOND.plural(14))
        assertEquals("24 секунды", TimeUnits.SECOND.plural(24))
        assertEquals("102 секунды", TimeUnits.SECOND.plural(102))
        assertEquals("112 секунд", TimeUnits.SECOND.plural(112))
        assertEquals("122 секунды", TimeUnits.SECOND.plural(122))
        assertEquals("311 секунд", TimeUnits.SECOND.plural(311))

        assertEquals("0 минут", TimeUnits.MINUTE.plural(0))
        assertEquals("1 минуту", TimeUnits.MINUTE.plural(1))
        assertEquals("2 минуты", TimeUnits.MINUTE.plural(2))
        assertEquals("7 минут", TimeUnits.MINUTE.plural(7))
        assertEquals("14 минут", TimeUnits.MINUTE.plural(14))
        assertEquals("24 минуты", TimeUnits.MINUTE.plural(24))
        assertEquals("102 минуты", TimeUnits.MINUTE.plural(102))
        assertEquals("112 минут", TimeUnits.MINUTE.plural(112))
        assertEquals("122 минуты", TimeUnits.MINUTE.plural(122))
        assertEquals("311 минут", TimeUnits.MINUTE.plural(311))

        assertEquals("0 часов", TimeUnits.HOUR.plural(0))
        assertEquals("1 час", TimeUnits.HOUR.plural(1))
        assertEquals("2 часа", TimeUnits.HOUR.plural(2))
        assertEquals("7 часов", TimeUnits.HOUR.plural(7))
        assertEquals("14 часов", TimeUnits.HOUR.plural(14))
        assertEquals("24 часа", TimeUnits.HOUR.plural(24))
        assertEquals("102 часа", TimeUnits.HOUR.plural(102))
        assertEquals("112 часов", TimeUnits.HOUR.plural(112))
        assertEquals("122 часа", TimeUnits.HOUR.plural(122))
        assertEquals("311 часов", TimeUnits.HOUR.plural(311))

        assertEquals("0 дней", TimeUnits.DAY.plural(0))
        assertEquals("1 день", TimeUnits.DAY.plural(1))
        assertEquals("2 дня", TimeUnits.DAY.plural(2))
        assertEquals("7 дней", TimeUnits.DAY.plural(7))
        assertEquals("14 дней", TimeUnits.DAY.plural(14))
        assertEquals("24 дня", TimeUnits.DAY.plural(24))
        assertEquals("102 дня", TimeUnits.DAY.plural(102))
        assertEquals("112 дней", TimeUnits.DAY.plural(112))
        assertEquals("122 дня", TimeUnits.DAY.plural(122))
        assertEquals("311 дней", TimeUnits.DAY.plural(311))
        assertEquals("1234 дня", TimeUnits.DAY.plural(1234))
    }

    @Test
    fun builderTest(){
        val date = Date()

        val user1 = User(
            "5",
            "Никола",
            "Тесла",
            null,
            0,
            1000,
            date.add(-2, TimeUnits.DAY),
            false)

        val user2 = User.Builder().id("5")
            .firstName("Никола")
            .lastName("Тесла")
            .avatar(null)
            .rating(0)
            .respect(1000)
            .lastVisit(date.add(-2, TimeUnits.DAY))
            .isOnline(false)
            .build()

        val user3 = User.Builder().build()

        assertEquals(user1, user2)
        assertTrue(user3 is User)
        assertNotEquals(null, user3.id)
        assertEquals(null, user3.firstName)
        assertEquals(null, user3.lastName)
        assertEquals(null, user3.avatar)
        assertEquals(0, user3.rating)
        assertEquals(0, user3.respect)
        assertNotEquals(null, user3.lastVisit)
        assertFalse(user3.isOnline)
    }
}
