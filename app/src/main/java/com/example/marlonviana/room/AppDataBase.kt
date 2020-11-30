package com.areuin.android.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.areuin.android.room.dao.DaoCharacter
import com.example.marlonviana.AppMaster
import com.example.marlonviana.model.DataCharacters
import com.example.marlonviana.room.ConvertersCharacter
import com.example.marlonviana.util.NAME_DB
import com.example.marlonviana.util.VERSION_DB

@Database(entities = [DataCharacters::class], version = VERSION_DB)
@TypeConverters(ConvertersCharacter::class)
abstract class AppDataBase: RoomDatabase() {

    companion object{
        private var INSTANCE:AppDataBase?= null

        fun getInstance():AppDataBase{
            if (INSTANCE == null)
            {
                INSTANCE= Room.databaseBuilder(
                        AppMaster.contextApp(),
                        AppDataBase::class.java,
                        NAME_DB
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }
    }
    abstract fun daoRoom():DaoCharacter
}