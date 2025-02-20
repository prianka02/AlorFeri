package com.test.alorferi.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GetMobileNoView(
    modifier: Modifier,
    mobileText: TextFieldValue, // Accept the text as a parameter
    onTextChanged: (TextFieldValue) -> Unit,
    hint: String
) {
    val focusManager =
        LocalFocusManager.current  // Request for inputText Focusing and Hiding as well as keyboard manager

    Box(
        modifier = modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart

    ){
        BasicTextField(
            value = mobileText, // Use the passed text value
            singleLine = true,
            onValueChange = { newMobileText ->
                if (newMobileText.text.length <= 40) {
                    onTextChanged(newMobileText) // Only update the text if it's within the limit
                }
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            ),            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),

            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        // Show hint if text field is empty
        if (mobileText.text.isEmpty()) {
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = hint,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}