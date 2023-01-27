package xyz.neziw.calc4j

import java.awt.EventQueue
import java.awt.Font
import java.awt.GridLayout
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JTextField
import javax.swing.SwingConstants

class Calc4j : JFrame() {

    private val display = JTextField()
    private var operator = ""
    private var firstNumber = ""
    private var secondNumber = ""

    init {
        title = "Java Calculator"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(400, 500)
        setLocationRelativeTo(null)
        isResizable = false
        layout = GridLayout(4, 4, 5, 5)
        display.isEditable = false
        display.horizontalAlignment = SwingConstants.RIGHT
        add(display)
        val buttons = arrayOf("7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "C", "0", "=", "/")
        for ((i, button) in buttons.withIndex()) {
            val button = JButton(buttons[i])
            button.font = Font(Font.SANS_SERIF, Font.BOLD, 14)
            button.addActionListener { e: ActionEvent ->
                val input = (e.source as JButton).text
                when {
                    input == "C" -> {
                        operator = ""
                        firstNumber = ""
                        secondNumber = ""
                        display.text = ""
                    }
                    "+-/*=".contains(input) -> handleOperator(input)
                    else -> {
                        if (operator.isEmpty()) {
                            firstNumber += input
                            display.text = firstNumber
                        } else {
                            secondNumber += input
                            display.text = secondNumber
                        }
                    }
                }
            }
            add(button)
        }
    }

    private fun handleOperator(input: String) {
        when (input) {
            "+" -> operator = "+"
            "-" -> operator = "-"
            "*" -> operator = "*"
            "/" -> operator = "/"
            "=" -> {
                val result = when (operator) {
                    "+" -> firstNumber.toDouble() + secondNumber.toDouble()
                    "-" -> firstNumber.toDouble() - secondNumber.toDouble()
                    "*" -> firstNumber.toDouble() * secondNumber.toDouble()
                    "/" -> firstNumber.toDouble() / secondNumber.toDouble()
                    else -> 0.0
                }
                display.text = result.toString()
                operator = ""
                firstNumber = ""
                secondNumber = ""
            }
        }
    }
}

fun main() {
    EventQueue.invokeLater { Calc4j().isVisible = true }
}