package com.nelkinda.training

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*


class ExpenseReportTest {

    private fun getExpense(type: ExpenseType, amount: Int): Expense {
        val expense = Expense()
        expense.amount = amount
        expense.type = type
        return expense
    }

    @Test
    fun printReport() {
        //Arrange
        val expenseReport = ExpenseReport()
        val carExpense = getExpense(ExpenseType.CAR_RENTAL, 10)
        val breakFastExpense = getExpense(ExpenseType.BREAKFAST, 100)
        val breakFastExpenseLimitExceeded = getExpense(ExpenseType.BREAKFAST, 10001)
        val dinnerExpense = getExpense(ExpenseType.DINNER, 1000)
        val dinnerExpenseLimitExceeded = getExpense(ExpenseType.DINNER, 50001)
        val expenseList = listOf(
            carExpense,
            breakFastExpense,
            breakFastExpenseLimitExceeded,
            dinnerExpense,
            dinnerExpenseLimitExceeded
        )
        val currentDate = Date()
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        val oldPs: PrintStream = System.out
        System.setOut(ps)

        //Act
        expenseReport.printReport(expenseList, currentDate)

        //Assert
        System.out.flush()
        System.setOut(oldPs)
        assertEquals(
            "Expenses $currentDate\n" +
                    "Car Rental\t10\t \n" +
                    "Breakfast\t100\t \n" +
                    "Breakfast\t10001\tX\n" +
                    "Dinner\t1000\t \n" +
                    "Dinner\t50001\tX\n" +
                    "Meal expenses: 61102\n" +
                    "Total expenses: 61112\n", baos.toString()
        )
    }

    @Test
    fun `print report for lunch type expense where expense marker is X`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val lunchExpense = getExpense(ExpenseType.LUNCH, 2001)
        val expenseList = listOf(lunchExpense)
        val currentDate = Date()
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        val oldPs: PrintStream = System.out
        System.setOut(ps)

        //Act
        expenseReport.printReport(expenseList, currentDate)

        //Assert
        System.out.flush()
        System.setOut(oldPs)
        assertEquals(
            "Expenses $currentDate\n" +
                    "Lunch\t2001\tX\n" +
                    "Meal expenses: 2001\n" +
                    "Total expenses: 2001\n", baos.toString()
        )
    }

    @Test
    fun `mealExpenseLimitExceedMarker should return X for dinner limit exceeded`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val dinnerExpenseLimitExceeded = getExpense(ExpenseType.DINNER, 50001)

        //Act
        val response = expenseReport.mealExpenseLimitExceedMarker(dinnerExpenseLimitExceeded)

        //Assert
        assertEquals("X",response)
    }

    @Test
    fun `mealExpenseLimitExceedMarker should return X for lunch limit exceeded`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val lunchExpenseLimitExceeded = getExpense(ExpenseType.LUNCH, 20001)

        //Act
        val response = expenseReport.mealExpenseLimitExceedMarker(lunchExpenseLimitExceeded)

        //Assert
        assertEquals("X",response)
    }


    @Test
    fun `mealExpenseLimitExceedMarker should return X for breakfast limit exceeded`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val breakfastExpenseLimitExceeded = getExpense(ExpenseType.BREAKFAST, 10001)

        //Act
        val response = expenseReport.mealExpenseLimitExceedMarker(breakfastExpenseLimitExceeded)

        //Assert
        assertEquals("X",response)
    }

    @Test
    fun `mealExpenseLimitExceedMarker should return space for breakfast expense within limit`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val breakfastExpense = getExpense(ExpenseType.BREAKFAST, 999)

        //Act
        val response = expenseReport.mealExpenseLimitExceedMarker(breakfastExpense)

        //Assert
        assertEquals(" ",response)
    }

    @Test
    fun `mealExpenseLimitExceedMarker should return space for dinner expense within limit`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val dinnerExpense = getExpense(ExpenseType.DINNER, 4999)

        //Act
        val response = expenseReport.mealExpenseLimitExceedMarker(dinnerExpense)

        //Assert
        assertEquals(" ",response)
    }

    @Test
    fun `mealExpenseLimitExceedMarker should return space for lunch expense within limit`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val lunchExpense = getExpense(ExpenseType.LUNCH, 999)

        //Act
        val response = expenseReport.mealExpenseLimitExceedMarker(lunchExpense)

        //Assert
        assertEquals(" ",response)
    }

    @Test
    fun `mealExpenseLimitExceedMarker should return space for expense type other than MEAL`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val carRentalExpense = getExpense(ExpenseType.CAR_RENTAL, 4999)

        //Act
        val response = expenseReport.mealExpenseLimitExceedMarker(carRentalExpense)

        //Assert
        assertEquals(" ",response)
    }
    s
}