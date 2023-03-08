package io.nativeblocks.uikit.block

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.nativeblocks.uikit.theme.NativeTheme

data class LinkTextData(
    val text: String,
    val tag: String? = null,
    val annotation: String? = null,
    val onClick: ((str: AnnotatedString.Range<String>) -> Unit)? = null,
)

@Composable
fun NativeLink(
    modifier: Modifier = Modifier,
    linkTextData: List<LinkTextData>,
    style: NativeTextProperties,
    annotatedForegroundColor: Color = Color.Transparent.copy(alpha = 0.0f)
) {
    val annotatedString =
        createAnnotatedString(linkTextData, style.foregroundColor, annotatedForegroundColor)

    ClickableText(
        text = annotatedString,
        overflow = style.overflow,
        maxLines = style.maxLines,
        style = style.textStyle.copy(textAlign = style.textAlign),
        onClick = { offset ->
            linkTextData.forEach { annotatedStringData ->
                if (annotatedStringData.tag != null && annotatedStringData.annotation != null) {
                    annotatedString.getStringAnnotations(
                        tag = annotatedStringData.tag,
                        start = offset,
                        end = offset,
                    ).firstOrNull()?.let {
                        annotatedStringData.onClick?.invoke(it)
                    }
                }
            }
        },
        modifier = modifier.then(modifier),
    )
}

@Composable
private fun createAnnotatedString(
    data: List<LinkTextData>,
    foregroundColor: Color,
    annotationColor: Color
): AnnotatedString {
    return buildAnnotatedString {
        data.forEach { linkTextData ->
            if (linkTextData.tag != null && linkTextData.annotation != null) {
                pushStringAnnotation(
                    tag = linkTextData.tag,
                    annotation = linkTextData.annotation,
                )
                withStyle(
                    style = SpanStyle(
                        color = annotationColor,
                        textDecoration = TextDecoration.None
                    )
                ) {
                    append(linkTextData.text)
                }
                pop()
            } else {
                withStyle(
                    style = SpanStyle(
                        color = foregroundColor,
                        textDecoration = TextDecoration.None
                    )
                ) {
                    append(linkTextData.text)

                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun NativeLinkPreview() {
    NativeLink(
        linkTextData = listOf(
            LinkTextData(
                text = "Icons made by ",
            ),
            LinkTextData(
                text = "smalllikeart",
                tag = "icon_1_author",
                annotation = "https://www.flaticon.com/authors/smalllikeart",
                onClick = {
                },
            ),
            LinkTextData(
                text = " from ",
            ),
            LinkTextData(
                text = "Flaticon",
                tag = "icon_1_source",
                annotation = "https://www.flaticon.com/",
                onClick = {
                },
            )
        ),
        style = NativeTextProperties(
            foregroundColor = Color.Transparent.copy(alpha = 0.0f),
            textStyle = NativeTheme.typographyBuilder(
                NativeTheme.interFontFamily,
                FontWeight.Normal,
                20.sp
            )
        )
    )
}