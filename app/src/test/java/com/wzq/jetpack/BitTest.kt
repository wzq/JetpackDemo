package com.wzq.jetpack

import androidx.collection.arrayMapOf
import org.junit.Test

/**
 * create by wzq on 2020/12/22
 *
 */
class BitTest {

    @Test
    fun binSearch() {
        val arr = arrayOf(1, 3, 2, 9, 6, 4)
        arr.sort()
        println(arr.asList())
        println("result is ${bSearch(arr, 4).inv()}")

        val map = arrayMapOf<String, String>()
    }


    /**
     * @return new index
     */
    fun bSearch(arr: Array<Int>, target: Int): Int {
        val size = arr.size
        var low = 0
        var high = size - 1

        while (low <= high) {
            val mid = (low + high) ushr 1
            val midVal = arr[mid]
            println(mid)

            if (midVal < target) {
                low = mid + 1
            } else if (midVal > target) {
                high = mid - 1
            } else {
                return mid
            }
        }
        return low.inv()
    }
}