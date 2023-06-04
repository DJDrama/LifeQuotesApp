package com.test.reactivecomposeapp.data.repository

import com.test.reactivecomposeapp.data.db.QuoteDao
import com.test.reactivecomposeapp.data.db.QuoteEntity
import com.test.reactivecomposeapp.data.db.asDomainList
import com.test.reactivecomposeapp.data.db.asDomainModel
import com.test.reactivecomposeapp.data.db.asEntityModel
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuoteRepositoryImpl
constructor(
    private val quoteDao: QuoteDao
) : QuoteRepository {
    override fun getAllQuotes(): Flow<List<Quote>> {
        return flow {
            quoteDao.getAllQuotes().collect {
                emit(value = it.asDomainList())
            }
        }
    }

    override suspend fun addQuote(quote: Quote) {
        quoteDao.insertQuote(quoteEntity = quote.asEntityModel())
    }

    override suspend fun modifyQuote(quote: Quote) {
        quoteDao.updateQuote(quoteEntity = quote.asEntityModel())
    }

    override suspend fun deleteQuote(id: Int) {
        quoteDao.deleteQuote(id = id)
    }

    override fun getRandomQuote(): Flow<Quote?> {
        return flow {
            quoteDao.getRandomQuote().collect {
                emit(value = it?.asDomainModel())
            }
        }
    }

    override fun getFavoriteQuotes(): Flow<List<Quote>> {
        return flow {
            quoteDao.getFavoriteQuotes().collect {
                emit(value = it.asDomainList())
            }
        }
    }
}