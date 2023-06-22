package chess

class Game {
    private val input = Input()
    private val boardHandler = BoardHandler()
    private val players = mutableListOf<Player>()

    fun run() {
        println("Pawns-Only Chess")
        println("First Player's name:")
        val firstPlayer = readln()
        players.add(PlayerWhite(firstPlayer))
        println("Second Player's name:")
        val secondPlayer = readln()
        players.add(PlayerBlack(secondPlayer))

        boardHandler.printBoard()

        var currentPlayerIndex = 0
        var exit = false
        while (!exit) {
            println("${players[currentPlayerIndex].name}'s turn:")
            try {
                val move = input.getMove()

                val pawnAtStartPosition =
                    boardHandler.getPawnByColorAndPosition(players[currentPlayerIndex].color, move.startPosition)
                        ?: throw RuntimeException("No ${players[currentPlayerIndex].color.name.lowercase()} pawn at ${move.startPosition.file.char}${move.startPosition.rank.char}")

                val pawnAtEndPosition =
                    boardHandler.getPawnByPosition(move.endPosition)

                if (pawnAtEndPosition != null) {
                    throw RuntimeException("Invalid Input")
                }

                if (isNotValidMove(players[currentPlayerIndex], move)) {
                    throw RuntimeException("Invalid Input")
                }

                pawnAtStartPosition.position = move.endPosition
                currentPlayerIndex = (currentPlayerIndex + 1) % 2
                boardHandler.printBoard()
            } catch (e: RuntimeException) {
                if (e.message == "exit") {
                    exit = true
                } else {
                    println(e.message)
                }
            }
        }
        println("Bye!")
    }

    private fun isNotValidMove(player: Player, move: Move): Boolean {
        return when(player.color) {
            Colors.WHITE -> isNotValidWhiteMove(move)
            Colors.BLACK -> isNotValidBlackMove(move)
        }
    }

    private fun isNotValidWhiteMove(move: Move): Boolean {
        if (move.startPosition.file != move.endPosition.file) return true
        val moveRanks = move.endPosition.rank.char - move.startPosition.rank.char
        if (moveRanks < 1 || moveRanks > 2) return true
        return moveRanks == 2 && move.startPosition.rank != Ranks.TWO
    }

    private fun isNotValidBlackMove(move: Move): Boolean {
        if (move.startPosition.file != move.endPosition.file) return true
        val moveRanks = move.startPosition.rank.char - move.endPosition.rank.char
        if (moveRanks < 1 || moveRanks > 2) return true
        return moveRanks == 2 && move.startPosition.rank != Ranks.SEVEN
    }

}