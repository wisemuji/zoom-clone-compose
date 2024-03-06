/*
 * Copyright 2024 Suhyeon(wisemuji) and Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wisemuji.zoomclone.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wisemuji.zoomclone.ui.theme.Gray20
import com.wisemuji.zoomclone.ui.theme.Gray50
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@Composable
fun ZoomTextField(
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

@Preview
@Composable
fun ZoomTextFieldPreview() {
    ZoomCloneComposeTheme {
        ZoomTextField(
            value = "",
            onValueChange = {},
            placeholderText = "Meeting ID",
        )
    }
}
