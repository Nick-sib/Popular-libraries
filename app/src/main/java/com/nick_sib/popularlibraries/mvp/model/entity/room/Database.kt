package com.nick_sib.popularlibraries.mvp.model.entity.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.mvp.model.entity.room.dao.RepositoryDao
import com.nick_sib.popularlibraries.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        var instance: Database? = null
            get() = field ?: synchronized(Database::class.java) {
                field = Room.databaseBuilder(
                            App.instance,
                            Database::class.java, DB_NAME
                        ).build()
                field
            }
            private set
    }
}