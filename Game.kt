package chess

import kotlin.math.absoluteValue

class Game {
    private val input = Input()
    private val boardHandler = BoardHandler()
    private val players = mutableListOf<Player>()
    private val moves = mutableListOf<Move>()

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
            val currentPlayer = players[currentPlayerIndex]
            println("${currentPlayer.name}'s turn:")
            try {
                val move = input.getMove()

                val pawnAtStartPosition =
                    boardHandler.getPawnByColorAndPosition(currentPlayer.color, move.startPosition)
                        ?: throw RuntimeException("No ${currentPlayer.color.name.lowercase()} pawn at ${move.startPosition.file.char}${move.startPosition.rank.char}")

                val capturedPawn = getCapturedPawn(currentPlayer, move)

                if (capturedPawn != null) {
                    boardHandler.removePawn(capturedPawn)
                } else if (isNotValidMove(currentPlayer, move)) {
                    throw RuntimeException("Invalid Input")
                }

                if (boardHandler.getPawnByPosition(move.endPosition) != null) {
                    throw RuntimeException("Invalid Input")
                }

                pawnAtStartPosition.position = move.endPosition
                currentPlayerIndex = (currentPlayerIndex + 1) % 2
                boardHandler.printBoard()
                moves.add(move)

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
        return when (player.color) {
            Colors.WHITE -> isNotValidMoveWhite(move)
            Colors.BLACK -> isNotValidMoveBlack(move)
        }
    }

    private fun isNotValidMoveWhite(move: Move): Boolean {
        if (move.startPosition.file != move.endPosition.file) return true
        val moveRanks = move.endPosition.rank.char - move.startPosition.rank.char
        if (moveRanks < 1 || moveRanks > 2) return true
        return moveRanks == 2 && move.startPosition.rank != Ranks.TWO
    }

    private fun isNotValidMoveBlack(move: Move): Boolean {
        if (move.startPosition.file != move.endPosition.file) return true
        val moveRanks = move.startPosition.rank.char - move.endPosition.rank.char
        if (moveRanks < 1 || moveRanks > 2) return true
        return moveRanks == 2 && move.startPosition.rank != Ranks.SEVEN
    }

    private fun getCapturedPawn(player: Player, move: Move): Pawn? {
        return when (player.color) {
            Colors.WHITE -> getCaptureWhite(move)
            Colors.BLACK -> getCaptureBlack(move)
        }
    }

    private fun getCaptureWhite(move: Move): Pawn? {
        if (isCaptureWhite(move)) {
            return boardHandler.getPawnByPosition(move.endPosition)
        }

        if (isEnPassantWhite(move)) {
            return boardHandler.getPawnByPosition(Position(move.endPosition.file, Ranks.getRank(move.endPosition.rank.char - 1)))
        }
        return null
    }

    private fun getCaptureBlack(move: Move): Pawn? {
        if (isCaptureBlack(move)) {
            return boardHandler.getPawnByPosition(move.endPosition)
        }

        if (isEnPassantBlack(move)) {
            return boardHandler.getPawnByPosition(Position(move.endPosition.file, Ranks.getRank(move.endPosition.rank.char + 1)))
        }
        return null
    }

    private fun isCaptureWhite(move: Move): Boolean {
        return isCaptureByOpponentColor(Colors.BLACK, move)
    }

    private fun isCaptureBlack(move: Move): Boolean {
        return isCaptureByOpponentColor(Colors.WHITE, move)
    }

    private fun isCaptureByOpponentColor(opponentColor: Colors, move: Move): Boolean {
        if (hasInvalidRanksAndFiles(opponentColor, move)) return false

        val capturedPawn = boardHandler.getPawnByPosition(move.endPosition) ?: return false

        return capturedPawn.color == opponentColor
    }

    private fun hasInvalidRanksAndFiles(opponentColor: Colors, move: Move): Boolean {
        val moveRanks =
            (move.endPosition.rank.char - move.startPosition.rank.char) * if (opponentColor == Colors.BLACK) 1 else -1
        val moveFiles = (move.endPosition.file.char - move.startPosition.file.char).absoluteValue

        return moveRanks != 1 || moveFiles != 1
    }

    private fun isEnPassantWhite(move: Move): Boolean {
        if (move.endPosition.rank != Ranks.SIX) return false
        return isEnPassantByOpponentColor(Colors.BLACK, move)
    }

    private fun isEnPassantBlack(move: Move): Boolean {
        if (move.endPosition.rank != Ranks.THREE) return false
        return isEnPassantByOpponentColor(Colors.WHITE, move)
    }

    private fun isEnPassantByOpponentColor(opponentColor: Colors, move: Move): Boolean {
        val moveRanks =
            (move.endPosition.rank.char - move.startPosition.rank.char) * if (opponentColor == Colors.BLACK) 1 else -1
        val moveFiles = (move.endPosition.file.char - move.startPosition.file.char).absoluteValue

        if (moveRanks != 1 || moveFiles != 1) return false

        val capturedPawnPosition = Position(move.endPosition.file, Ranks.getRank(if (opponentColor == Colors.BLACK) move.endPosition.rank.char - 1 else move.endPosition.rank.char + 1))

        val capturedPawn = boardHandler.getPawnByPosition(capturedPawnPosition) ?: return false


        return capturedPawn.position.file == moves.last().endPosition.file && capturedPawn.position.rank == moves.last().endPosition.rank
    }

}
