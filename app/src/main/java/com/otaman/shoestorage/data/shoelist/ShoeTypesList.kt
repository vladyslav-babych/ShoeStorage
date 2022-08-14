package com.otaman.shoestorage.data.shoelist

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity @Parcelize
data class ShoeTypesList (
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "model_type")
    val model_type: String,

    @ColumnInfo(name = "model_gender")
    val model_gender: String
): Parcelable