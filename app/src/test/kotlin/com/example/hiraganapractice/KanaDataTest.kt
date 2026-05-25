package com.example.hiraganapractice

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KanaDataTest {
    @Test
    fun testKanaDataIntegrity() {
        // Test that we have the same number of valid kana as entries in romajiMap (minus non-empty chart entries)
        val validKanaChars = KanaData.kanaChart.filter { it.isNotEmpty() }
        assertEquals(validKanaChars.size, KanaData.validKana.size)

        // Test a few specific mappings
        assertEquals("a", KanaData.romajiMap["あ"])
        assertEquals("ka", KanaData.romajiMap["か"])
        assertEquals("n", KanaData.romajiMap["ん"])

        // Test that all valid kana have instructions
        KanaData.validKana.forEach { kana ->
            assertTrue("Instruction for ${kana.char} should not be empty", kana.instruction.isNotEmpty())
        }
    }
}
