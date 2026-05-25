package com.example.hiraganapractice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiraganapractice.KanaData
import com.example.hiraganapractice.KanaViewModel
import com.example.hiraganapractice.ui.components.DrawingCanvas
import com.example.hiraganapractice.ui.theme.BlueBorder
import com.example.hiraganapractice.ui.theme.BlueLight
import com.example.hiraganapractice.ui.theme.BorderGray
import com.example.hiraganapractice.ui.theme.DarkGray
import com.example.hiraganapractice.ui.theme.RedLight
import com.example.hiraganapractice.ui.theme.RedText
import com.example.hiraganapractice.ui.theme.TextGray

@Composable
fun PracticeScreen(viewModel: KanaViewModel) {
    val currentIndex = viewModel.currentIndex
    val currentKana = KanaData.validKana[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main Canvas
        DrawingCanvas(
            guideChar = currentKana.char,
            lines = viewModel.drawingLines
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stroke Guide Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(BlueLight)
                .border(1.dp, BlueBorder, RoundedCornerShape(8.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Cara Menulis「${currentKana.char}」(${currentKana.romaji})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF004085)
                )
                Text(
                    text = currentKana.instruction,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF004085),
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.prevKana() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = BorderGray, contentColor = DarkGray),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Prev")
            }

            Button(
                onClick = { viewModel.clearDrawing() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = RedLight, contentColor = RedText),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Clear")
            }

            Button(
                onClick = { viewModel.nextKana() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = BorderGray, contentColor = DarkGray),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Kana Grid
        Text(
            text = "PILIH KARAKTER",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 8.dp, bottom = 8.dp)
        )

        val gridItems = KanaData.kanaChart
        // Use fixed height but let the grid be as high as it needs to be inside the column
        // Removed the outer Box with fixed height and LazyVerticalGrid with userScrollEnabled = false
        // Using a Flow-like grid or just a non-lazy grid for better integration with verticalScroll

        KanaGrid(
            items = gridItems,
            selectedChar = currentKana.char,
            onCharClick = { char ->
                val newIndex = KanaData.validKana.indexOfFirst { it.char == char }
                if (newIndex != -1) {
                    viewModel.updateCurrentIndex(newIndex)
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))
        DakutenGuide()
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun KanaGrid(
    items: List<String>,
    selectedChar: String,
    onCharClick: (String) -> Unit
) {
    // Instead of LazyVerticalGrid inside a verticalScroll (which is problematic),
    // we can use a simple column of rows.
    val columns = 5
    val rows = (items.size + columns - 1) / columns

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (i in 0 until rows) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (j in 0 until columns) {
                    val index = i * columns + j
                    if (index < items.size) {
                        val char = items[index]
                        Box(modifier = Modifier.weight(1f)) {
                            if (char.isNotEmpty()) {
                                KanaGridButton(
                                    char = char,
                                    romaji = KanaData.romajiMap[char] ?: "",
                                    isSelected = char == selectedChar,
                                    onClick = { onCharClick(char) }
                                )
                            } else {
                                Spacer(modifier = Modifier.aspectRatio(1f))
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f).aspectRatio(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun KanaGridButton(
    char: String,
    romaji: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) DarkGray else Color.White)
            .border(1.dp, BorderGray, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = char,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else DarkGray
            )
            Text(
                text = romaji,
                fontSize = 10.sp,
                color = if (isSelected) Color.LightGray else TextGray
            )
        }
    }
}

@Composable
fun DakutenGuide() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, BorderGray, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "PETUNJUK TAMBAHAN: DAKUTEN (゛) & HANDAKUTEN (゜)",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Tanda strip dua (Dakuten) dan bulatan (Handakuten) ditambahkan di kanan atas huruf dasar untuk mengubah bunyinya.",
            fontSize = 11.sp,
            color = TextGray
        )
        Spacer(modifier = Modifier.height(12.dp))

        val guides = listOf(
            "K ➔ G (゛)" to "が ぎ ぐ げ ご",
            "S ➔ Z (゛)" to "ざ じ ず ぜ ぞ",
            "T ➔ D (゛)" to "だ ぢ づ で ど",
            "H ➔ B (゛)" to "ば び ぶ べ ぼ"
        )

        guides.chunked(2).forEach { rowGuides ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                rowGuides.forEach { guide ->
                    GuideBox(title = guide.first, kana = guide.second, modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(BlueLight.copy(alpha = 0.5f))
                .border(1.dp, BlueBorder, RoundedCornerShape(8.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("H ➔ P (゜) - Bulatan", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF004085))
                Text("ぱ ぴ ぷ ぺ ぽ", fontSize = 16.sp, color = Color(0xFF004085), letterSpacing = 4.sp)
            }
        }
    }
}

@Composable
fun GuideBox(title: String, kana: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF8F9FA))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DarkGray)
            Text(kana, fontSize = 14.sp, color = DarkGray, letterSpacing = 2.sp)
        }
    }
}
