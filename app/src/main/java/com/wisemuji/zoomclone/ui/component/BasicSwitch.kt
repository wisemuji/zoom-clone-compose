package com.wisemuji.zoomclone.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisemuji.zoomclone.ui.theme.Black
import com.wisemuji.zoomclone.ui.theme.Gray20
import com.wisemuji.zoomclone.ui.theme.Gray30
import com.wisemuji.zoomclone.ui.theme.Gray50
import com.wisemuji.zoomclone.ui.theme.Gray60
import com.wisemuji.zoomclone.ui.theme.Green
import com.wisemuji.zoomclone.ui.theme.White

@Composable
fun BasicSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = White,
            checkedTrackColor = Green,
            uncheckedThumbColor = Gray30,
            uncheckedTrackColor = White,
            uncheckedBorderColor = Gray30,
        ),
        modifier = modifier,
    )
}

@Composable
fun BasicSwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(White)
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
                color = Black,
            )
            subtitle?.let {
                Text(
                    text = it,
                    fontSize = 13.sp,
                    color = Gray60,
                )
            }
        }
        BasicSwitch(checked, onCheckedChange = onCheckedChange)
    }
}

@Preview
@Composable
private fun BasicSwitchPreview() {
    BasicSwitch(checked = true, onCheckedChange = {})
}

@Preview
@Composable
private fun BasicSwitchPreviewUnchecked() {
    BasicSwitch(checked = false, onCheckedChange = {})
}
