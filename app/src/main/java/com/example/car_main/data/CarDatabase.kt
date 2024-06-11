package com.example.car_main.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * src: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#6
 *Database class with a singleton Instance object.
 */
@Database(
    entities = [Car::class, Expense::class],
    version = 2,
    exportSchema = true
)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var Instance: CarDatabase? = null

        /**
         * provides proper way of migration (adding extra table from version 1 to 2)
         */
        val migration1to2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `car_expenses` (" +
                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`value` REAL NOT NULL, " +
                            "`kind` TEXT NOT NULL, " +
                            "`date` INTEGER NOT NULL, " +
                            "`car_id` INTEGER NOT NULL, " +
                            "FOREIGN KEY(`car_id`) REFERENCES `user_Cars`(`id`) ON DELETE CASCADE)"
                )
            }
        }
        fun getDatabase(context: Context): CarDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CarDatabase::class.java, "car_database")
                    .addMigrations(migration1to2)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}