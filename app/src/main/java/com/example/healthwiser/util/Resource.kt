package com.example.healthwiser.util

/**
 * this is a class recommended by google to wrap around our network responses.
 * it'll:
 *      a)-> be a generic class, type T
 *      b)-> help differentiate between successful and error responses
 *      c)-> help handling of the loading States
 *      d)-> data = body of response/actual response
 *      e)-> message = type of message,e.g successful or error
*/
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Loading<T>(data: T? = null, var refresh: Boolean): Resource<T>(data = data)
    class Error<T>(message:String,data: T? = null): Resource<T>(data,message)
}


/*
enum class Resource {
    SUCCESS,
    LOADING,
    ERROR
}*/
