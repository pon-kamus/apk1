package com.example.hiraganapractice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hiraganapractice.ui.components.Line

class KanaViewModel : ViewModel() {
    // Practice Screen State
    var currentIndex by mutableIntStateOf(0)
        private set

    val drawingLines = mutableStateListOf<Line>()

    fun updateCurrentIndex(index: Int) {
        currentIndex = index
        drawingLines.clear()
    }

    fun clearDrawing() {
        drawingLines.clear()
    }

    fun nextKana() {
        updateCurrentIndex((currentIndex + 1) % KanaData.validKana.size)
    }

    fun prevKana() {
        updateCurrentIndex((currentIndex - 1 + KanaData.validKana.size) % KanaData.validKana.size)
    }

    // Flashcard Screen State
    var flashcardKana by mutableStateOf(KanaData.validKana.random())
        private set

    var isFlashcardFlipped by mutableStateOf(false)

    fun nextFlashcard() {
        isFlashcardFlipped = false
        flashcardKana = KanaData.validKana.random()
    }
}
