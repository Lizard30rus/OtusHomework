package com.example.otushomework.utils

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson

/** функция преобразования модели из Json */
fun <A> String.fromJson(type : Class<A>): A {
    return Gson().fromJson(this, type)
}
/** функция преобразования модель в Json */
fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}

inline fun <reified T : Parcelable> createNavParams(isNullableAllowed : Boolean = true): NavType<T> =
    object : NavType<T>(isNullableAllowed){
        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): T {
            return Gson().fromJson(value, T::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }
    }