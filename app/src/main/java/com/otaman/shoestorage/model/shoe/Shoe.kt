package com.otaman.shoestorage.model.shoe

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity @Parcelize
data class Shoe(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "model_type")
    val model_type: String,

    @ColumnInfo(name = "model_name")
    val model_name: String,

    @ColumnInfo(name = "model_color")
    val model_color: String,

    @ColumnInfo(name = "model_size")
    val model_size: Int,

    @ColumnInfo(name = "model_material")
    val model_material: String,

    @ColumnInfo(name = "model_lining")
    val model_lining: String,

    @ColumnInfo(name = "model_barcode")
    val model_barcode: Long
): Parcelable