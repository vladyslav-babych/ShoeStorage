package com.otaman.shoestorage.di

import android.content.Context
import androidx.room.Room
import com.otaman.shoestorage.data.shoelist.ShoeTypesListRepository
import com.otaman.shoestorage.data.shoelist.room.ShoeTypesListDao
import com.otaman.shoestorage.data.shoelist.room.ShoeTypesListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoeTypesDatabaseModule {
    @Provides
    @Singleton
    fun provideShoeTypesDao(database: ShoeTypesListDatabase): ShoeTypesListDao = database.getAllShoeTypeListsDao()

    @Provides
    @Singleton
    fun provideShoeTypesDatabase(@ApplicationContext context: Context): ShoeTypesListDatabase {
        return Room.databaseBuilder(
            context,
            ShoeTypesListDatabase::class.java,
            "shoe_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoeTypesRepository(dao: ShoeTypesListDao, ioDispatcher: CoroutineDispatcher): ShoeTypesListRepository {
        return  ShoeTypesListRepository(dao, ioDispatcher)
    }
}

