package com.experiment.tubelighteffect.tubelight

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp

@Composable
fun Light(
    mainGlowColor: Color = Color(0xFFFF9800),
    glowColor: Color = Color(0xFFE91E63),
    flip: Boolean = true,
    startY: Float,
    progress: Float,
    modifier: Modifier,
    halfTubeWidth: Float,
) {
    val animationProgress = lerp(0.5f, 1f, easeIn(progress))

    Box(
        modifier = modifier
            .alpha(easeIn(progress))
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                val centerOffset = Offset(
                    x = size.width - halfTubeWidth,
                    y = startY
                )

                val sweepGradient1 = Brush.sweepGradient(
                    colorStops = arrayOf(
                        0.0f to mainGlowColor,
                        animationProgress * 0.49f to Color.Transparent,
                        1.0f to Color.Transparent,
                    ),
                    center = centerOffset
                )

                val sweepGradient2 = Brush.sweepGradient(
                    colorStops = arrayOf(
                        0.0f to glowColor,
                        0.0f to glowColor.copy(alpha = animationProgress * 0.65f),
                        animationProgress * 0.35f to Color.Transparent,
                        1.0f to Color.Transparent,
                    ),
                    center = centerOffset
                )

                val start = 20.dp.toPx()
                val end = 450.dp.toPx()

                val mask = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to Color.White,
                        start / size.height to Color.White,
                        (start + (end - start) * 0.25f) / size.height to Color.White.copy(alpha = 0.7f),
                        (start + (end - start) * 0.55f) / size.height to Color.White.copy(alpha = 0.35f),
                        (start + (end - start) * 0.7f) / size.height to Color.White.copy(alpha = 0.15f),
                        end / size.height to Color.Transparent,
                        1f to Color.Transparent
                    )
                )

                onDrawBehind {
                    scale(
                        scaleX = if (flip) -1f else 1f,
                        scaleY = 1f
                    ) {
                        drawRect(
                            brush = sweepGradient1,
                            blendMode = BlendMode.Plus
                        )
                        drawRect(
                            brush = sweepGradient2,
                            blendMode = BlendMode.Plus
                        )
                    }

                    drawRect(
                        brush = mask,
                        blendMode = BlendMode.DstIn,
                        alpha = 0.98f
                    )
                }
            }
    )
}

@Composable
fun SimpleLight(
    mainGlowColor: Color = Color(0xFF00DEFF),
    flip: Boolean = true,
    startY: Float,
    modifier: Modifier,
    halfTubeWidth: Float,
    progress: Float,
) {

    val animationProgress = lerp(0.5f, 1f, easeIn(progress))
    Box(
        modifier = modifier
            .drawWithCache {
                val centerOffset = Offset(
                    x = size.width - halfTubeWidth,
                    y = startY
                )

                val sweepGradient1 = Brush.sweepGradient(
                    colorStops = arrayOf(
                        0.0f to mainGlowColor,
                        animationProgress * 0.49f to Color.Transparent,
                        1.0f to Color.Transparent,
                    ),
                    center = centerOffset
                )

                onDrawBehind {
                    scale(
                        scaleX = if (flip) -1f else 1f,
                        scaleY = 1f
                    ) {
                        drawRect(
                            brush = sweepGradient1,
                            //blendMode = BlendMode.Plus
                        )
                    }
                }
            }
    )
}

fun easeIn(t: Float) = t * t

@Composable
@Preview
fun LightPreview() {
    Light(
        startY = 100f,
        progress = 1f,
        modifier = Modifier
            .width(200.dp)
            .height(200.dp),
        halfTubeWidth = 200f,
        flip = false
    )
}