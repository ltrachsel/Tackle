package com.ltr.tackle.Screens.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ltr.tackle.R

@Composable
fun Topbar(
    titleId: Int,
    showBackButton: Boolean = false,
    backButtonClickHandler: (() -> Unit)? = null,
    content: @Composable() (() -> Unit)? = null
){
    Row (
        modifier = Modifier
            .padding(top = 10.dp, bottom = 25.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackButton) {
            Box(
                modifier = Modifier
                    .padding(end = 15.dp)
            ) {
                TopbarButton(
                    imageId = R.drawable.arrow_small_left,
                    contentDescription = "back",
                    onClickHandler = backButtonClickHandler?: {},
                    backgroundColor = colorResource(R.color.light_gray)
                )
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = stringResource(titleId),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding()
            )

            content?.invoke()
        }
    }
}