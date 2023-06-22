package chess

enum class Files(val char: Char) {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    companion object {
        fun getFile(char: Char): Files {
            for (file in Files.values()) {
                if (file.char == char) {
                    return file
                }
            }
            throw RuntimeException("Wrong character $char")
        }
    }

}