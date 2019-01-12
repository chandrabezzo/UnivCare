package com.widyatama.univcare.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.univcare.constanta.AppConstans
import com.widyatama.univcare.data.model.Karyawan
import io.reactivex.Flowable

@Dao
interface KaryawanDao {
    @Query("SELECT * FROM " + AppConstans.KARYAWAN)
    fun getAll(): Flowable<List<Karyawan>>

    @Query("SELECT * FROM " + AppConstans.KARYAWAN
            + " LIMIT 1")
    fun get(): Flowable<Karyawan>

    @Query("SELECT * FROM " + AppConstans.KARYAWAN
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Karyawan>

    @Query("SELECT * FROM " + AppConstans.KARYAWAN
            + " WHERE id=:id")
    fun get(id: Int): Flowable<Karyawan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Karyawan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : List<Karyawan>)

    @Query("DELETE FROM " + AppConstans.KARYAWAN)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.KARYAWAN
            + " WHERE id=:id")
    fun delete(id: Int)

    @Query("SELECT COUNT(*) FROM " + AppConstans.KARYAWAN)
    fun count(): Int
}