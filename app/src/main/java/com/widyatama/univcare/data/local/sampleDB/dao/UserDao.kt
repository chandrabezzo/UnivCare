package com.widyatama.univcare.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.univcare.constanta.AppConstans
import com.widyatama.univcare.data.model.UserResponse
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM " + AppConstans.USER)
    fun getAll(): Flowable<List<UserResponse.User>>

    @Query("SELECT * FROM " + AppConstans.USER
            + " LIMIT 1")
    fun get(): Flowable<UserResponse.User>

    @Query("SELECT * FROM " + AppConstans.USER
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<UserResponse.User>

    @Query("SELECT * FROM " + AppConstans.USER
            + " WHERE id=:id")
    fun get(id: Int): Flowable<UserResponse.User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : UserResponse.User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<UserResponse.User>)

    @Query("DELETE FROM " + AppConstans.USER)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.USER
            + " WHERE id=:id")
    fun delete(id: Int)

    @Query("SELECT COUNT(*) FROM " + AppConstans.USER)
    fun count(): Int
}