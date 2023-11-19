package model

/**
 * Class representing text
 * @property sentences list of sentences that create word itself
 * @author Kirill Biliashov
 */

data class Text(val sentences: List<Sentence>) {

    /**
     * Removes words with a specified length that start with consonant letter from each sentence in the text.
     *
     * @param wordsLength The length of words to be removed from each sentence.
     * @return A new Text instance with the specified consonant words removed.
     */
    fun removeConsonantWords(wordsLength: Int): Text {
        return copy(sentences = sentences.map { old -> Sentence(old.filteredParts(wordsLength)) })
    }


    override fun toString(): String {
        return sentences.joinToString("") { it.toString() }
    }
}
