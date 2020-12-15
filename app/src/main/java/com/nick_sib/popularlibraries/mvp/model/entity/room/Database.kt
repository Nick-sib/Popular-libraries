package com.nick_sib.popularlibraries.mvp.model.entity.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.mvp.model.entity.room.dao.RepositoryDao
import com.nick_sib.popularlibraries.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 2
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"

//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(db: SupportSQLiteDatabase) {
//                val tempTableName = "RoomGithubUserNEW"
//                val correctTableName = "RoomGithubUser"
////                db.execSQL("BEGIN TRANSACTION;")
//                // 1. Create new table
//                db.execSQL("CREATE TABLE $tempTableName (id INTEGER NOT NULL UNIQUE, login TEXT NOT NULL, avatarUrl TEXT NOT NULL, avatarPath TEXT NOT NULL);")
////                // 2. Copy the data
//                db.execSQL("INSERT INTO $tempTableName (id, login, avatarUrl, avatarPath) SELECT id, login, avatarUrl, reposUrl FROM $correctTableName;")
////                // 3. Remove the old table
//                db.execSQL("DROP TABLE $correctTableName;")
////                // 4. Change the table name to the correct one
//                db.execSQL("ALTER TABLE $tempTableName RENAME TO $correctTableName;")
////                db.execSQL("COMMIT;")
//            }
//        }


    }
}