# Stage 1/5: Chessboard
## Description
Chess has many variations. One of them is Pawns-only chess. It has only pawns. Not even a king. In this project, you create a two-player program for this chess variant.

In this stage, you need to print a chessboard made of characters as seen in the following picture:

![Chessboard scheme](chessboard.png)

The white pawns are denoted with the capital `W`, the black pawns are the capital `B` characters.

A unique pair of coordinates (a letter and a number) identifies each square of the chessboard. From White's left to right, the squares in the vertical lines are called files. They are labeled from `a` to `h`. The horizontal lines, known as <b>ranks</b>, are numbered from `1` to `8`. These are legit coordinate pairs: `a2`, `c6`, `d8`, `h5`, and so on. This corresponds to the <a href="https://en.wikipedia.org/wiki/Algebraic_notation_(chess)">chess algebraic notation</a> for recording moves in chess.

The Pawns-only chess has very simple rules. The pawns can make any <a href="https://en.wikipedia.org/wiki/Pawn_(chess)">standard pawn moves</a>, except promotion. A player wins the game when one of the pawns reaches the opponent's last row, or if all opponent pawns are captured. A draw occurs when one of the players is unable to make a valid move. This is called a stalemate.

Use a <b>monospaced</b> font for the program output, to print the chessboard correctly. It is a font with fixed-width characters. By default, IntelliJ IDEA uses the monospaced font.

## Objectives
Print the program name: `Pawns-Only Chess` and the text chessboard (see Example 1). Note the number of spaces.

## Examples
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

### Example 1:
```
 Pawns-Only Chess
  +---+---+---+---+---+---+---+---+
8 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
7 | B | B | B | B | B | B | B | B |
  +---+---+---+---+---+---+---+---+
6 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
5 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
4 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
3 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
2 | W | W | W | W | W | W | W | W |
  +---+---+---+---+---+---+---+---+
1 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
    a   b   c   d   e   f   g   h
```
