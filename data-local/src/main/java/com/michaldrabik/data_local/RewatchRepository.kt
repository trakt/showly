package com.michaldrabik.data_local

import com.michaldrabik.data_local.database.dao.RewatchHistoryDao
import com.michaldrabik.data_local.database.dao.ShowsDao
import com.michaldrabik.data_local.database.model.RewatchHistory
import kotlinx.coroutines.flow.Flow

class RewatchRepository(
    private val showsDao: ShowsDao,
    private val rewatchHistoryDao: RewatchHistoryDao
) {

    suspend fun startRewatch(showTraktId: Long) {
        val now = System.currentTimeMillis()
        showsDao.updateRewatchStatus(showTraktId, isRewatching = true, startedAt = now)
    }

    suspend fun completeRewatch(showTraktId: Long, rating: Int? = null) {
        val now = System.currentTimeMillis()
        showsDao.updateRewatchStatus(showTraktId, isRewatching = false, startedAt = null)
        showsDao.incrementRewatchCount(showTraktId)

        // Create history entry
        val rewatchHistory = RewatchHistory(
            showTraktId = showTraktId,
            startedAt = now,
            completedAt = now,
            rating = rating
        )
        rewatchHistoryDao.insert(rewatchHistory)
    }

    fun observeRewatchingShows(): Flow<List<com.michaldrabik.data_local.database.model.Show>> {
        return showsDao.observeRewatchingShows()
    }

    fun observeRewatchHistory(showTraktId: Long): Flow<List<RewatchHistory>> {
        return rewatchHistoryDao.observeRewatchHistory(showTraktId)
    }
}