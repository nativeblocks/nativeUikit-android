package io.nativeblocks.uikit.block

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.nativeblocks.uikit.theme.NativeTheme

@Immutable
data class NativeCheckboxProperties(
    val foregroundColor: Color? = null,
    val backgroundColor: Color? = null,
    val enable: Boolean = true,
)

@Composable
fun NativeCheckbox(
    modifier: Modifier,
    check: Boolean,
    properties: NativeCheckboxProperties,
    onCheckChange: ((Boolean) -> Unit)?
) {
    Checkbox(
        modifier = Modifier.then(modifier),
        enabled = properties.enable,
        checked = check,
        colors = CheckboxDefaults.colors(
            checkedColor = properties.backgroundColor ?: Color.Transparent.copy(alpha = 0.0f),
            uncheckedColor = properties.backgroundColor?.copy(alpha = 0.6f) ?: Color.Transparent.copy(alpha = 0.0f),
            checkmarkColor = properties.foregroundColor ?: Color.Transparent.copy(alpha = 0.0f),
            disabledColor = properties.backgroundColor?.copy(alpha = ContentAlpha.disabled) ?: Color.Transparent.copy(alpha = 0.0f),
            disabledIndeterminateColor = properties.foregroundColor?.copy(alpha = ContentAlpha.disabled) ?: Color.Transparent.copy(alpha = 0.0f),
        ),
        onCheckedChange = onCheckChange
    )
}
