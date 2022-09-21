package com.example.healthwiser.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.healthwiser.domain.model.Disease

@Dao
interface DiseaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(disease: Disease): Long

    @Query("SELECT * FROM diseases")
    fun getAllDiseases(): LiveData<List<Disease>>

    @Delete
    suspend fun deleteDisease(disease: Disease)

}