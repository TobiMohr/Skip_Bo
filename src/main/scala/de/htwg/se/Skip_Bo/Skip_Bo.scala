package de.htwg.se.Skip_Bo

import de.htwg.se.Skip_Bo.aview.TUI
import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model._

import scala.io.StdIn.readLine

object  Skip_Bo {
  val controller = new Controller(Stack(List(Card(Colour.red, 1), Card(Colour.green, 5))), Card(Colour.blue, 3), new Board)
  val tui = new TUI(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""


    do {
      input = readLine()
      val s = tui.processInputLine(input)
    } while (input != "q")
  }
}
