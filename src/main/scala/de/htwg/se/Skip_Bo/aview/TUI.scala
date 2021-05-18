package de.htwg.se.Skip_Bo.aview

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Card, Colour, Stack}
import de.htwg.se.Skip_Bo.util.Observer



class TUI(controller: Controller) extends Observer{


  controller.add(this)
  val stack = List(Card(Colour.red, 1), Card(Colour.green, 5))
  def processInputLine(input: String): Unit = {


    input match {
      case "d" => controller.makeStack(stack)
      case "c" => controller.makeCard(Colour.green, 2)
      case "p1" => controller.p1()
      case "p2" => controller.p2()
      case "p3" => controller.p3()
      case "p4" => controller.p4()
      case "m1" => controller.m1()
      case "m2" => controller.m2()
      case "m3" => controller.m3()
      case "m4" => controller.m4()
      case "end" => controller.Beenden
      case "exit" => sys.exit
      case _  => println("Wrong input!")
    }
  }







  override def update: Unit = println(controller.boardtoString)
}
