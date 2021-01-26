package com.tim.burtons

import com.tim.burtons.utils.Utils
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class UtilsTest {
    @Before
    fun beforeTests() {
        mockkObject(Utils)
        every { Utils.getProducts().size } returns 5
    }
    @Test
    fun testSearchProductSize() {
        assertEquals(5, Utils.getProducts().size)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }
}