package com.widyatama.univcare.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.univcare.data.model.UniversityResponse
import io.reactivex.Flowable

@Dao
interface UniversityDao {
    @Query("SELECT * FROM University")
    fun getAll(): Flowable<List<UniversityResponse.University>>

    @Query("SELECT * FROM University LIMIT 1")
    fun get(): Flowable<UniversityResponse.University>

    @Query("SELECT * FROM University LIMIT :limit")
    fun getLimit(limit : Int): Flowable<UniversityResponse.University>

    @Query("SELECT * FROM University WHERE name=:name")
    fun get(name: String): Flowable<UniversityResponse.University>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : UniversityResponse.University)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : List<UniversityResponse.University>)

    @Query("DELETE FROM University")
    fun deleteAll()

    @Query("DELETE FROM University WHERE name=:name")
    fun delete(name: String)

    @Query("SELECT COUNT(*) FROM University")
    fun count(): Int
}