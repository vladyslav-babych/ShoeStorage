package com.otaman.shoestorage.di

import android.content.Context
import androidx.room.Room
import com.otaman.shoestorage.data.shoe.ShoeRepository
import com.otaman.shoestorage.data.shoe.room.ShoeDao
import com.otaman.shoestorage.data.shoe.room.ShoeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoeDatabaseModule {

    @Provides
    @Singleton
    fun provideShoeDao(database: ShoeDatabase): ShoeDao = database.getShoeDao()

    @Provides
    @Singleton
    fun provideShoeDatabase(@ApplicationContext context: Context): ShoeDatabase {
        return Room.databaseBuilder(
            context,
            ShoeDatabase::class.java,
            "shoe_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoeRepository(dao: ShoeDao, ioDispatcher: CoroutineDispatcher): ShoeRepository = ShoeRepository(dao, ioDispatcher)
}