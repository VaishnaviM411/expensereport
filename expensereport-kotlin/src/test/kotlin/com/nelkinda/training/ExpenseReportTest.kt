package com.nelkinda.training

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*


class ExpenseReportTest{

    @Test
    fun printReport() {
        //Arrange
        val expenseReport: ExpenseReport = ExpenseReport()
        val expense = Expense()
        expense.amount = 10
        expense.type = ExpenseType.CAR_RENTAL
        val expenseList = listOf<Expense>(expense)
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
        assertEquals("Expenses "+currentDate.toString()+"\n" +
                "Car Rental\t10\t \n" +
                "Meal expenses: 0\n" +
                "Total expenses: 10\n",baos.toString())
    }
}