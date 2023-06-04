package com.test.reactivecomposeapp

import android.app.Application
import com.test.reactivecomposeapp.data.db.AppDatabase
import com.test.reactivecomposeapp.data.repository.QuoteRepositoryImpl
import com.test.reactivecomposeapp.domain.usecase.AddQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.DeleteQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.GetFavoriteQuotesUseCase
import com.test.reactivecomposeapp.domain.usecase.GetQuotesUseCase
import com.test.reactivecomposeapp.domain.usecase.GetRandomQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.ModifyQuoteUseCase

class MyApplication : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { QuoteRepositoryImpl(database.quoteDao()) }
    val getQuotesUseCase by lazy { GetQuotesUseCase(repository = repository) }
    val addQuoteUseCase by lazy { AddQuoteUseCase(repository = repository) }
    val modifyQuoteUseCase by lazy { ModifyQuoteUseCase(repository = repository) }
    val deleteQuoteUseCase by lazy { DeleteQuoteUseCase(repository = repository) }
    val getRandomQuoteUseCase by lazy { GetRandomQuoteUseCase(repository = repository) }
    val getFavoriteQuotesUseCase by lazy { GetFavoriteQuotesUseCase(repository = repository) }
}