package com.ltr.tackle.Screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopbarButton(
    imageId: Int,
    contentDescription: String,
    onClickHandler: () -> Unit,
    iconSize: Dp = 24.dp,
    backgroundColor: Color = Color.White
) {
    Button(
        modifier = Modifier
            .size(38.dp),
        onClick = { onClickHandler() },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier.size(iconSize)
        )
    }
}