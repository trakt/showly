package com.michaldrabik.showly2.repository.local.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "rewatch_history",
    foreignKeys = [
        ForeignKey(
            entity = Show::class,
            parentColumns = ["traktId"],
            childColumns = ["showTraktId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RewatchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val showTraktId: Long,
    val startedAt: Long,
    val completedAt: Long? = null,
    val rating: Int? = null
)