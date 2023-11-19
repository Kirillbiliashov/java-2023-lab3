import model.Letter
import model.Sentence
import model.SentencePart
import model.Text


private val punctuationMarks = listOf(".", "!", "?")

private val punctuationMarksInSentence = listOf(",", ";", ":", " ", "-")


fun main() {
    print("Enter word length: ")
    var removeLength: Int
    while (true) {
        try {
            removeLength = readLine()!!.toInt()
            if (removeLength > 0) break
            else print("Enter valid word length (at least 1): ")
        } catch (e: Exception) {
            print("Incorrect format. Try again: ")
        }
    }
    print("Enter text: ")
    val text = readLine()!!

    val textClass = text.lowercase().replace(Regex("\\s+"), " ").parseToClass()

    println("result: ${textClass.removeConsonantWords(removeLength)}")
}

/**
 * Parses input text string to Text object
 * @return Parsed Text object
 */

private fun String.parseToClass(): Text {
    val sentences = this.splitInclusive(punctuationMarks).map(String::parseToSentence)

    return Text(sentences = if (sentences.isNotEmpty()) sentences else listOf(this.parseToSentence()))
}

/**
 * Splits string so that split strings remain in string list values (as opposed to regular split() method)
 * @param splitStrs The list of strings used as splitting criteria.
 * @return A list of substrings resulting from the split operation.
 */
private fun String.splitInclusive(splitStrs: List<String>): List<String> {
    val result = mutableListOf<String>()
    var splitStrIdx = -1
    var strOffset = 0
    this.forEachIndexed { idx, symbol ->
        if (splitStrs.any { it == symbol.toString() }) {
            splitStrIdx = idx
            result.add(this.substring(strOffset, splitStrIdx + 1))
            strOffset = splitStrIdx + 1
        }
    }
    return result
}

/**
 * Splits the given string into substrings based on the specified list of split strings.
 * Each occurrence of a split string is included as a separate element in the result list.
 * @param splitStrs The list of strings used as splitting criteria.
 * @return A list of substrings resulting from the split operation.
 */

private fun String.splitSeparately(splitStrs: List<String>): List<String> {
    val result = mutableListOf<String>()
    var strOffset = 0
    this.forEachIndexed { idx, symbol ->
            if (splitStrs.any { it == symbol.toString() }) {
                println("match idx: $idx")
                val prevSubstr = this.substring(strOffset, idx)
                if (prevSubstr != symbol.toString()) {
                    result.add(prevSubstr)
                }
                result.add(symbol.toString())
                strOffset = idx + 1
            }
    }
    if (strOffset < this.length) {
        val substr = substring(strOffset)
        println("substring: $substr")
        result.add(substr)
    }
    return result
}

/**
 * Parses string to a Sentence object
 * @return Parsed Sentence object
 */
private fun String.parseToSentence(): Sentence {
    val splitSentence = splitSeparately(punctuationMarks + punctuationMarksInSentence)
    return Sentence(
        parts = if (splitSentence.isNotEmpty()) splitSentence.map(String::parseToSentencePart)
        else listOf(this.parseToSentencePart())
    )
}

/**
 * Parses string to a SentencePart object
 * @return Parsed SentencePart object
 */
private fun String.parseToSentencePart(): SentencePart {
    val allPunctuationMarks = punctuationMarks + punctuationMarksInSentence
    if (allPunctuationMarks.contains(this)) return SentencePart.PunctuationMark(this)
    return parseToWord()
}

/**
 * Parses string to a SentencePart.Word object
 * @return Parsed SentencePart.Word object
 */
private fun String.parseToWord(): SentencePart.Word {
    val letters = this.toList().map { it.toString() }.map(::Letter)
    println("letters size: ${letters.size}")
    return SentencePart.Word(letters)
}
