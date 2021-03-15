package digital.wup.cardsummary.util

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CardNumberTextFormatterTest {


    companion object {
        val validCardNumbersMap = mapOf<String, String>(
            "4003-1565-5402-0882" to "****-****-****-0882",
            "4003-6326-3784-1064" to "****-****-****-1064",
            "5315-7204-0721-2531" to "****-****-****-2531"
        )

        val invalidFormats = listOf(
            "467-456-4687-78797",
            "ab12-aff4-w7f8-895w",
            "t4003.1565.5402.0882",
            "",
        )
    }

    @Test
    fun `given card number passed, when valid format provided, then appropriate result is given back`() {
        validCardNumbersMap.forEach {
            Assert.assertTrue(it.value == CardNumberTextFormatter.format(it.key))
        }
    }

    @Test
    fun `given card number passed, when invalid format provided, not available result is given back`() {
        invalidFormats.forEach {
            Assert.assertTrue(
                CardNumberTextFormatter.NOT_AVAILABLE == CardNumberTextFormatter.format(it)
            )
        }
    }

}