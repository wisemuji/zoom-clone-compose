package com.wisemuji.zoomclone.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.wisemuji.zoomclone.ui.theme.Gray20
import com.wisemuji.zoomclone.ui.theme.Gray50

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        colors = TextFieldDefaults.colors(
            focusedPlaceholderColor = Gray50,
            unfocusedPlaceholderColor = Gray50,
            focusedIndicatorColor = Gray20,
            unfocusedIndicatorColor = Gray20,
        ),
        modifier = modifier.fillMaxWidth(),
    )
}
