package com.jedev.whatsnumber.domains.historic.model

import org.junit.Assert
import org.junit.Test


class TempContactModelTest {

    @Test
    fun shouldReturnNumberFormatted() {
        TempContactModel(
            id = 1,
            prefix = "55",
            number = "11999999999"
        ).let {
            Assert.assertEquals("+55 (11)99999-9999", it.numberFormatted)
        }

        TempContactModel(
            id = 1,
            prefix = "55",
            number = "9999999999"
        ).let {
            Assert.assertEquals("+55 (99)9999-9999", it.numberFormatted)
        }
    }

    @Test
    fun shouldReturnNumberIsValid() {
        TempContactModel(
            id = 1,
            prefix = "55",
            number = "11999999999"
        ).let {
            Assert.assertTrue(it.isValid)
        }

        TempContactModel(
            id = 1,
            prefix = "55",
            number = "9999999999"
        ).let {
            Assert.assertTrue(it.isValid)
        }
    }
}
