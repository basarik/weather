package com.example.bkubuzcu.weather.network

/**
 * Created by bkubuzcu on 06/11/18.
 * this is OnResponseListener.
 * all of the retrofit service calls are completed here as success or fail
 */
interface OnResponseListener<in T> {
    /**
     * service response
     */
    fun onResponse(data: ApiResponse<T>)
}

data class ApiResponse<out T>(val success: T? = null, val error: Throwable? = null) {
    companion object {
        /**
         * response success
         */
        fun <T> success(data: T) = ApiResponse(success = data)

        /**
         * response fail
         */
        fun <T> error(err: Throwable) = ApiResponse<T>(error = err)
    }
}