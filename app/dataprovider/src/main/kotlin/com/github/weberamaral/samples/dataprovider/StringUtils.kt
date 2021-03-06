/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.weberamaral.samples.dataprovider

import com.github.weberamaral.samples.core.LinkedList

class StringUtils {
    companion object {
        fun join(source: LinkedList): String {
            return JoinUtils.join(source)
        }

        fun split(source: String): LinkedList {
            return SplitUtils.split(source)
        }
    }
}
