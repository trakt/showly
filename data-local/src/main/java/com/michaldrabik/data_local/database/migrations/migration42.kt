package com.michaldrabik.showly2.repository.local.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_41_42 = object : Migration(41, 42) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add columns to shows table
        database.execSQL(
            "ALTER TABLE shows ADD COLUMN isRewatching INTEGER NOT NULL DEFAULT 0"
        )
        database.execSQL(
            "ALTER TABLE shows ADD COLUMN rewtachStartedAt INTEGER"
        )
        database.execSQL(
            "ALTER TABLE shows ADD COLUMN rewatchCount INTEGER NOT NULL DEFAULT 0"
        )

        // Create rewatch_history table
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS rewatch_history (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                showTraktId INTEGER NOT NULL,
                startedAt INTEGER NOT NULL,
                completedAt INTEGER,
                rating INTEGER,
                FOREIGN KEY(showTraktId) REFERENCES shows(traktId) ON DELETE CASCADE
            )
            """.trimIndent()
        )

        // Create index for better query performance
        database.execSQL(
            "CREATE INDEX IF NOT EXISTS idx_rewatch_show ON rewatch_history(showTraktId)"
        )
    }
}