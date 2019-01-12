package com.docotel.coreandroid.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.docotel.coreandroid.constanta.AppConstans
import com.docotel.coreandroid.data.model.JabatanResponse
import io.reactivex.Flowable

@Dao
interface JabatanDao {
    @Query("SELECT * FROM " + AppConstans.JABATAN)
    fun getAll(): Flowable<List<JabatanResponse.Jabatan>>

    @Query("SELECT * FROM " + AppConstans.JABATAN
            + " LIMIT 1")
    fun get(): Flowable<JabatanResponse.Jabatan>

    @Query("SELECT * FROM " + AppConstans.JABATAN
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<JabatanResponse.Jabatan>

    @Query("SELECT * FROM " + AppConstans.JABATAN
            + " WHERE id=:id")
    fun get(id: Int): Flowable<JabatanResponse.Jabatan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : JabatanResponse.Jabatan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : List<JabatanResponse.Jabatan>)

    @Query("DELETE FROM " + AppConstans.JABATAN)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.JABATAN
            + " WHERE id=:id")
    fun delete(id: Int)

    @Query("SELECT COUNT(*) FROM " + AppConstans.JABATAN)
    fun count(): Int
}