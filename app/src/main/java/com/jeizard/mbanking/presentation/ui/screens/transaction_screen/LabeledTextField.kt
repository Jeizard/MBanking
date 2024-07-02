package com.jeizard.mbanking.presentation.ui.screens.transaction_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun LabeledTextField(
    label: String,
    placeholder: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int = 15
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= maxLength && isValidInput(newValue, keyboardType)) {
                    onValueChange(newValue)
                }
            },
            placeholder = { Text(text = placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            textStyle = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = enabled,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.tertiary,
                disabledTextColor = MaterialTheme.colorScheme.tertiary
            ),
            visualTransformation = visualTransformation,
            singleLine = true
        )
    }
}

private fun isValidInput(input: String, keyboardType: KeyboardType): Boolean {
    return if (keyboardType == KeyboardType.Number || keyboardType == KeyboardType.Decimal) {
        input.count { it == '.' } <= 1 && input.all { it.isDigit() || it == '.' }
    } else {
        true
    }
}
