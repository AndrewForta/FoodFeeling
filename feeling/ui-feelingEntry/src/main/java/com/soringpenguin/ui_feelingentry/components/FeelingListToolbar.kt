package com.soringpenguin.ui_feelingentry.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
    fun FeelingListToolbar(
    feelingName: String,
    onFeelingNameChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    onAddFeeling: (String) -> Unit,
    onSubmitFeelingEntry: () -> Unit,
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 12.dp,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val localFocusManager = LocalFocusManager.current
                val focusRequester = FocusRequester()
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(.85f)
                        .padding(8.dp)
                        .focusRequester(focusRequester)
                    ,
                    value = feelingName,
                    onValueChange = {
                        onFeelingNameChanged(it)
                        onExecuteSearch()
                    },
                    label = { Text(text = "Filter feelings") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                            keyboardController?.hide()
                            localFocusManager.clearFocus()
                        },
                    ),
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            if (feelingName.isEmpty())
                                onSubmitFeelingEntry()
                            else
                                onAddFeeling(feelingName)
                        }
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                        ,
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Filter Icon"
                    )
                }
            }
        }
    }