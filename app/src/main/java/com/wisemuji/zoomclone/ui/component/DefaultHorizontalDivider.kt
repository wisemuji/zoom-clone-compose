package com.wisemuji.zoomclone.ui.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wisemuji.zoomclone.ui.theme.Gray20

@Composable
fun DefaultHorizontalDivider(
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        color = Gray20,
        thickness = 1.dp,
        modifier = modifier,
    )
}
