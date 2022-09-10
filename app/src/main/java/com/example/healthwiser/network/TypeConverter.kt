package com.example.healthwiser.network

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class TypeConverter {
    private val type: Type = Types.newParameterizedType(List::class.java, String::class.java)
    private val moshi: Moshi = Moshi.Builder().build()
    private val adapter: JsonAdapter<List<String>> = moshi.adapter(type)

    @TypeConverter
    fun factsToJson(factsList: List<String>): String = adapter.toJson(factsList)



    @TypeConverter
    fun jsonToFacts(jsonObj:String):List<String> = adapter.fromJson(jsonObj)!!
}