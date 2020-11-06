package com.nick_sib.popularlibraries


/**
 * @param buttonsCount - count of working buttons*/
class CountersModel(private val buttonsCount: Int) {
    private val counters = MutableList(buttonsCount) { 0 }

    /**
     * @param buttonIndex - index of clicked button*/
    fun getCurrent(buttonIndex: Int) = counters[buttonIndex]

    fun next(buttonIndex: Int) = ++counters[buttonIndex]


    fun set(buttonIndex: Int, value: Int){
        counters[buttonIndex] = value
    }

}