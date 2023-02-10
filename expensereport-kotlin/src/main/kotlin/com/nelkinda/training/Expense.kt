package com.nelkinda.training

class Expense {
    lateinit var type: ExpenseType
    var amount: Int = 0

    fun isMealExpense() = this.type.expenseCategory == "MEAL"

    fun checkIfLimitExceeds() = amount > type.expenseLimit
}
