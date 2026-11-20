package ie.tudublin

import java.io.File

fun main(args: Array<String>) {

    if (args.isEmpty()) {
        println("Please provide a Sudoku file.")
        return
    }

    val file = File(args[0])

    if (!file.exists()) {
        println("Error: File not found.")
        return
    }

    val lines = file.readLines()

    if (lines.size != 9) {
        println("Error: Sudoku must contain exactly 9 rows.")
        return
    }

    val board = Array(9) { row ->
        val line = lines[row].filterNot { it.isWhitespace() }

        if (line.length != 9 || line.any { !it.isDigit() }) {
            println("Error: Each row must contain exactly 9 digits (0-9).")
            return
        }

        IntArray(9) { col -> line[col].digitToInt() }
    }

    println("Original Sudoku:")
    printBoard(board)

    val sudoku = Sudoku(board)

    println("\nSolving Sudoku...")

    if (sudoku.solve()) {
        println("\nSolved Sudoku:")
        printBoard(sudoku.getBoard())
    } else {
        println("\nNo solution found.")
    }

    println("\nIterations: ${sudoku.iterations}")
}

fun printBoard(board: Array<IntArray>) {
    for (row in board) {
        println(row.joinToString(" "))
    }
}