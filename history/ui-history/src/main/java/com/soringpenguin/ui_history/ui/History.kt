package com.soringpenguin.ui_history.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.ui_history.components.HistoryListItem

@Composable
fun HistoryLog(
    state: HistoryState,
    events: (HistoryListEvents) -> Unit
) {
// A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                elevation = 10.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                ) {
                    Column() {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Logs",
                            style = MaterialTheme.typography.h6,
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp)
                        ) {
                            items(state.history) { history ->
                                HistoryListItem(
                                    history,
                                )
                            }

                        }
                    }
                }
            }
            if (state.progressBarState is ProgressBarState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.BottomEnd),
                onClick = {
                    events(HistoryListEvents.GetHistory)
                }
            ) {
                Icon(Icons.Rounded.Refresh, contentDescription = "")
            }
        }
    }
}