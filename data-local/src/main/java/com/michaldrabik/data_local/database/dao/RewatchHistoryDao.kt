package com.michaldrabik.showly2.repository.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.michaldrabik.showly2.repository.local.database.model.RewatchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface RewatchHistoryDao {

    @Insert
    suspend fun insert(history: RewatchHistory)

    @Query("SELECT * FROM rewatch_history WHERE showTraktId = :showTraktId ORDER BY startedAt DESC")
    fun observeRewatchHistory(showTraktId: Long): Flow<List<RewatchHistory>>

    @Query("SELECT COUNT(*) FROM rewatch_history WHERE showTraktId = :showTraktId")
    suspend fun getRewatchCount(showTraktId: Long): Int

    @Query("UPDATE rewatch_history SET completedAt = :completedAt, rating = :rating WHERE id = :id")
    suspend fun updateRewatchCompletion(id: Long, completedAt: Long, rating: Int? = null)
}