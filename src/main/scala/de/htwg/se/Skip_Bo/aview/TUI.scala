package de.htwg.se.Skip_Bo.aview

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Card, Colour, Stack}
import de.htwg.se.Skip_Bo.util.Observer


class TUI(controller: Controller) extends Observer{
  while(true) {
    val scanner = new java.util.Scanner(System.in)
    val line = scanner.nextLine
    line match {
      case "d" => controller.makeStack(List(Card(Colour.red,1),Card(Colour.green,5)))
      case "c" => controller.makeCard(Colour.green,2)
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
    }


    }




  override def update: Unit = println(controller.boardtoString)
}
