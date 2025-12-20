package com.michaldrabik.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaldrabik.data_local.database.model.RewatchHistory
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun RewatchSection(
    isRewatching: Boolean,
    onStartRewatch: () -> Unit,
    onCompleteRewatch: (Int?) -> Unit,
    rewatchHistory: List<RewatchHistory>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Rewatch Status",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            if (isRewatching) {
                Button(
                    onClick = { onCompleteRewatch(null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text("Finish Rewatch")
                }
            } else {
                Button(
                    onClick = onStartRewatch,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text("Start Rewatching")
                }
            }

            // Show rewatch history
            if (rewatchHistory.isNotEmpty()) {
                Text(
                    "Rewatched ${rewatchHistory.size} time(s)",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyColumn {
                    items(rewatchHistory) { entry ->
                        RewatchHistoryItem(entry)
                    }
                }
            }
        }
    }
}

@Composable
fun RewatchHistoryItem(history: RewatchHistory) {
    val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    val date = dateFormat.format(Date(history.startedAt))

    Text(
        text = "Rewatched on $date",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}