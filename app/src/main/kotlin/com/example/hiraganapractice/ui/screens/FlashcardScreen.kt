package com.example.hiraganapractice.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiraganapractice.KanaViewModel
import com.example.hiraganapractice.ui.components.Flashcard
import com.example.hiraganapractice.ui.theme.BluePrimary
import com.example.hiraganapractice.ui.theme.TextGray

@Composable
fun FlashcardScreen(viewModel: KanaViewModel) {
    val currentKana = viewModel.flashcardKana

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "TEBAK CARA BACA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Flashcard(
            kana = currentKana.char,
            romaji = currentKana.romaji,
            isFlipped = viewModel.isFlashcardFlipped,
            onFlip = { viewModel.isFlashcardFlipped = !viewModel.isFlashcardFlipped }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tap kartu untuk membalik",
            fontSize = 14.sp,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.nextFlashcard() },
            modifier = Modifier.padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BluePrimary, contentColor = Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Acak Karakter Lain", fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}
