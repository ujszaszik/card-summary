package digital.wup.cardsummary.model

import androidx.core.os.bundleOf

object AndroidTestCards {

    val testCardOne = CardModel(
        cardId = "1",
        issuer = "Test Issuer 1",
        cardNumber = "1234-5678-9101-1121",
        expirationDate = "2021/12",
        cardHolderName = "Giant Octopus",
        friendlyName = "Big Octo",
        currency = "USD",
        cvv = "564",
        availableBalance = 12345.6789,
        currentBalance = 13579.2468,
        minPayment = 25.12,
        dueDate = "2026-02-23",
        reservations = 5463.47,
        balanceCarriedOverFromLastStatement = 15168.0,
        spendingsSinceLastStatement = 12312.0,
        yourLastRepayment = "2020-01-16",
        accountDetails = AccountDetails(15684.17, "12-345-6"),
        status = CardStatus.ACTIVE,
        cardImage = "1"
    )

    val testCardWithMalformedValues = CardModel(
        cardId = "A",
        issuer = "Wrongly Typed Issues",
        cardNumber = "123-456",
        expirationDate = "2021/12",
        cardHolderName = "345",
        friendlyName = "Three Four Five",
        currency = "ICN",
        cvv = "abc",
        availableBalance = 1000.0,
        currentBalance = 250.0,
        minPayment = 0.0,
        dueDate = "Due Date",
        reservations = 0.0,
        balanceCarriedOverFromLastStatement = 0.0,
        spendingsSinceLastStatement = 0.0,
        yourLastRepayment = "Your Last Re-Payment",
        accountDetails = AccountDetails(0.0, "AccountNumber"),
        status = CardStatus.BLOCKED,
        cardImage = "Image"
    )

    val testBundle = bundleOf("cardModel" to testCardOne)
    val testBundleWithMalformedValues = bundleOf("cardModel" to testCardWithMalformedValues)
}