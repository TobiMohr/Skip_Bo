package de.htwg.se.Skip_Bo

import com.google.inject.Guice
import de.htwg.se.Skip_Bo.aview.TUI
import de.htwg.se.Skip_Bo.aview.gui.SwingGui
import de.htwg.se.Skip_Bo.controller.controllerComponent.ControllerInterface
import scala.io.StdIn.readLine

object  Skip_Bo {
  val injector = Guice.createInjector(new Skip_BoModule)
  val controller = injector.getInstance(classOf[ControllerInterface])//new Controller(Game())
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