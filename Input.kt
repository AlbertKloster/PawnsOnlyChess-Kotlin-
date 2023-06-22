package chess

class Input {
    fun getMove(): Move {
        val regex = Regex("([a-h][1-8]){2}")
        val input = readln()
        if (input == "exit") {
            throw RuntimeException("exit")
        }
        if (!input.matches(regex)) {
            throw RuntimeException("Invalid Input")
        }
        return Move(Position(Files.getFile(input[0]), Ranks.getRank(input[1])), Position(Files.getFile(input[2]), Ranks.getRank(input[3])))
    }
}