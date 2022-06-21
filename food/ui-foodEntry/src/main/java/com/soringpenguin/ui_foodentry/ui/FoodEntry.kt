package com.soringpenguin.ui_foodentry.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soringpenguin.core.ProgressBarState
import com.soringpenguin.ui_foodentry.components.FoodListItem
import com.soringpenguin.ui_foodentry.components.FoodListToolbar

@Composable
fun FoodEntry(
    state: FoodEntryState,
    events: (FoodListEvents) -> Unit,
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
                        text = "Food",
                        style = MaterialTheme.typography.h5,
                    )
                    FoodListToolbar(
                        foodName = state.foodName,
                        onFoodNameChanged = { foodName ->
                            events(FoodListEvents.UpdateFoodName(foodName))
                        },
                        onExecuteSearch = {
                            events(FoodListEvents.FilterFood)
                        },
                        onAddFood = {
                            events(FoodListEvents.AddFood(it))
                        },
                        onSubmitFoodEntry = {
                            events(FoodListEvents.SubmitFoodEntry(state.selectedFood))
                        })
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)) {
                        Column() {
                            Text(
                                modifier = Modifier.
                                padding(8.dp),
                                text = "All Food",
                                style = MaterialTheme.typography.h6,
                            )
                            LazyColumn(modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(start = 8.dp)
                            ) {
                                items(state.filteredFood) { food ->
                                    FoodListItem(
                                        food,
                                        onSelectFood = {
                                            events(FoodListEvents.SelectFood(it))
                                        }
                                    )
                                }
                            }
                        }
                        Column() {
                            Text(
                                modifier = Modifier.
                                padding(8.dp),
                                text = "Selected Food",
                                style = MaterialTheme.typography.h6,
                            )
                            LazyColumn(Modifier.padding(horizontal = 8.dp)) {
                                items(state.selectedFood) { food ->
                                    FoodListItem(
                                        food,
                                        onSelectFood = {
                                            events(FoodListEvents.DeselectFood(it))
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