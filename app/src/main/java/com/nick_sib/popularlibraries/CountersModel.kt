package com.nick_sib.popularlibraries


/**
 * @param size - count of working buttons*/
class CountersModel(size: Int) {
    private val counters = MutableList(size) { 0 }

    /**
     * @param index - index of clicked button*/
    fun getCurrent(index: Int) = counters[index]

    fun next(index: Int) = ++counters[index]


    fun set(index: Int, value: Int){
        counters[index] = value
    }

    companion object {
        private var INSTANCE: CountersModel? = null

        fun getInstance(size: Int) =
            INSTANCE ?: let {
                val result = CountersModel(size)
                INSTANCE = result
                result
            }
    }

}