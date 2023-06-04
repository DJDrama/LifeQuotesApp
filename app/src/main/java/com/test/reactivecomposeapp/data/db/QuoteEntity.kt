package com.test.reactivecomposeapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.reactivecomposeapp.domain.model.Quote
import java.time.format.DateTimeFormatter

@Entity(tableName = "quote")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "quote") val quote: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
)

fun QuoteEntity.asDomainModel(): Quote {
    return Quote(
        id = this.id ?: 1,
        quote = this.quote,
        author = this.author,
        description = this.description,
        date = this.date,
        favorite = this.favorite
    )
}

fun List<QuoteEntity>.asDomainList(): List<Quote> = map {
    it.asDomainModel()
}

fun Quote.asEntityModel(): QuoteEntity {
    return QuoteEntity(
        id = this.id,
        quote = this.quote,
        author = this.author,
        description = this.description,
        date = this.date,
        favorite = this.favorite
    )
}