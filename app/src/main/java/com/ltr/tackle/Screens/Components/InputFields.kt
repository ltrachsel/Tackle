package com.ltr.tackle.Screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ltr.tackle.R

data class TextInputProps(
    val value: String,
    val label: String,
    val onValueChange: (String) -> Unit,
    val isMultiLine: Boolean = false,
    val lines: Int = 5
)

@Composable
fun TextInput(
    props: TextInputProps,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = props.label,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        val lines = if (props.isMultiLine) props.lines else 1

        OutlinedTextField(
            value = props.value,
            onValueChange = props.onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = !props.isMultiLine,
            shape = RoundedCornerShape(6.dp),
            minLines = lines,
            maxLines = lines,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,

                focusedBorderColor = Color.Black, // Change this color as needed
                unfocusedBorderColor = colorResource(R.color.light_gray) // Default border color
            )
        )
    }
}

@Composable
fun TextInputWithValidation(
    props: TextInputProps,
    validationErrorText: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextInput(props = props)


        if (!isValid) {
            Text(text = validationErrorText)
        }
    }
}

@Composable
fun DateInputWithValidation(
    value: String,
    label: String,
    onClick: () -> Unit,
    validationErrorText: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = label,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Box(
            modifier = Modifier
                .border(1.dp, colorResource(R.color.light_gray), RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(6.dp))
                .padding(12.dp)
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClick() }
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = value)
                Image(
                    painter = painterResource(R.drawable.icon_calendar),
                    contentDescription = "calendar",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        if (!isValid) {
            Text(text = validationErrorText)
        }
    }
}