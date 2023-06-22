package chess

class Engine {
    private val board = Board(mutableListOf())

    init {
        for (file in Files.values()) {
            board.pawns.add(WhitePawn(Coordinate(file, Ranks.TWO)))
            board.pawns.add(BlackPawn(Coordinate(file, Ranks.SEVEN)))
        }
    }

    fun printBoard() {
        val horizontalLine = "  +---+---+---+---+---+---+---+---+"
        val builder = StringBuilder(horizontalLine)
        for (rank in Ranks.values().reversed()) {
            builder.append("\n${rank.number}")
            for (file in Files.values()) {
                val pawnByCoordinate = getPawnByCoordinate(Coordinate(file, rank))
                builder.append(" | ")
                builder.append(pawnByCoordinate?.color?.char ?: ' ')
            }
            builder.append(" |\n")
            builder.append(horizontalLine)
        }
        builder.append("\n    a   b   c   d   e   f   g   h")
        print(builder)

    }

    private fun getPawnByCoordinate(coordinate: Coordinate): Pawn? {
        return board.pawns.find { isAtCoordinate(it, coordinate) }
    }

    private fun isAtCoordinate(pawn: Pawn, coordinate: Coordinate): Boolean {
        return pawn.coordinate.rank == coordinate.rank && pawn.coordinate.file == coordinate.file
    }

}