package io.nativeblocks.uikit.block

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.nativeblocks.uikit.R
import io.nativeblocks.uikit.theme.NativeTheme
import io.nativeblocks.uikit.util.isHttpUrl
import coil.compose.AsyncImage

@Immutable
data class NativeImageProperties(
    val backgroundColor: Color? = null,
    val foregroundColor: Color? = null,
    val shape: Shape? = null,
    val scaleType: ContentScale? = null,
)

@Composable
fun NativeImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    @DrawableRes imageId: Int? = null,
    @DrawableRes placeHolder: Int? = null,
    placeHolderContent: @Composable (() -> Unit)? = null,
    contentDescription: String = "",
    properties: NativeImageProperties
) {
    val modifierResult = Modifier
        .background(color = properties.backgroundColor ?: Color.Transparent.copy(alpha = 0.0f))
        .clip(properties.shape ?: RectangleShape)
        .then(modifier)

    if (imageId != null) {
        Image(
            colorFilter = properties.foregroundColor?.let { color ->
                ColorFilter.tint(color = color)
            },
            painter = painterResource(imageId),
            contentDescription = contentDescription,
            contentScale = properties.scaleType ?: ContentScale.Crop,
            modifier = modifierResult
        )
    }
    if (imageUrl?.isHttpUrl() == true) {
        AsyncImage(
            model = imageUrl.trim(),
            placeholder = placeHolder?.let { plc ->
                painterResource(id = plc)
            },
            error = placeHolder?.let { plc ->
                painterResource(id = plc)
            },
            fallback = placeHolder?.let { plc ->
                painterResource(id = plc)
            },
            contentDescription = contentDescription,
            contentScale = properties.scaleType ?: ContentScale.Crop,
            colorFilter = properties.foregroundColor?.let { color ->
                ColorFilter.tint(color = color)
            },
            modifier = modifierResult
        )
    } else {
        placeHolderContent?.invoke()
    }
}

@Preview
@Composable
fun NativeImagePreview() {
    Column {
        NativeImage(
            modifier = Modifier
                .size(32.dp)
                .clip(shape = CircleShape),
            imageId = R.drawable.ic_android_black_24dp,
            placeHolder = R.drawable.ic_android_black_24dp,
            contentDescription = "",
            properties = NativeImageProperties(backgroundColor = Color.Transparent.copy(alpha = 0.0f))
        )
        NativeImage(
            modifier = Modifier,
            imageUrl = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
            placeHolder = R.drawable.ic_android_black_24dp,
            contentDescription = "",
            properties = NativeImageProperties(backgroundColor = Color.Transparent.copy(alpha = 0.0f))
        )
    }
}