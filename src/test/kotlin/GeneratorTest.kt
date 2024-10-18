import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GeneratorTest {
    @Test
    fun `test generate adds correct number of phones`() {
        val td = TestData()
        val numberOfPhones = 100

        runBlocking {
            generate(numberOfPhones, td)
        }

        assertEquals(
            numberOfPhones,
            td.phones.size,
            "The number of phones added should be equal to the number requested.",
        )
    }

    @Test
    fun `test generate adds unique phone numbers`() {
        val td = TestData()
        val numberOfPhones = 100

        runBlocking {
            generate(numberOfPhones, td)
        }

        val uniquePhones = td.phones.toSet()
        assertEquals(numberOfPhones, uniquePhones.size, "All phone numbers should be unique.")
    }

    @Test
    fun `test each phone number has correct number of digits`() {
        val td = TestData()
        val numberOfPhones = 20

        runBlocking {
            generate(numberOfPhones, td)
        }

        td.phones.forEach { phone ->
            val numberOfDigits = phone.toString().length
            assertEquals(11, numberOfDigits, "Each phone number should have 11 digits.")
        }
    }

    @Test
    fun `test each phone number matches expected format`() {
        val td = TestData()
        val numberOfPhones = 100

        runBlocking {
            generate(numberOfPhones, td)
        }

        td.phones.forEach { phone ->
            val phoneString = phone.toString()
            assertTrue(phoneString.startsWith("89"), "Each phone number should start with '89'.")
        }
    }

    @Test
    fun `test generate with negative number of phones`() {
        val td = TestData()
        val numberOfPhones = -10

        runBlocking {
            generate(numberOfPhones, td)
        }

        assertEquals(0, td.phones.size, "No phone numbers should be added when a negative number is requested.")
    }
}
