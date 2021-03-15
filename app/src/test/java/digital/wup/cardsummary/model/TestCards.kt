package digital.wup.cardsummary.model

object TestCards {

    private val testCardOne = CardModel(
        cardId = "1",
        issuer = "Test Issuer 1",
        cardNumber = "123-456-78",
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

    private val testCardTwo = CardModel(
        cardId = "2",
        issuer = "Test Issuer 2",
        cardNumber = "987-654-32",
        expirationDate = "2022/11",
        cardHolderName = "Heavy Dolores",
        friendlyName = "Big Dolly",
        currency = "NZD",
        cvv = "123",
        availableBalance = 54321.9876,
        currentBalance = 97531.8642,
        minPayment = 12.25,
        dueDate = "2024-08-11",
        reservations = 3645.74,
        balanceCarriedOverFromLastStatement = 86151.0,
        spendingsSinceLastStatement = 21312.0,
        yourLastRepayment = "2018-11-05",
        accountDetails = AccountDetails(48615.71, "65-432-1"),
        status = CardStatus.ACTIVE,
        cardImage = "2"
    )

    private val testCardThree = CardModel(
        cardId = "3",
        issuer = "Test Issuer 3",
        cardNumber = "135-24-89",
        expirationDate = "2022/05",
        cardHolderName = "Baptist Barry",
        friendlyName = "Bapty",
        currency = "GBP",
        cvv = "157",
        availableBalance = 4567.89,
        currentBalance = 12345.678,
        minPayment = 15.2,
        dueDate = "2022-08-30",
        reservations = 3468.52,
        balanceCarriedOverFromLastStatement = 18651.20,
        spendingsSinceLastStatement = 32145.46,
        yourLastRepayment = "2019-12-01",
        accountDetails = AccountDetails(86751.25, "13-246-5"),
        status = CardStatus.BLOCKED,
        cardImage = "3"
    )

    val testCardsList = listOf(testCardOne, testCardTwo, testCardThree)
}