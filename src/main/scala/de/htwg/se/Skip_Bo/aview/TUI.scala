package de.htwg.se.Skip_Bo.aview

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Card, Colour, Stack}
import de.htwg.se.Skip_Bo.util.Observer


class TUI(controller: Controller) {
  while(true) {
    val scanner = new java.util.Scanner(System.in)
    val line = scanner.nextLine
    val l = line.split(" ")
    l(1) match {
        //start Game
      case "s" => controller.startGame()
      case "p1" => {
        val s = l(2).toInt
        controller.pushCard1A(s)
      }
      case "p2" => {
        val s = l(2)
        controller.pushCard2A(s)
      }
      case "p3" => {
        val s = l(2)
        controller.pushCard3A(s)
      }
      case "p4" => {
        val s = l(2)
        controller.pushCard4A(s)
      }
      case "a1" => {
        val i = l(2).toInt
        controller.ablegen1A(i)
      }
      case "a2" => {
        val i = l(2).toInt
        controller.ablegen2A(i)
      }
      case "a3" => {
        val i = l(2).toInt
        controller.ablegen3A(i)
      }
      case "a4" => {
        val i = l(2).toInt
        controller.ablegen4A(i)
      }
      case "end" => controller.Beenden
      case "exit" => sys.exit
        case "help" => println(controller.hilfe)
    }

    def update: Boolean = {println(controller.gameToString);true}




  }

  //override def update: Unit = println(controller.printCard)
}
