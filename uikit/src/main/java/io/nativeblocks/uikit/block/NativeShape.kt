package io.nativeblocks.uikit.block

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape

@Composable
fun NativeShape(shape: Shape, modifier: Modifier) {
    Column(modifier = Modifier.wrapContentSize(Alignment.Center)) {
        Box(modifier = Modifier
            .clip(shape)
            .then(modifier))
    }
}