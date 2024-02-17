package com.wisemuji.zoomclone.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@Composable
fun FullSizeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(14.dp),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun FullSizeButtonPreview() {
    ZoomCloneComposeTheme {
        FullSizeButton(text = "Start a Meeting", onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun FullSizeButtonPreviewDisabled() {
    ZoomCloneComposeTheme {
        FullSizeButton(text = "Start a Meeting", onClick = {}, enabled = false)
    }
}
