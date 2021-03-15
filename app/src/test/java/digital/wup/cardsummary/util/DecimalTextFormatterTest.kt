package digital.wup.cardsummary.util

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DecimalTextFormatterTest {

    companion object {

        private val numbersMap = mapOf<Double, String>(
            1234.5687987 to "1'234.57",
            123456.15 to "123'456.15",
            64.5 to "64.50",
            0.0 to "0.00"
        )
    }

    @Test
    fun `given decimal value passed, when converting with formatter, then appropriate result is given back`() {
        numbersMap.forEach {
            Assert.assertTrue(it.value == DecimalTextFormatter.format(it.key))
        }
    }
}