package me.dwidar.movieapp.src.core.network

interface OnCallResponse<T>
{
    fun onSuccess(result: T)
    fun onFail(errorMessage : String)
}