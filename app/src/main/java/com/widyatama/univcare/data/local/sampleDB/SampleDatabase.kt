package com.widyatama.coreandroid.data.local.sampleDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.widyatama.coreandroid.constanta.AppConstans
import com.widyatama.coreandroid.data.local.sampleDB.converter.AlamatConverter
import com.widyatama.coreandroid.data.local.sampleDB.dao.JabatanDao
import com.widyatama.coreandroid.data.local.sampleDB.dao.KaryawanDao
import com.widyatama.coreandroid.data.local.sampleDB.dao.SocmedDao
import com.widyatama.coreandroid.data.local.sampleDB.dao.UserDao
import com.widyatama.coreandroid.data.model.JabatanResponse
import com.widyatama.coreandroid.data.model.Karyawan
import com.widyatama.coreandroid.data.model.Socmed
import com.widyatama.coreandroid.data.model.UserResponse

/**
 * Created by bezzo on 11/01/18.
 * Add more entities = arrayOf(UserLokal::class, SampleBTable::class)
 * Add more converter must unique
 */
@Database(entities =
    [(UserResponse.User::class), (JabatanResponse.Jabatan::class), (Karyawan::class),
    (Socmed::class)], version = 1)
@TypeConverters(AlamatConverter::class)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun user() : UserDao
    abstract fun jabatan() : JabatanDao
    abstract fun karyawan() : KaryawanDao
    abstract fun socmed() : SocmedDao

    companion object {
        @Volatile private var INSTANCE: SampleDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }

        fun getInstance(context: Context): SampleDatabase {
            if (INSTANCE == null) {
                synchronized(SampleDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context,
                                SampleDatabase::class.java, AppConstans.DB_NAME)
                                .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun clearAllTables() {

    }
}