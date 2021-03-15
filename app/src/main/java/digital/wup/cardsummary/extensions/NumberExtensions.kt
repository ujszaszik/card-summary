package digital.wup.cardsummary.extensions

import digital.wup.cardsummary.util.DecimalTextFormatter
import kotlin.math.min

fun Double.validPercentageOf(total: Double): Double {
    val ratio = this / total
    if (total.isZero()) this.setToZero()
    return ratio.adjustToLimit(one())
}

fun Double.toFormattedString(): String = DecimalTextFormatter.format(this)

fun Double.isZero(): Boolean = this == zero()

fun Double.adjustToLimit(limit: Double): Double = min(this, limit)

fun Double.setToZero() = this.times(zero())

fun Number.half() = this.toDouble() / two()

fun Number.isNegative() = this.toInt() < zero()

fun Number.isGraterThanZero() = this.toInt() > zero()

fun two() = 2.0

fun one() = 1.0

fun zero() = 0.0