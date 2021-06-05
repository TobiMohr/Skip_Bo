package de.htwg.se.Skip_Bo.aview

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Card, Colour, InvalidHandCard, Stack}
import de.htwg.se.Skip_Bo.util.Observer


class TUI(controller: Controller) extends Observer{
  controller.add(this)
  def processInputLine(input: String): Unit = {
    val l:Array[String] = input.split(" ")
    l(0) match {
      //start Game
      case "s" => controller.startGame()
      case "p1" => {
        val i = l(1).toInt
        val j = l(2).toInt
        val n = l(3).toInt
        val helpst = l(4).toBoolean
          controller.pushCardHand1A(i, j, n, helpst)
      }
      case "p2" => {
        val s = l(1).toInt
        if(controller.checkCardHand(s)){
          controller.pushCardHand2A(s)
        } else {
          println("Diese Karte kannst du nicht ablegen.")
        }
      }

      case "p3" => {
        val s = l(1).toInt
        if(controller.checkCardHand(s)){
          controller.pushCardHand3A(s)
        } else {
          println("Diese Karte kannst du nicht ablegen.")
        }
      }
      case "p4" => {
        val s = l(1).toInt
        if(controller.checkCardHand(s)){
          controller.pushCardHand4A(s)
        } else {
          println("Diese Karte kannst du nicht ablegen.")
        }
      }
      case "a1" => {
        val i = l(1).toInt
        controller.ablegen1A(i)
      }
      case "a2" => {
        val i = l(1).toInt
        controller.ablegen2A(i)
      }
      case "a3" => {
        val i = l(1).toInt
        controller.ablegen3A(i)
      }
      case "a4" => {
        val i = l(1).toInt
        controller.ablegen4A(i)
      }
      case "ps1" => controller.pushCardStapel1A()
      case "ps2" => controller.pushCardStapel2A()
      case "ps3" => controller.pushCardStapel3A()
      case "ps4" => controller.pushCardStapel4A()
      case "ph1a1" => controller.pushCardH1A1A()
      case "ph1a2" => controller.pushCardH1A2A()
      case "ph1a3" => controller.pushCardH1A3A()
      case "ph1a4" => controller.pushCardH1A4A()
      case "ph2a1" => controller.pushCardH2A1A()
      case "ph2a2" => controller.pushCardH2A2A()
      case "ph2a3" => controller.pushCardH2A3A()
      case "ph2a4" => controller.pushCardH2A4A()
      case "ph3a1" => controller.pushCardH3A1A()
      case "ph3a2" => controller.pushCardH3A2A()
      case "ph3a3" => controller.pushCardH3A3A()
      case "ph3a4" => controller.pushCardH3A4A()
      case "ph4a1" => controller.pushCardH4A1A()
      case "ph4a2" => controller.pushCardH4A2A()
      case "ph4a3" => controller.pushCardH4A3A()
      case "ph4a4" => controller.pushCardH4A4A()

      case "u" => controller.undo
      case "r" => controller.redo
      case "end" => controller.Beenden
      case "help" => println(controller.hilfe)
      case "q"=>
    }




  }

 override def update: Unit = println(controller.gameToString)
  override def error(throwable: Throwable): Unit = throwable match{
    case InvalidHandCard(i) => println("Falscher Index: " +i)
    case InvalidMove => println("Dieser Zug geht nicht!")
  }
}
