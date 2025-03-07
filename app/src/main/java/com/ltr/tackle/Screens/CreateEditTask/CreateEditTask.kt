package com.ltr.tackle.Screens.CreateEditTask

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ltr.tackle.R
import com.ltr.tackle.Screens.Components.Topbar
import java.util.Calendar

@Composable
fun CreateEditScreen(
    navController: NavHostController,
    viewModel: CreateEditTaskViewModel = hiltViewModel()
) {
    val title by viewModel.title.collectAsState()
    val isTitleValid by viewModel.isTitleValid.collectAsState()

    val description by viewModel.description.collectAsState()

    val date by viewModel.date.collectAsState()
    val isDateValid by viewModel.isDateValid.collectAsState()



    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // State to control DatePicker visibility
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                viewModel.setDate(selectedDate)
                showDatePicker = false  // Close the dialog
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnCancelListener { showDatePicker = false } // Reset when canceled
        }.show()
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(20),
                    onClick = {
                        val taskCreated = viewModel.createTask()

                        if (taskCreated) {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("task_created", true)

                            navController.popBackStack()
                        }
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        style = MaterialTheme.typography.labelMedium,
                        text = stringResource(R.string.screen_create_task_create),
                        color = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Topbar(
                    titleId = R.string.top_bar_create_task,
                    showBackButton = true,
                    backButtonClickHandler = { navController.popBackStack() }
                )


                Spacer(modifier = Modifier.height(16.dp))

                TextFieldWithLabel(
                    label = stringResource(R.string.screen_create_task_title),
                    value = title,
                    isValid = isTitleValid,
                    validationLabel = "Titel darf nicht leer sein!",
                    onValueChangeHandler = {
                        viewModel.setTitle(it)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextFieldWithLabel(
                    label = stringResource(R.string.screen_create_task_date),
                    value = date,
                    isValid = isDateValid,
                    validationLabel = "Datum darf nicht leer sein!",
                    onValueChangeHandler = {
                        viewModel.setDate(it)
                    },
                    modifier = Modifier.clickable { showDatePicker = true },
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextFieldWithLabel(
                    label = stringResource(R.string.screen_create_task_description),
                    value = description,
                    onValueChangeHandler = {
                        viewModel.setDescription(it)
                    },
                    singleLine = false,
                    maxLines = 5,
                    textFieldModifier = Modifier.height(150.dp)
                )
            }
        }

    }
}

@Composable
fun TextFieldWithLabel(
    value: String,
    onValueChangeHandler: (String) -> Unit,
    label: String,
    validationLabel: String = "",
    @SuppressLint("ModifierParameter") textFieldModifier: Modifier = Modifier,
    isValid: Boolean? = null,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = label,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChangeHandler,
            modifier = textFieldModifier
                .fillMaxWidth(),
            singleLine = singleLine,
            maxLines = maxLines,
            shape = RoundedCornerShape(5), // Rounded corners
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,

                focusedBorderColor = Color.Black, // Change this color as needed
                unfocusedBorderColor = colorResource(R.color.light_gray) // Default border color
            )
        )

        if (isValid != null) {
            if (!isValid) {
                Text(text = validationLabel)
            }
        }
    }
}