package com.mjollror.noteapp.featureNote.presentation.notes.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = MaterialTheme.colorScheme.onBackground,
            disabledSelectedColor = MaterialTheme.colorScheme.onBackground,
            disabledUnselectedColor = MaterialTheme.colorScheme.onBackground,
        )
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = text, style = MaterialTheme.typography.bodyMedium, onTextLayout = {})
}
