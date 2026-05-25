package com.example.hiraganapractice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hiraganapractice.KanaViewModel
import com.example.hiraganapractice.ui.theme.DarkGray
import com.example.hiraganapractice.ui.theme.TextGray

enum class Mode {
    PRACTICE, FLASHCARD
}

@Composable
fun MainScreen(viewModel: KanaViewModel = viewModel()) {
    var mode by remember { mutableStateOf(Mode.PRACTICE) }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hiragana Practice",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkGray
                )
                Text(
                    text = "Ikuti panduan abu-abu untuk menulis",
                    fontSize = 14.sp,
                    color = TextGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Custom Tabs
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE9ECEF))
                        .padding(4.dp)
                ) {
                    TabButton(
                        text = "Latihan Tulis",
                        isSelected = mode == Mode.PRACTICE,
                        onClick = { mode = Mode.PRACTICE },
                        modifier = Modifier.weight(1f)
                    )
                    TabButton(
                        text = "Tes Hafalan",
                        isSelected = mode == Mode.FLASHCARD,
                        onClick = { mode = Mode.FLASHCARD },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (mode) {
                Mode.PRACTICE -> PracticeScreen(viewModel)
                Mode.FLASHCARD -> FlashcardScreen(viewModel)
            }
        }
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color.White else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) DarkGray else TextGray
        )
    }
}
