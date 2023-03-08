package io.nativeblocks.uikit.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.nativeblocks.uikit.theme.NativeTheme

@Immutable
data class NativeTextProperties(
    val foregroundColor: Color,
    val backgroundColor: Color? = null,
    val textStyle: TextStyle,
    val textAlign: TextAlign? = TextAlign.Center,
    val overflow: TextOverflow = TextOverflow.Clip,
    val maxLines: Int = Int.MAX_VALUE
)

@Composable
fun NativeText(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    text: String,
    properties: NativeTextProperties,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        text = text,
        color = properties.foregroundColor,
        style = properties.textStyle,
        modifier = Modifier
            .padding(contentPadding)
            .background(properties.backgroundColor ?: Color.Transparent.copy(alpha = 0.0f))
            .then(modifier),
        textAlign = properties.textAlign,
        overflow = properties.overflow,
        maxLines = properties.maxLines,
        onTextLayout = onTextLayout
    )
}

@Preview(showBackground = false)
@Composable
fun DefaultTextPreview() {
    Column {
        NativeText(
            text = "headline 1", properties = NativeTextProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    36.sp
                )
            )
        )
        NativeText(
            text = "headline 2", properties = NativeTextProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    30.sp
                )
            )
        )
        NativeText(
            text = "subtitle", properties = NativeTextProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    28.sp
                )
            )
        )
        NativeText(
            text = "body 1", properties = NativeTextProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    20.sp
                )
            )
        )
        NativeText(
            text = "body 2", properties = NativeTextProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    14.sp
                )
            )
        )
        NativeText(
            text = "caption", properties = NativeTextProperties(
                foregroundColor = NativeTheme.rgbColorBuilder("#ffffff", "100"),
                textStyle = NativeTheme.typographyBuilder(
                    NativeTheme.interFontFamily,
                    FontWeight.Normal,
                    10.sp
                ),
            )
        )
    }
}