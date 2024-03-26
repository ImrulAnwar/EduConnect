package com.imrul.educonnect.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.imrul.educonnect.ui.theme.Maroon20
import com.imrul.educonnect.ui.theme.Maroon70

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegularTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        value = text,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Maroon70,
            focusedLabelColor = Maroon70,
            focusedTextColor = Maroon70,
            cursorColor = Maroon70,
            unfocusedBorderColor = Maroon20,
            unfocusedLabelColor = Maroon20,
            unfocusedTextColor = Maroon70,
        ),
    )
}