package com.experiment.tubelighteffect

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.experiment.tubelighteffect.tubelight.Light

@Composable
fun TubelightEffect(modifier: Modifier) {

    var clicked by remember { mutableStateOf(false) }

    val animProgress by animateFloatAsState(
        targetValue = if (clicked) 1f else 0f,
        animationSpec = tween(
            400,
            easing = FastOutSlowInEasing
        ),
        label = "click"
    )

    val animProgress2 by animateFloatAsState(
        targetValue = if (clicked) 1f else 0f,
        animationSpec = tween(
            700,
            easing = FastOutSlowInEasing
        ),
        label = "click"
    )

    var value by remember { mutableFloatStateOf(1.0f) }
    var value2 by remember { mutableFloatStateOf(1.0f) }


    val startY = 160f + 16 * animProgress
    val progress = animProgress2
    val tubeWidth = 300f * animProgress

    Box {

        Column(
            modifier = modifier
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Button(
                modifier = Modifier.padding(bottom = 32.dp),
                onClick = {
                    clicked = !clicked
                }
            ) {
                Text("Start")
            }

//            Slider(
//                value = value,
//                onValueChange = { value = it },
//                valueRange = 0f..1f,
//                modifier = Modifier
//                    .padding(16.dp)
//            )
//            Slider(
//                value = value2,
//                onValueChange = { value2 = it },
//                valueRange = 0f..1f,
//                modifier = Modifier
//                    .padding(16.dp)
//            )
        }

        Text(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(top = 32.dp),
            text = "Tubelight Effect",
            color = Color(0xFFFF9800),
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = modifier.fillMaxSize()
        ) {
            Light(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                flip = false,
                startY = startY,
                progress = progress,
                halfTubeWidth = tubeWidth
            )
            Light(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                startY = startY,
                progress = progress,
                halfTubeWidth = tubeWidth
            )
        }
    }

}

@Composable
@Preview
fun IneffectualnessPreview() {
    TubelightEffect(Modifier)
}
