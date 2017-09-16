package com.parkingcalculator.parkingcalculator.test;

import static org.junit.Assert.assertEquals;

import com.parkingcalculator.parkingcalculator.ParkingCalculatorPage;
import org.junit.Test;


public class ParkingCalculatorTest extends ParkingCalculatorPage{

    @Test
    public void testMainPageElements() throws Exception{
        driver.get(URL);
        checkScreenElements();
    }

    @Test
    public void validLotDateTimeAm() throws Exception {
        String expectedCost = "$ 2.00";
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "12:00",
                "AM",
                "12:00",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualCost = getCost();
        String actualDaysHoursMinutes = getDaysHoursMinutes();

        assertEquals(expectedCost, actualCost);
        assertEquals(expectedDaysHoursMinutes, actualDaysHoursMinutes);
    }

    @Test
    public void validLotDateTimePm() throws Exception {
        String expectedCost = "$ 2.00";
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "12:00",
                "PM",
                "12:00",
                "PM",
                currentDate(),
                currentDate()
        );

        String actualCost = getCost();
        String actualDaysHoursMinutes = getDaysHoursMinutes();

        assertEquals(expectedCost, actualCost);
        assertEquals(expectedDaysHoursMinutes, actualDaysHoursMinutes);
    }

    @Test
    public void verifyDayChange() throws Exception {
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 1 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "11:59",
                "PM",
                "00:00",
                "AM",
                "01/01/2020",
                "01/02/2020"
        );

        String actualDaysHoursMinutes = getDaysHoursMinutes();

        assertEquals(expectedDaysHoursMinutes, actualDaysHoursMinutes);
    }

    @Test
    public void amTimeFieldsValueUnderTwelve() throws Exception {
        String expectedCost = "$ 2.00";
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "11:59",
                "AM",
                "11:59",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualCost = getCost();
        String actualDaysHoursMinutes = getDaysHoursMinutes();
        String actualEntryTime = getEntryTime();
        String actualLeavingTime = getLeavingTime();

        assertEquals(expectedCost, actualCost);
        assertEquals(expectedDaysHoursMinutes, actualDaysHoursMinutes);

        //Verify times fields value persistence
        assertEquals("11:59", actualEntryTime);
        assertEquals("11:59", actualLeavingTime);
    }

    @Test
    public void pmTimeFieldsValueUnderTwelve() throws Exception {
        String expectedCost = "$ 2.00";
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "11:59",
                "PM",
                "11:59",
                "PM",
                currentDate(),
                currentDate()
        );

        String actualCost = getCost();
        String actualDaysHoursMinutes = getDaysHoursMinutes();
        String actualEntryTime = getEntryTime();
        String actualLeavingTime = getLeavingTime();

        assertEquals(expectedCost, actualCost);
        assertEquals(expectedDaysHoursMinutes, actualDaysHoursMinutes);

        //Verify times fields value persistence
        assertEquals("11:59", actualEntryTime);
        assertEquals("11:59", actualLeavingTime);
    }

    @Test
    public void amTimeFieldsValueAboveTwelve() throws Exception {
        String expectedMessage = "PLEASE ENTER A VALID TIME";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "13:00",
                "AM",
                "13:00",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void pmTimeFieldsValueAboveTwelve() throws Exception {
        String expectedMessage = "PLEASE ENTER A VALID TIME";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "13:00",
                "PM",
                "13:00",
                "PM",
                currentDate(),
                currentDate()
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void leavingBeforeEntryTime() throws Exception {
        String expectedMessage = "ERROR! YOUR EXIT DATE OR TIME IS BEFORE YOUR ENTRY DATE OR TIME";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "05:00",
                "AM",
                "01:00",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void leavingBeforeEntryDate() throws Exception {
        String expectedMessage = "ERROR! YOUR EXIT DATE OR TIME IS BEFORE YOUR ENTRY DATE OR TIME";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "05:00",
                "AM",
                "09:00",
                "AM",
                futureDate(),
                currentDate()
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void alphaCharsInTimeFields() throws Exception {
        String expectedMessage = "ERROR! ENTER A VALID AND CORRECTLY FORMATTED TIME";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "ab:cd",
                "AM",
                "abcd",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void specialCharsInTimeFields() throws Exception {
        String expectedMessage = "ERROR! ENTER A VALID AND CORRECTLY FORMATTED TIME";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "!@:#$",
                "AM",
                "!@#$",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void negativeNumbersInTimeFields() throws Exception {
        String expectedMessage = "ERROR! ENTER A VALID AND CORRECTLY FORMATTED TIME";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "-123",
                "AM",
                "-123",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void alphaCharsInDateFields() throws Exception {
        String expectedMessage = "ERROR! ENTER A VALID AND CORRECTLY FORMATTED DATE";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "05:00",
                "AM",
                "05:00",
                "AM",
                "ab/cd/efgh",
                "abcdefgh"
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void specialCharsInDateFields() throws Exception {
        String expectedMessage = "ERROR! ENTER A VALID AND CORRECTLY FORMATTED DATE";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "05:00",
                "AM",
                "05:00",
                "AM",
                "!@/#$/%&()",
                "!@#$%&*()"
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void negativeNumbersInDateFields() throws Exception {
        String expectedMessage = "ERROR! ENTER A VALID AND CORRECTLY FORMATTED DATE";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "05:00",
                "AM",
                "05:00",
                "AM",
                "-123",
                "-123"
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void dateFieldsWrongDate() throws Exception {
        String expectedMessage = "ERROR! CHECK YOUR DATE INPUT AND TRY AGAIN";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "05:00",
                "AM",
                "05:00",
                "AM",
                "80/50/1000",
                "80/50/1000"
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void emptyTimeFields() throws Exception {
        String expectedMessage = "ERROR! TIME CAN NOT BE BLANK. ENTER A CORRECTLY FORMATTED TIME";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "",
                "AM",
                "",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void emptyDateFields() throws Exception {
        String expectedMessage = "ERROR! DATE CAN NOT BE BLANK. ENTER A CORRECTLY FORMATTED DATE";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "11:00",
                "AM",
                "11:00",
                "AM",
                "",
                ""
        );

        String actualMessage = getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void checkFebruaryDaysAmount() throws Exception {
        String expectedMessage = "(28 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "11:00",
                "AM",
                "11:00",
                "AM",
                "02/01/2017",
                "03/01/2017"
        );

        String actualMessage = getDaysHoursMinutes();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void checkLeapYearFebruaryDaysAmount() throws Exception {
        String expectedMessage = "(29 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "11:00",
                "AM",
                "11:00",
                "AM",
                "02/01/2020",
                "03/01/2020"
        );

        String actualMessage = getDaysHoursMinutes();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void testWaitElement() throws Exception {
        driver.get(URL);
        waitForElement(LOT_COMBO_BOX);
    }

}