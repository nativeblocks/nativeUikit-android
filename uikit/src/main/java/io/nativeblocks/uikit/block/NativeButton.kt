package io.nativeblocks.uikit.block

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class NativeButtonProperties(
    val foregroundColor: Color,
    val backgroundColor: Color,
    val boarderColor: Color? = null,
    val textStyle: TextStyle,
    val shape: Shape = RectangleShape,
    val shadow: Dp = 0.dp,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val enable: Boolean = true
)

@Immutable
data class NativeIconButtonProperties(
    val foregroundColor: Color,
    val backgroundColor: Color,
    val boarderColor: Color? = null,
    val shape: Shape = RectangleShape,
    val shadow: Dp = 0.dp,
    val enable: Boolean = true
)

@Composable
fun NativeIconButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    iconUrl: String = "",
    properties: NativeIconButtonProperties,
    onClick: (() -> Unit) = {}
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .clip(properties.shape)
            .then(modifier),
        enabled = properties.enable,
    ) {
        NativeImage(
            modifier = Modifier
                .clip(properties.shape)
                .background(properties.backgroundColor)
                .padding(contentPadding),
            imageUrl = iconUrl,
            properties = NativeImageProperties(foregroundColor = properties.foregroundColor)
        )
    }
}

@Composable
fun NativeButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
    buttonText: String = "",
    properties: NativeButtonProperties,
    onClick: (() -> Unit) = {}
) {
    val boarderColor = if (properties.enable) {
        properties.boarderColor
    } else {
        properties.boarderColor?.copy(alpha = ContentAlpha.disabled)
    }
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = properties.backgroundColor,
            contentColor = properties.foregroundColor,
            disabledBackgroundColor = properties.backgroundColor.copy(alpha = ContentAlpha.disabled),
            disabledContentColor = properties.foregroundColor.copy(alpha = ContentAlpha.disabled),
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = properties.shadow,
            pressedElevation = properties.shadow,
            disabledElevation = 0.dp,
            hoveredElevation = properties.shadow,
            focusedElevation = properties.shadow,
        ),
        modifier = Modifier.then(modifier),
        shape = properties.shape,
        border = BorderStroke(1.dp, boarderColor ?: Color.Unspecified),
        enabled = properties.enable
    ) {
        ButtonContent(buttonText, properties, contentPadding)
    }
}

@Composable
private fun ButtonContent(
    buttonText: String,
    properties: NativeButtonProperties,
    contentPadding: PaddingValues
) {
    Row(
        modifier = Modifier.padding(contentPadding), verticalAlignment = Alignment.CenterVertically
    ) {
        properties.leadingIcon?.let { leadingIcon ->
            leadingIcon.invoke()
            if (buttonText.isNotEmpty()) {
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
        }
        if (buttonText.isNotEmpty()) {
            NativeText(
                text = buttonText,
                properties = NativeTextProperties(
                    foregroundColor = properties.foregroundColor,
                    textStyle = properties.textStyle
                )
            )
        }
        properties.trailingIcon?.let { trailingIcon ->
            if (buttonText.isNotEmpty()) {
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            trailingIcon.invoke()
        }
    }
}