package com.nelkinda.training

import java.util.Date

enum class ExpenseType (val expenseName: String){
    DINNER("Dinner"),
    BREAKFAST("Breakfast"),
    CAR_RENTAL("Car Rental")
}

class Expense {
    lateinit var type: ExpenseType
    var amount: Int = 0
}

class ExpenseReport {
    fun printReport(expenses: List<Expense>, date: Date) {
        var total = 0
        var mealExpenses = 0

        println("Expenses $date")

        for (expense in expenses) {
            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount
            }

            val expenseName = expense.type.expenseName

            val mealOverExpensesMarker = checkExpenseMarker(expense)

            println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker)

            total += expense.amount
        }

        println("Meal expenses: $mealExpenses")
        println("Total expenses: $total")
    }

    fun checkExpenseMarker(expense: Expense): String {
        val mealOverExpensesMarker =
            if (expense.type == ExpenseType.DINNER && expense.amount > 5000 || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000) "X" else " "
        return mealOverExpensesMarker
    }
}
