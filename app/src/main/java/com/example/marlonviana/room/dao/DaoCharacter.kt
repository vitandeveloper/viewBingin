package com.areuin.android.room.dao

import androidx.room.*
import com.example.marlonviana.model.DataCharacters

@Dao
interface DaoCharacter {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insterCharcter(data: DataCharacters)

    @Query("SELECT * FROM table_character")
    suspend fun getAllCharacters():List<DataCharacters>

    @Query("SELECT * FROM table_character WHERE id = :idC")
    suspend fun getCharacters(idC:Int):DataCharacters

    @Query("DELETE FROM table_character WHERE id = :idC")
    suspend fun deletCharacterr(idC:Int)

}