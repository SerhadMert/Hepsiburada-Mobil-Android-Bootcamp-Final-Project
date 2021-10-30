package com.example.test.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorites")
@Parcelize
data class Favorites(
    @PrimaryKey
    @ColumnInfo(name = "trackId") val trackId : Int?,
    @ColumnInfo(name = "artworkUrl100") val artworkUrl100: String? = null,
    @ColumnInfo(name = "trackName") val trackName: String?= null,
    @ColumnInfo(name = "kind") val kind: String?= null,
    @ColumnInfo(name = "trackPrice") val trackPrice: Double?= null,
    @ColumnInfo(name = "price") val price: Double?= null,
    @ColumnInfo(name = "currency") val currency: String?= null,
    @ColumnInfo(name = "releaseDate") val releaseDate : String?= null,
    @ColumnInfo(name = "description") val description: String?= null,
    @ColumnInfo(name = "longDescription") val longDescription: String?= null,
    @ColumnInfo(name = "shortDescription") val shortDescription: String?= null
) : Parcelable
