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
    fun `checkExpenseMarkerTest should return X for dinner limit exceeded`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val dinnerExpenseLimitExceeded = getExpense(ExpenseType.DINNER, 50001)

        //Act
        val response = expenseReport.checkExpenseMarker(dinnerExpenseLimitExceeded)

        //Assert
        assertEquals("X",response)
    }

    @Test
    fun `checkExpenseMarkerTest should return X for breakfast limit exceeded`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val breakfastExpenseLimitExceeded = getExpense(ExpenseType.BREAKFAST, 10001)

        //Act
        val response = expenseReport.checkExpenseMarker(breakfastExpenseLimitExceeded)

        //Assert
        assertEquals("X",response)
    }

    @Test
    fun `checkExpenseMarkerTest should return space for breakfast expense within limit`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val breakfastExpense = getExpense(ExpenseType.BREAKFAST, 999)

        //Act
        val response = expenseReport.checkExpenseMarker(breakfastExpense)

        //Assert
        assertEquals(" ",response)
    }

    @Test
    fun `checkExpenseMarkerTest should return space for dinner expense within limit`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val dinnerExpense = getExpense(ExpenseType.DINNER, 4999)

        //Act
        val response = expenseReport.checkExpenseMarker(dinnerExpense)

        //Assert
        assertEquals(" ",response)
    }

    @Test
    fun `checkExpenseMarkerTest should return space for expense type that does not have limit`() {
        //Arrange
        val expenseReport = ExpenseReport()
        val carRentalExpense = getExpense(ExpenseType.CAR_RENTAL, 4999)

        //Act
        val response = expenseReport.checkExpenseMarker(carRentalExpense)

        //Assert
        assertEquals(" ",response)
    }

    @Test
    fun `isMealExpense should return true for expense type dinner`() {
        //Arrange
        val dinnerExpense = getExpense(ExpenseType.DINNER, 4999)

        //Act
        val response = dinnerExpense.isMealExpense()

        //Assert
        assertEquals(true, response)
    }

    @Test
    fun `isMealExpense should return true for expense type breakfast`() {
        //Arrange
        val breakfastExpense = getExpense(ExpenseType.BREAKFAST, 100)

        //Act
        val response = breakfastExpense.isMealExpense()

        //Assert
        assertEquals(true, response)
    }

    @Test
    fun `isMealExpense should return false for expense type car rental`() {
        //Arrange
        val carRentalExpense = getExpense(ExpenseType.CAR_RENTAL, 4999)

        //Act
        val response = carRentalExpense.isMealExpense()

        //Assert
        assertEquals(false, response)
    }


}