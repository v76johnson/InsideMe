package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.data.model.ZodiacSign
import com.example.ui.theme.CelestialGold
import com.example.ui.theme.MysticViolet
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.ShadowRose
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ChartWheelView(
    sunSign: ZodiacSign,
    moonSign: ZodiacSign,
    risingSign: ZodiacSign,
    modifier: Modifier = Modifier
) {
    val mysticViolet = MysticViolet
    val celestialGold = CelestialGold
    val nebulaTeal = NebulaTeal
    val shadowRose = ShadowRose

    Box(
        modifier = modifier.size(240.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val outerRadius = size.width / 2f - 12.dp.toPx()
            val innerRadius = outerRadius * 0.65f
            val coreRadius = outerRadius * 0.35f

            // 1. Draw outer cosmic rings
            drawCircle(
                color = mysticViolet.copy(alpha = 0.3f),
                radius = outerRadius,
                center = center,
                style = Stroke(width = 2.dp.toPx())
            )

            drawCircle(
                color = celestialGold.copy(alpha = 0.4f),
                radius = innerRadius,
                center = center,
                style = Stroke(width = 1.5.dp.toPx())
            )

            drawCircle(
                color = nebulaTeal.copy(alpha = 0.3f),
                radius = coreRadius,
                center = center,
                style = Stroke(width = 1.dp.toPx())
            )

            // 2. Draw 12 Zodiac Houses lines
            for (i in 0 until 12) {
                val angleRad = Math.toRadians((i * 30).toDouble())
                val start = Offset(
                    (center.x + innerRadius * cos(angleRad)).toFloat(),
                    (center.y + innerRadius * sin(angleRad)).toFloat()
                )
                val end = Offset(
                    (center.x + outerRadius * cos(angleRad)).toFloat(),
                    (center.y + outerRadius * sin(angleRad)).toFloat()
                )
                drawLine(
                    color = Color.White.copy(alpha = 0.15f),
                    start = start,
                    end = end,
                    strokeWidth = 1.dp.toPx()
                )
            }

            // 3. Draw Placement Connectors (Sun, Moon, Rising Aspect Triangle)
            val sunAngle = Math.toRadians((sunSign.ordinal * 30 + 15).toDouble())
            val moonAngle = Math.toRadians((moonSign.ordinal * 30 + 15).toDouble())
            val risingAngle = Math.toRadians((risingSign.ordinal * 30 + 15).toDouble())

            val sunPt = Offset(
                (center.x + innerRadius * 0.85f * cos(sunAngle)).toFloat(),
                (center.y + innerRadius * 0.85f * sin(sunAngle)).toFloat()
            )
            val moonPt = Offset(
                (center.x + innerRadius * 0.85f * cos(moonAngle)).toFloat(),
                (center.y + innerRadius * 0.85f * sin(moonAngle)).toFloat()
            )
            val risingPt = Offset(
                (center.x + innerRadius * 0.85f * cos(risingAngle)).toFloat(),
                (center.y + innerRadius * 0.85f * sin(risingAngle)).toFloat()
            )

            val aspectPath = Path().apply {
                moveTo(sunPt.x, sunPt.y)
                lineTo(moonPt.x, moonPt.y)
                lineTo(risingPt.x, risingPt.y)
                close()
            }

            drawPath(
                path = aspectPath,
                brush = Brush.radialGradient(
                    listOf(celestialGold.copy(alpha = 0.35f), mysticViolet.copy(alpha = 0.1f)),
                    center = center
                )
            )

            drawPath(
                path = aspectPath,
                color = celestialGold,
                style = Stroke(width = 2.dp.toPx())
            )

            // 4. Draw Placement Glowing Nodes
            drawCircle(celestialGold, radius = 6.dp.toPx(), center = sunPt)
            drawCircle(nebulaTeal, radius = 6.dp.toPx(), center = moonPt)
            drawCircle(shadowRose, radius = 6.dp.toPx(), center = risingPt)
        }
    }
}
