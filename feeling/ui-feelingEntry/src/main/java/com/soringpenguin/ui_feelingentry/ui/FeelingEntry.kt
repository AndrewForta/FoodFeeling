package com.soringpenguin.ui_feelingentry.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.ui_feelingentry.components.FeelingListItem
import com.soringpenguin.ui_feelingentry.components.FeelingListToolbar

@Composable
fun FeelingEntry(
    state: FeelingEntryState,
    events: (FeelingListEvents) -> Unit,
) {
// A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
                elevation = 10.dp
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "Feelings",
                        style = MaterialTheme.typography.h5,
                    )
                    FeelingListToolbar(
                        feelingName = state.feelingName,
                        onFeelingNameChanged = { feelingName ->
                            events(FeelingListEvents.UpdateFeelingName(feelingName))
                        },
                        onExecuteSearch = {
                            events(FeelingListEvents.FilterFeelings)
                        },
                        onAddFeeling = {
                            events(FeelingListEvents.AddFeeling(it))
                        },
                        onSubmitFeelingEntry = {
                            events(FeelingListEvents.SubmitFeelingEntry(state.selectedFeelings))
                        })
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)) {
                        Column() {
                            Text(
                                modifier = Modifier.
                                padding(8.dp),
                                text = "All Feelings",
                                style = MaterialTheme.typography.h6,
                            )
                            LazyColumn(modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(start = 8.dp)
                            ) {
                                items(state.filteredFeelings) { feeling ->
                                    FeelingListItem(
                                        feeling,
                                        onSelectFeeling = {
                                            events(FeelingListEvents.SelectFeeling(it))
                                        }
                                    )
                                }
                            }
                        }
                        Column() {
                            Text(
                                modifier = Modifier.
                                padding(8.dp),
                                text = "Selected Feelings",
                                style = MaterialTheme.typography.h6,
                            )
                            LazyColumn(Modifier.padding(horizontal = 8.dp)) {
                                items(state.selectedFeelings) { feeling ->
                                    FeelingListItem(
                                        feeling,
                                        onSelectFeeling = {
                                            events(FeelingListEvents.DeselectFeeling(it))
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if(state.progressBarState is ProgressBarState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}