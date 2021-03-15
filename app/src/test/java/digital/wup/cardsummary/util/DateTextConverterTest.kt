package digital.wup.cardsummary.util

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DateTextConverterTest {

    companion object {
        val validDatesList =
            listOf("2018-09-26", "2018.09.26.", "2018.09.26")

        const val VALID_DATE_CONVERSION = "26.09.2018"

        val invalidFormats =
            listOf("20151-5151.1615", "sad-15ad-wqeqw", "")
    }

    @Test
    fun `given date text passed, when valid format provided, then appropriate result is given back`() {
        validDatesList.forEach {
            Assert.assertTrue(VALID_DATE_CONVERSION == DateTextConverter.convert(it))
        }
    }

    @Test
    fun `given date text passed, when invalid format provided, not available result is given back`() {
        invalidFormats.forEach {
            Assert.assertTrue(
                DateTextConverter.NOT_AVAILABLE == DateTextConverter.convert(it)
            )
        }
    }

}