package chess

class BoardHandler {
    private val board = Board(mutableListOf())

    init {
        for (file in Files.values()) {
            board.pawns.add(PawnWhite(Position(file, Ranks.TWO)))
            board.pawns.add(PawnBlack(Position(file, Ranks.SEVEN)))
        }
    }

    fun printBoard() {
        val horizontalLine = "  +---+---+---+---+---+---+---+---+"
        val builder = StringBuilder(horizontalLine)
        for (rank in Ranks.values().reversed()) {
            builder.append("\n${rank.char}")
            for (file in Files.values()) {
                val pawnByPosition = getPawnByPosition(Position(file, rank))
                builder.append(" | ")
                builder.append(pawnByPosition?.color?.char ?: ' ')
            }
            builder.append(" |\n")
            builder.append(horizontalLine)
        }
        builder.append("\n    a   b   c   d   e   f   g   h\n\n")
        print(builder)

    }

    fun getPawnByColorAndPosition(color: Colors, position: Position): Pawn? {
        return board.pawns.filter { it.color == color }.find { isAtCoordinate(it, position) }
    }

    fun getPawnByPosition(position: Position): Pawn? {
        return board.pawns.find { isAtCoordinate(it, position) }
    }

    fun removePawn(pawn: Pawn): Boolean {
        return board.pawns.remove(pawn)
    }

    private fun isAtCoordinate(pawn: Pawn, position: Position): Boolean {
        return pawn.position.rank == position.rank && pawn.position.file == position.file
    }

}