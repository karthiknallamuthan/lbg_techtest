package com.lbg.techassest.common

import org.junit.Assert.*
import org.junit.Test

class UtilsTimeKtTest{

    @Test
    fun `convertDateToFormattedString should return formatted date`(){
        val date = "2021-01-01"
        val expected = "Viernes 01 de enero del 2021"
        val result = convertDateToFormattedString(date)
        assertEquals(expected, result)
    }

    @Test
    fun `convertDateToFormattedString should return same date`(){
        val sut = "2021"
        val result = convertDateToFormattedString(sut)
        assertEquals(sut, result)
    }

    @Test
    fun `convertDateToFormattedString should return same date when date is invalid`(){
        val date = "2021-01-01T00:00:00.000Z"
        val expected = "Viernes 01 de enero del 2021"

        val result = convertDateToFormattedString(date)
        assertEquals(expected, result)
    }

    @Test
    fun `convertDateToFormattedString should return same date when date is invalid 2`(){
        // Arrange
        val date = "Viernes 01 de enero del 2021"
        // Act
        val result = convertDateToFormattedString(date)
        // Assert
        assertEquals(date, result)
    }

    @Test
    fun `convertEmptyDateToFormattedString should return same date when date is empty`(){
        // Arrange
        val date = ""
        // Act
        val result = convertDateToFormattedString(date)
        // Assert
        assertEquals(date, result)
    }

}