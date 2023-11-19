package model

/**
 * Class representing single letter (a-z)
 * @property value underlying value of a letter
 * @author Kirill Biliashov
 */

data class Letter(
    val value: String
) {
    override fun toString(): String {
        return value
    }
}

