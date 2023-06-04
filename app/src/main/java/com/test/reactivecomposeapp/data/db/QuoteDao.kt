package com.test.reactivecomposeapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.test.reactivecomposeapp.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao  {
    @Query("SELECT * FROM quote")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Insert
    suspend fun insertQuote(quoteEntity: QuoteEntity)

    @Update
    suspend fun updateQuote(quoteEntity: QuoteEntity)

    @Query("DELETE FROM quote WHERE id = :id")
    suspend fun deleteQuote(id: Int)

    @Query("SELECT * FROM quote ORDER BY RANDOM() LIMIT 1")
    fun getRandomQuote(): Flow<QuoteEntity?>

    @Query("SELECT * FROM quote WHERE favorite = 1")
    fun getFavoriteQuotes(): Flow<List<QuoteEntity>>

}