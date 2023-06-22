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
                input.getMove()
                currentPlayerIndex = (currentPlayerIndex + 1) % 2
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

}