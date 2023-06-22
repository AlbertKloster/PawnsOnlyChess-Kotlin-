package chess

enum class Ranks(val char: Char) {
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8');

    companion object {
        fun getRank(char: Char): Ranks {
            for (rank in Ranks.values()) {
                if (rank.char == char) {
                    return rank
                }
            }
            throw RuntimeException("Wrong character $char")
        }
    }
}