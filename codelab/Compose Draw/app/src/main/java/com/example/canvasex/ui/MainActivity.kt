package com.example.canvasex.ui

import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.canvasex.ui.theme.CanvasExTheme
import com.example.canvasex.ui.theme.Purple40
import java.math.BigDecimal
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasExTheme {
//                Graph()
                ArtistCard()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Graph() {
    val barColor = Color.White
    val animateProgress = remember { Animatable(0f) }

    LaunchedEffect(key1 = graphData, block = {
        animateProgress.animateTo(1f, tween(3000))
    })

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .background(Purple40)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Canvas(
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(3 / 2f)
                        .fillMaxSize()
                        .drawWithCache {
                            val path = generatePath(graphData, size)
                            val filledPath = Path()
                            filledPath.addPath(path)
                            filledPath.lineTo(size.width, size.height)
                            filledPath.lineTo(0f, size.height)
                            filledPath.close()

                            val brush = Brush.verticalGradient(
                                listOf(
                                    Color.Green.copy(alpha = 0.4f),
                                    Color.Transparent
                                )
                            )
                            onDrawBehind {
                                clipRect(right = size.width * animateProgress.value) {
                                    drawPath(
                                        path, Color.Green,
                                        style = Stroke(2.dp.toPx())
                                    )
                                }
                                drawPath(
                                    filledPath,
                                    brush = brush,
                                    style = Fill
                                )
                            }
                        }
                ) {
                    val barWidthPx = 1.dp.toPx()
                    drawRect(
                        barColor, style = Stroke(barWidthPx)
                    )

                    val verticalLines = 4
                    val verticalSize = size.width / (verticalLines + 1)
                    repeat(verticalLines) { i ->
                        val startX = verticalSize * (i + 1)
                        drawLine(
                            barColor,
                            start = Offset(startX, 0f),
                            end = Offset(startX, size.height),
                            strokeWidth = barWidthPx
                        )
                    }

                    val horizontalLines = 3
                    val sectionSize = size.height / (horizontalLines + 1)
                    repeat(horizontalLines) { i ->
                        val startY = sectionSize * (i + 1)
                        drawLine(
                            barColor,
                            start = Offset(0f, startY),
                            end = Offset(size.width, startY),
                            strokeWidth = barWidthPx
                        )
                    }
                }
            }
        }
    }
}

fun generatePath(data: List<Balance>, size: Size): Path {
    val path = Path()
    val numberEntries = data.size - 1
    val weekWidth = size.width / numberEntries

    val max = data.maxBy { it.amount }
    val min = data.minBy { it.amount } // will map to x= 0, y = height
    val range = max.amount - min.amount
    val heightPxPerAmount = size.height / range.toFloat()

    var previousBalanceX = 0f
    var previousBalanceY = size.height
    data.forEachIndexed { i, balance ->
        if (i == 0) {
            path.moveTo(
                0f,
                size.height - (balance.amount - min.amount).toFloat() *
                        heightPxPerAmount
            )
        }
        val balanceX = i * weekWidth
        val balanceY = size.height - (balance.amount - min.amount).toFloat() *
                heightPxPerAmount
        val controlPoint1 = PointF((balanceX + previousBalanceX) / 2f, previousBalanceY)
        val controlPoint2 = PointF((balanceX + previousBalanceX) / 2f, balanceY)
        path.cubicTo(
            controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y,
            balanceX, balanceY
        )
//        path.lineTo(balanceX, balanceY)
        previousBalanceX = balanceX
        previousBalanceY = balanceY
    }
    return path
}

@RequiresApi(Build.VERSION_CODES.O)
val graphData = listOf(
    Balance(LocalDate.now(), BigDecimal(65631)),
    Balance(LocalDate.now().plusWeeks(1), BigDecimal(65931)),
    Balance(LocalDate.now().plusWeeks(2), BigDecimal(65851)),
    Balance(LocalDate.now().plusWeeks(3), BigDecimal(65931)),
    Balance(LocalDate.now().plusWeeks(4), BigDecimal(66484)),
    Balance(LocalDate.now().plusWeeks(5), BigDecimal(67684)),
    Balance(LocalDate.now().plusWeeks(6), BigDecimal(66684)),
    Balance(LocalDate.now().plusWeeks(7), BigDecimal(66984)),
    Balance(LocalDate.now().plusWeeks(8), BigDecimal(70600)),
    Balance(LocalDate.now().plusWeeks(9), BigDecimal(71600)),
    Balance(LocalDate.now().plusWeeks(10), BigDecimal(72600)),
    Balance(LocalDate.now().plusWeeks(11), BigDecimal(72526)),
    Balance(LocalDate.now().plusWeeks(12), BigDecimal(72976)),
    Balance(LocalDate.now().plusWeeks(13), BigDecimal(73589)),
)

data class Balance(val date: LocalDate, val amount: BigDecimal)

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CanvasExTheme {
        ArtistCard()
    }
}

val onClick = {}

@Composable
fun ArtistCard(/*...*/) {
    val padding = 16.dp
    Row(modifier = Modifier.size(width = 400.dp, height = 100.dp)) {
        Image(modifier = Modifier.requiredSize(150.dp)) {
        }
        Column(
            Modifier
                .padding(padding)
                .clickable(onClick = onClick)
                .fillMaxWidth()
        ) {
            // rest of the implementation
        }
    }

}


