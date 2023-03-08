package io.nativeblocks.uikit.block

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class NativeBadgeProperties(
    val foregroundColor: Color,
    val backgroundColor: Color? = null,
    val shadow: Dp? = null,
    val textStyle: TextStyle,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val shape: Shape = RoundedCornerShape(0.dp),
)

@Composable
fun NativeBadge(
    modifier: Modifier,
    label: String,
    properties: NativeBadgeProperties,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .clip(properties.shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(true)
            ) {
                onClick.invoke()
            }

    ) {
        Surface(
            elevation = properties.shadow ?: 0.dp,
            shape = properties.shape,
            color = properties.backgroundColor ?: Color.Transparent.copy(alpha = 0.0f),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                properties.leadingIcon?.let { leadingIcon ->
                    if (label.isNotEmpty()) {
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    }
                    leadingIcon.invoke()
                }
                NativeText(
                    text = label,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .then(modifier),
                    properties = NativeTextProperties(
                        textStyle = properties.textStyle,
                        foregroundColor = properties.foregroundColor,
                    )
                )
                properties.trailingIcon?.let { trailingIcon ->
                    trailingIcon.invoke()
                    if (label.isNotEmpty()) {
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    }
                }
            }
        }
    }
}
