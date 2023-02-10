package com.nelkinda.training

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExpenseTest{

    private fun getExpense(type: ExpenseType, amount: Int): Expense {
        val expense = Expense()
        expense.amount = amount
        expense.type = type
        return expense
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
    fun `isMealExpense should return true for expense type lunch`() {
        //Arrange
        val lunchExpense = getExpense(ExpenseType.LUNCH, 100)

        //Act
        val response = lunchExpense.isMealExpense()

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