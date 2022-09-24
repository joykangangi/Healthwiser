package com.example.healthwiser.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.healthwiser.data.remote.dto.Disease

@Dao
interface DiseaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(disease: Disease): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun userUpsert(disease: List<Disease>)


    @Query("SELECT * FROM diseases")
    fun getAllDiseases(): LiveData<List<Disease>>

    @Delete
    suspend fun deleteDisease(disease: Disease)

}