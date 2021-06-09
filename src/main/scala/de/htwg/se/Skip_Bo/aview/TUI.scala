package de.htwg.se.Skip_Bo.aview

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{InvalidHandCard, InvalidMove}
import de.htwg.se.Skip_Bo.util.Observer


class TUI(controller: Controller) extends Observer{
  controller.add(this)
  def processInputLine(input: String): Unit = {
    val l:Array[String] = input.split(" ")
    l(0) match {
      //start Game
      case "s" => controller.startGame()
      //legt Handkarte auf Ablegestapel oder Hilfstapel von Spieler
      case "ph" => {
        val i = l(1).toInt //Welcher Hilfs- oder Ablagestapel (Index)
        val j = l(2).toInt //Welche Handkarte (Index)
        val n = controller.playerState.getPlayer //Welcher Spieler
        val helpst = l(3).toBoolean //true=Hilfsstapel, false=Ablagestapel
          controller.pushCardHand(i, j, n, helpst)
      }
      //legt Karte vom Spielerstapel auf Ablegestapel
      case "ps" => {
        val i = l(1).toInt //Welcher Ablagestapel (Index)
        val n = controller.playerState.getPlayer //Welcher Spieler
          controller.pushCardPlayer(i, n)
      }
      //legt Karte vom Hilfsstapel auf Ablegestapel
      case "philfe" => {
        val i = l(1).toInt //Welcher Hilfestapel (Index)
        val j = l(2).toInt //Welcher Ablagestapel (Index)
        val n = controller.playerState.getPlayer //Welcher Spieler
          controller.pushCardHelp(i, j, n)
      }

      case "u" => controller.undo
      case "r" => controller.redo
      case "help" => println(controller.hilfe)
      case "q"=>
    }




  }

 override def update: Unit = println(controller.gameToString(controller.playerState.getPlayer))
  override def error(throwable: Throwable): Unit = throwable match{
    case InvalidHandCard(i) => println("Falscher Index: " + i)
    case InvalidMove => println("Dieser Zug geht nicht!")
  }
}
