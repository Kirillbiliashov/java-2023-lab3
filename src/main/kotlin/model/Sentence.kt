package model

val CONSONANT_LETTERS = listOf("B", "C", "D", "F", "G", "H", "J", "K", "L", "M",
    "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z")


/**
 * Class representing text sentence
 * @property parts list of sentence parts that create sentence itself
 * @author Kirill Biliashov
 */

data class Sentence(val parts: List<SentencePart>) {

    /**
     * Returns a new list of sentence parts after filtering out words with a specified length
     * that start with consonant letter.
     *
     * @param wordsLength The length of words to be filtered out.
     * @return A new list of sentence parts with the words that are not of wordsLength and
     * don't begin with consonant letter
     */
    internal fun filteredParts(wordsLength: Int): List<SentencePart> {
        return parts.toList().filter { part ->
            !(part is SentencePart.Word &&
                    part.letters.size == wordsLength &&
                    CONSONANT_LETTERS.contains(part.letters.first().value))
        }
    }

    override fun toString(): String {
        return parts.joinToString("") { it.toString() }
    }

}
