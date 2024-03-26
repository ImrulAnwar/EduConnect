package com.imrul.educonnect.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.imrul.educonnect.R
import com.imrul.educonnect.ui.theme.Maroon20
import com.imrul.educonnect.ui.theme.Maroon70

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    passwordVisibility: Boolean,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    label: String
) {
    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.pw_eye_visible)
    else
        painterResource(id = R.drawable.pw_eye_invisible)

    OutlinedTextField(
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        value = password,
        onValueChange = { onPasswordChange(it) },
        trailingIcon = {
            IconButton(onClick = { onPasswordVisibilityChange(!passwordVisibility) }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon",
                    modifier = Modifier.padding(8.dp),
                    tint = Maroon20
                )
            }
        },
        label = { Text(label) },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Maroon70,
            focusedLabelColor = Maroon70,
            focusedTextColor = Maroon70,
            cursorColor = Maroon70,
            unfocusedBorderColor = Maroon20,
            unfocusedLabelColor = Maroon20,
            unfocusedTextColor = Maroon70,
        )
    )
}