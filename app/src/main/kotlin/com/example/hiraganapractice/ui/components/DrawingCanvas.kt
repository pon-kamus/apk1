package com.example.hiraganapractice.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiraganapractice.ui.theme.BorderGray
import com.example.hiraganapractice.ui.theme.DarkGray
import com.example.hiraganapractice.ui.theme.LightGray

data class Line(
    val points: List<Offset>,
    val color: Color = DarkGray,
    val strokeWidth: Float = 30f
)

@Composable
fun DrawingCanvas(
    guideChar: String,
    modifier: Modifier = Modifier,
    lines: MutableList<Line> = remember { mutableStateListOf<Line>() }
) {
    Box(
        modifier = modifier
            .size(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(2.dp, BorderGray, RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        // Guide Character
        Text(
            text = guideChar,
            fontSize = 200.sp,
            color = LightGray,
            modifier = Modifier.align(Alignment.Center)
        )

        // Drawing Layer
        val currentPoints = remember { mutableStateListOf<Offset>() }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            currentPoints.add(offset)
                        },
                        onDrag = { change, _ ->
                            change.consume()
                            currentPoints.add(change.position)
                        },
                        onDragEnd = {
                            lines.add(Line(currentPoints.toList()))
                            currentPoints.clear()
                        }
                    )
                }
        ) {
            // Draw completed lines
            lines.forEach { line ->
                drawPath(
                    path = createPath(line.points),
                    color = line.color,
                    style = Stroke(
                        width = line.strokeWidth,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }

            // Draw current line
            if (currentPoints.isNotEmpty()) {
                drawPath(
                    path = createPath(currentPoints),
                    color = DarkGray,
                    style = Stroke(
                        width = 30f,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }
    }
}

private fun createPath(points: List<Offset>): Path {
    val path = Path()
    if (points.isNotEmpty()) {
        path.moveTo(points.first().x, points.first().y)
        for (i in 1 until points.size) {
            path.lineTo(points[i].x, points[i].y)
        }
    }
    return path
}
