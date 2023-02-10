package com.nelkinda.training

import java.util.Date

enum class ExpenseType(val expenseName: String, val expenseCategory: String, val expenseLimit: Int) {
    DINNER("Dinner", "MEAL", 5000),
    BREAKFAST("Breakfast", "MEAL", 1000),
    CAR_RENTAL("Car Rental", "LOGISTICS", Int.MAX_VALUE),
    LUNCH("Lunch", "MEAL", 2000)
}

class Expense {
    lateinit var type: ExpenseType
    var amount: Int = 0

    fun isMealExpense() = this.type.expenseCategory == "MEAL"

    fun checkIfLimitExceeds() = amount > type.expenseLimit
}

class ExpenseReport {
    fun printReport(expenses: List<Expense>, date: Date) {
        var total = 0
        var mealExpenses = 0

        println("Expenses $date")

        for (expense in expenses) {
            if (expense.isMealExpense()) {
                mealExpenses += expense.amount
            }

            val expenseName = expense.type.expenseName

            val mealOverExpensesMarker = mealExpenseLimitExceedMarker(expense)

            println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker)

            total += expense.amount
        }

        println("Meal expenses: $mealExpenses")
        println("Total expenses: $total")
    }

    fun mealExpenseLimitExceedMarker(expense: Expense): String {
        return if (expense.isMealExpense() && expense.checkIfLimitExceeds()) "X" else " "
    }
}
