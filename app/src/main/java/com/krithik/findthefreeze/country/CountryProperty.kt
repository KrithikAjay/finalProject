package com.krithik.findthefreeze.country

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "country_table")

data class CountryProperty(
  @PrimaryKey
  @ColumnInfo(name = "name")
    val name: String,
  @ColumnInfo(name= "capital")
    val capital: String,
  @ColumnInfo(name = "region")
    val region: String,
  @ColumnInfo(name = "population")
   val population : Long,
  @ColumnInfo(name = "flag")
   val flag : String

): Parcelable