package com.example.healthwiser.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.healthwiser.data.remote.dto.Disease
import com.example.healthwiser.data.remote.TypeConverter


@Database(entities = [Disease::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class DiseaseDatabase: RoomDatabase() {

    abstract fun getDiseaseDao(): DiseaseDao

    companion object {
        @Volatile
        private var instance: DiseaseDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DiseaseDatabase::class.java,
                "disease_db.db"
            ).build()
    }




}