package com.example.myapplication.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    text: String,
    hint: String = "",
    onChange: (String) -> Unit,
    onSearch: () -> Unit
) {

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }


    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onChange,
            maxLines = 1,
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, Color(0xFFF5F5F5)),
                    shape = MaterialTheme.shapes.large
                )
                .padding(vertical = 20.dp, horizontal = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused and text.trim().isEmpty()
                },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            )
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color(0xFF808080),
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 12.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}