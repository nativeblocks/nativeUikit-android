package io.nativeblocks.uikit.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import io.nativeblocks.uikit.R

object NativeTheme {
    fun findArrangementVertical(arrangement: String?): Arrangement.Vertical {
        return when (arrangement) {
            "top" -> Arrangement.Top
            "bottom" -> Arrangement.Bottom
            "center" -> Arrangement.Center
            "spaceBetween" -> Arrangement.SpaceBetween
            "spaceAround" -> Arrangement.SpaceAround
            "spaceEvenly" -> Arrangement.SpaceEvenly
            else -> Arrangement.Top
        }
    }

    fun findArrangementHorizontal(arrangement: String?): Arrangement.Horizontal {
        return when (arrangement) {
            "start" -> Arrangement.Start
            "end" -> Arrangement.End
            "center" -> Arrangement.Center
            "spaceBetween" -> Arrangement.SpaceBetween
            "spaceAround" -> Arrangement.SpaceAround
            "spaceEvenly" -> Arrangement.SpaceEvenly
            else -> Arrangement.Start
        }
    }

    fun findAlignment(alignment: String?): Alignment {
        return when (alignment) {
            "center" -> Alignment.Center
            "centerStart" -> Alignment.CenterStart
            "centerEnd" -> Alignment.CenterEnd
            "bottomCenter" -> Alignment.BottomCenter
            "bottomStart" -> Alignment.BottomStart
            "bottomEnd" -> Alignment.BottomEnd
            "topStart" -> Alignment.TopStart
            "topEnd" -> Alignment.TopEnd
            "topCenter" -> Alignment.TopCenter
            else -> Alignment.Center
        }
    }

    fun findAlignmentHorizontal(alignment: String?): Alignment.Horizontal {
        return when (alignment) {
            "start" -> Alignment.Start
            "end" -> Alignment.End
            "centerHorizontally" -> Alignment.CenterHorizontally
            else -> Alignment.Start
        }
    }

    fun findAlignmentVertical(alignment: String?): Alignment.Vertical {
        return when (alignment) {
            "top" -> Alignment.Top
            "bottom" -> Alignment.Bottom
            "centerVertically" -> Alignment.CenterVertically
            else -> Alignment.Top
        }
    }

    val interFontFamily = FontFamily(
        Font(R.font.inter_regular),
        Font(R.font.inter_medium),
        Font(R.font.inter_semibold),
        Font(R.font.inter_bold)
    )

    fun fontFamilyMapper(textFontFamily: String?): FontFamily {
        return if (textFontFamily == "inter") interFontFamily
        else interFontFamily
    }

    fun fontWeightMapper(fontWeight: String?): FontWeight {
        return when (fontWeight) {
            "thin" -> FontWeight.Thin
            "extraLight" -> FontWeight.ExtraLight
            "light" -> FontWeight.Light
            "normal" -> FontWeight.Normal
            "medium" -> FontWeight.Medium
            "semiBold" -> FontWeight.SemiBold
            "bold" -> FontWeight.Bold
            "extraBold" -> FontWeight.ExtraBold
            "black" -> FontWeight.Black
            else -> FontWeight.Normal
        }
    }

    fun typographyBuilder(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
        fontSize: TextUnit
    ): TextStyle {
        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize
        )
    }

    fun textAlignmentMapper(align: String?): TextAlign {
        return when (align) {
            "start" -> TextAlign.Start
            "center" -> TextAlign.Center
            "end" -> TextAlign.End
            "justify" -> TextAlign.Justify
            else -> TextAlign.Start
        }
    }

    fun textOverflowMapper(textOverflow: String?): TextOverflow {
        return when (textOverflow) {
            "clip" -> TextOverflow.Clip
            "ellipsis" -> TextOverflow.Ellipsis
            "visible" -> TextOverflow.Visible
            else -> TextOverflow.Clip
        }
    }

    @Composable
    fun shapeMapper(
        shapeName: String?,
        shapeRadiusTopStart: String?,
        shapeRadiusTopEnd: String?,
        shapeRadiusBottomStart: String?,
        shapeRadiusBottomEnd: String?
    ): Shape {
        return when (shapeName) {
            "circle" -> CircleShape
            "rectangle" -> RoundedCornerShape(
                topStart = shapeRadiusTopStart?.toInt()?.dp ?: 0.dp,
                topEnd = shapeRadiusTopEnd?.toInt()?.dp ?: 0.dp,
                bottomStart = shapeRadiusBottomStart?.toInt()?.dp ?: 0.dp,
                bottomEnd = shapeRadiusBottomEnd?.toInt()?.dp ?: 0.dp,
            )
            else -> RectangleShape
        }
    }

    fun scaleTypeMapper(scaleType: String?): ContentScale {
        return when (scaleType) {
            "none" -> ContentScale.None
            "crop" -> ContentScale.Crop
            "inside" -> ContentScale.Inside
            "fit" -> ContentScale.Fit
            "fillBounds" -> ContentScale.FillBounds
            "fillWidth" -> ContentScale.FillWidth
            "fillHeight" -> ContentScale.FillHeight
            else -> ContentScale.None
        }
    }

    fun rgbColorBuilder(colorString: String?, opacity: String?): Color {
        val alpha = (opacity?.toInt()?.div(100.0f)) ?: 1.0f
        val color = colorString?.let { Color(android.graphics.Color.parseColor(it)) }
            ?: Color.Unspecified
        return color.copy(alpha = alpha)
    }

}

@Composable
fun NativeTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider {
        content()
    }
}