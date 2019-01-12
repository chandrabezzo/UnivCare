package com.widyatama.coreandroid.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.coreandroid.constanta.AppConstans
import com.widyatama.coreandroid.data.model.Socmed
import io.reactivex.Flowable

@Dao
interface SocmedDao {
    @Query("SELECT * FROM " + AppConstans.SOCMED)
    fun getAll(): Flowable<List<Socmed>>

    @Query("SELECT * FROM " + AppConstans.SOCMED
            + " LIMIT 1")
    fun get(): Flowable<Socmed>

    @Query("SELECT * FROM " + AppConstans.SOCMED
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Socmed>

    @Query("SELECT * FROM " + AppConstans.SOCMED
            + " WHERE email=:email")
    fun get(email: String): Flowable<Socmed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Socmed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<Socmed>)

    @Query("DELETE FROM " + AppConstans.SOCMED)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.SOCMED
            + " WHERE email=:email")
    fun delete(email: String)

    @Query("SELECT COUNT(*) FROM " + AppConstans.SOCMED)
    fun count(): Int
}