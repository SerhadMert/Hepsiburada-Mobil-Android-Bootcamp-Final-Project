package com.example.test.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorites")
@Parcelize
data class Favorites(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "artworkUrl100") val artworkUrl100: String?,
    @ColumnInfo(name = "trackName") val trackName: String?,
    @ColumnInfo(name = "kind") val kind: String?,
    @ColumnInfo(name = "trackPrice") val trackPrice: Double?,
    @ColumnInfo(name = "price") val price: Double?,
    @ColumnInfo(name = "currency") val currency: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate : String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "longDescription") val longDescription: String?,
    @ColumnInfo(name = "shortDescription") val shortDescription: String?,
) : Parcelable
