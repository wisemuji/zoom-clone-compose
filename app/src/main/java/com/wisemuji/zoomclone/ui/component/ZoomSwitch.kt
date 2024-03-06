package com.wisemuji.zoomclone.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisemuji.zoomclone.ui.theme.Gray60
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@Composable
fun ZoomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.onTertiary,
            checkedTrackColor = MaterialTheme.colorScheme.tertiary,
            uncheckedThumbColor = MaterialTheme.colorScheme.outlineVariant,
            uncheckedBorderColor = MaterialTheme.colorScheme.outlineVariant,
        ),
        modifier = modifier,
    )
}

@Composable
fun ZoomSwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onCheckedChange(!checked) }
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 14.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )
            subtitle?.let {
                Text(
                    text = it,
                    fontSize = 13.sp,
                    color = Gray60,
                )
            }
        }
        ZoomSwitch(checked, onCheckedChange = onCheckedChange)
    }
}

class CheckedPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}

@Preview
@Composable
private fun ZoomSwitchPreview(
    @PreviewParameter(CheckedPreviewParameterProvider::class) checked: Boolean,
) {
    ZoomCloneComposeTheme {
        ZoomSwitch(checked = checked, onCheckedChange = {})
    }
}

@Preview
@Composable
private fun ZoomSwitchRowPreview(
    @PreviewParameter(CheckedPreviewParameterProvider::class) checked: Boolean,
) {
    ZoomCloneComposeTheme {
        ZoomSwitchRow(
            title = "Video On",
            checked = checked,
            onCheckedChange = {},
        )
    }
}
