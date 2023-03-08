package io.nativeblocks.uikit.block

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class NativeToggleProperties(
    val foregroundColor: Color? = null,
    val backgroundColor: Color? = null,
    val enable: Boolean = true,
)

@Composable
fun NativeToggle(
    modifier: Modifier,
    check: Boolean,
    properties: NativeToggleProperties,
    onCheckChange: ((Boolean) -> Unit)?
) {
    val interactionSource = remember { MutableInteractionSource() }
    val alignment by animateAlignmentAsState(if (check) 1f else -1f)

    val backgroundColor = if (properties.enable) {
        if (check) {
            properties.backgroundColor ?: Color.Transparent.copy(alpha = 0.0f)
        } else {
            properties.backgroundColor?.copy(alpha = 0.6f) ?: Color.Transparent.copy(alpha = 0.0f)
        }
    } else {
        properties.backgroundColor?.copy(alpha = ContentAlpha.disabled)
            ?: Color.Transparent.copy(alpha = ContentAlpha.disabled)
    }
    val toggleColor = if (properties.enable) {
        if (check) {
            properties.foregroundColor ?: Color.Transparent.copy(alpha = 0.0f)
        } else {
            properties.foregroundColor?.copy(alpha = 0.6f) ?: Color.Transparent.copy(alpha = 0.0f)
        }
    } else {
        properties.foregroundColor?.copy(alpha = ContentAlpha.disabled)
            ?: Color.Transparent.copy(alpha = ContentAlpha.disabled)
    }

    Column(modifier = Modifier.then(modifier)) {
        Box(
            modifier = Modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(percent = 50)
                )
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) {
                    if (properties.enable)
                        onCheckChange?.invoke(!check)
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxSize(),
                contentAlignment = alignment
            ) {
                NativeShape(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(size = 24.dp)
                        .background(toggleColor)
                        .padding(
                            vertical = 8.dp,
                            horizontal = 4.dp
                        )
                )
            }
        }
    }
}

@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return remember {
        derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
    }
}