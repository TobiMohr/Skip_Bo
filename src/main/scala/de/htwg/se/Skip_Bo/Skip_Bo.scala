package de.htwg.se.Skip_Bo

import de.htwg.se.Skip_Bo.aview.TUI
import de.htwg.se.Skip_Bo.aview.gui.SwingGui
import de.htwg.se.Skip_Bo.controller.{CardPlaced, Controller}
import de.htwg.se.Skip_Bo.model.Game

import scala.io.StdIn.readLine

object  Skip_Bo {
  val controller = new Controller(Game())
  val tui = new TUI(controller)
  val gui = new SwingGui(controller)
  //controller.publish(new CardPlaced)
  //controller.notifyObservers

  def main(args: Array[String]): Unit = {

    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}