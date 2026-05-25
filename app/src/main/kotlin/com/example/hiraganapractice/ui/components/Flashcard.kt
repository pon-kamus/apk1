package com.example.hiraganapractice.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiraganapractice.ui.theme.BlueBorder
import com.example.hiraganapractice.ui.theme.BlueLight
import com.example.hiraganapractice.ui.theme.BorderGray
import com.example.hiraganapractice.ui.theme.DarkGray
import com.example.hiraganapractice.ui.theme.TextGray

@Composable
fun Flashcard(
    kana: String,
    romaji: String,
    isFlipped: Boolean,
    onFlip: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "FlashcardRotation"
    )

    // To prevent immediate answer update while flipping,
    // we use a state that only updates when rotation is less than 90
    var displayedKana by remember { mutableStateOf(kana) }
    var displayedRomaji by remember { mutableStateOf(romaji) }

    LaunchedEffect(kana, romaji, rotation) {
        if (rotation <= 90f) {
            displayedKana = kana
            displayedRomaji = romaji
        }
    }

    Box(
        modifier = modifier
            .size(width = 250.dp, height = 320.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clickable { onFlip() }
    ) {
        if (rotation <= 90f) {
            // Front (Kana)
            CardFace(
                backgroundColor = Color.White,
                borderColor = BorderGray,
                content = {
                    Text(
                        text = displayedKana,
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkGray
                    )
                }
            )
        } else {
            // Back (Romaji)
            CardFace(
                backgroundColor = BlueLight,
                borderColor = BlueBorder,
                modifier = Modifier.graphicsLayer { rotationY = 180f },
                content = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = displayedRomaji,
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF007BFF)
                        )
                        Text(
                            text = "Jawaban",
                            fontSize = 14.sp,
                            color = TextGray
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun CardFace(
    backgroundColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, borderColor, RoundedCornerShape(24.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
