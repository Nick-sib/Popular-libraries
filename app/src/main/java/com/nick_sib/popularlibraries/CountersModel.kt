package com.nick_sib.popularlibraries

class CountersModel(size: Int) {
    private val counters = MutableList(size) { 0 }

    fun getCurrent(index: Int): Int {
        return counters[index]
    }

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

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