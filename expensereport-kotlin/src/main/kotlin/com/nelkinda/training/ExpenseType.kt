package com.nelkinda.training

enum class ExpenseType(val expenseName: String, val expenseCategory: String, val expenseLimit: Int) {
    DINNER("Dinner", "MEAL", 5000),
    BREAKFAST("Breakfast", "MEAL", 1000),
    CAR_RENTAL("Car Rental", "LOGISTICS", Int.MAX_VALUE),
    LUNCH("Lunch", "MEAL", 2000)
}