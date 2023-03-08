package io.nativeblocks.uikit.block

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.nativeblocks.uikit.R
import io.nativeblocks.uikit.theme.NativeTheme

@Immutable
data class NativeTextFieldProperties(
    val foregroundColor: Color,
    val textHintColor: Color,
    val backgroundColor: Color,
    val borderColor: Color,
    val borderFocusedColor: Color,
    val shape: Shape? = null,
    val textStyle: TextStyle,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val textAlign: TextAlign? = TextAlign.Start,
    val overflow: TextOverflow = TextOverflow.Clip,
    val maxLines: Int = Int.MAX_VALUE,
    val singleLine: Boolean = true,
    val readOnly: Boolean = false,
    val enable: Boolean = true,
    val isError: Boolean = false,
    val hintText: String = "",
    val imeAction: ImeAction = ImeAction.Done,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val keyboardActions: KeyboardActions = KeyboardActions(),
)

@Composable
fun NativeTextField(
    modifier: Modifier = Modifier,
    text: String,
    properties: NativeTextFieldProperties,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.then(modifier),
        value = text,
        onValueChange = {
            onValueChange.invoke(it)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = properties.imeAction,
            keyboardType = properties.keyboardType
        ),
        keyboardActions = properties.keyboardActions,
        textStyle = properties.textStyle.copy(textAlign = properties.textAlign),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = properties.foregroundColor,
            backgroundColor = properties.backgroundColor,
            focusedBorderColor = properties.borderFocusedColor,
            unfocusedBorderColor = properties.borderColor,
        ),
        readOnly = properties.readOnly,
        isError = properties.isError,
        enabled = properties.enable,
        leadingIcon = properties.leadingIcon,
        trailingIcon = properties.trailingIcon,
        shape = properties.shape ?: RoundedCornerShape(12.dp),
        maxLines = properties.maxLines,
        singleLine = properties.singleLine,
        placeholder = {
            NativeText(
                text = properties.hintText,
                properties = NativeTextProperties(
                    foregroundColor = properties.textHintColor,
                    textStyle = properties.textStyle
                ),
            )
        }
    )
}

@Preview(showBackground = false)
@Composable
fun DefaultTextFieldPreview() {
    Column {
        NativeTextField(
            text = "Test",
            properties = NativeTextFieldProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                textHintColor = NativeTheme.rgbColorBuilder("#ffffff", "60"),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_android_black_24dp),
                        contentDescription = "icon",
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        tint = NativeTheme.rgbColorBuilder("#ffffff", "100")
                    )
                },
                hintText = "Write your idea",
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    20.sp
                ),
                textAlign = TextAlign.Start,
                backgroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                borderColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                borderFocusedColor = NativeTheme.rgbColorBuilder("#ff00ff", "100")
            ),
            onValueChange = {
            }
        )
        NativeTextField(
            text = "Test",
            properties = NativeTextFieldProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                textHintColor = NativeTheme.rgbColorBuilder("#ffffff", "60"),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_android_black_24dp),
                        contentDescription = "icon",
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        tint = NativeTheme.rgbColorBuilder("#ffffff", "100")
                    )
                },
                hintText = "Write your idea",
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    20.sp
                ),
                textAlign = TextAlign.Start,
                backgroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                borderColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                borderFocusedColor = NativeTheme.rgbColorBuilder("#ff00ff", "100")
            ),
            onValueChange = {
            }
        )
        NativeTextField(
            text = "alireza@.com",
            properties = NativeTextFieldProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffff00", "100"),
                textHintColor = NativeTheme.rgbColorBuilder("#ffff00", "60"),
                hintText = "email",
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    20.sp
                ),
                textAlign = TextAlign.Start,
                backgroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                borderColor = NativeTheme.rgbColorBuilder("#ffff00", "100"),
                borderFocusedColor = NativeTheme.rgbColorBuilder("#ff00ff", "100")
            ),
            onValueChange = {
            }
        )
    }
}