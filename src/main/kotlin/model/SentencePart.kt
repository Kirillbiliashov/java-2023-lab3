package model

/**
 * Common representation of sentence part (either word or punctuation mark)
 * @author Kirill Biliashov
 */

sealed class SentencePart {

    /**
     * Class representing word in a sentence
     * @property letters list of letters that create word itself
     * @author Kirill Biliashov
     */

    data class Word(val letters: List<Letter>): SentencePart() {
        override fun toString(): String {
            return letters.joinToString("") { it.toString() }
        }
    }

    /**
     * Class representing punctuatioin mark in a sentence
     * @property value underlying string value of punctuation mark
     * @author Kirill Biliashov
     */

    data class PunctuationMark(val value: String): SentencePart() {
        override fun toString(): String {
            return value
        }
    }
}
