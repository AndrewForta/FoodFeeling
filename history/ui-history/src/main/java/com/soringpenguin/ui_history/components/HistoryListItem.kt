package com.soringpenguin.ui_history.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.soringpenguin.history_domain.HistoryEntry
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryListItem(
    history: HistoryEntry,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colors.surface)
            ,
        elevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                text = getHistoryString(history),
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Visible
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getHistoryString(history: HistoryEntry): String {
    val dateTime = LocalDateTime.parse(history.createdOn)

    val dateTimeStr = dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))

    return if(history.type.lowercase().contains("feel"))
        "${history.username} Felt ${history.name} on $dateTimeStr"
    else
        "${history.username} Ate ${history.name} on $dateTimeStr"
}