package de.htwg.se.Skip_Bo.aview

import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Card, Colour, Stack}
import de.htwg.se.Skip_Bo.util.Observer


class TUI(controller: Controller) extends Observer{
  controller.add(this)
  def processInputLine(input: String): Unit = {
    val l:Array[String] = input.split(" ")
    l(0) match {
      //start Game
      case "s" => controller.startGame()
      case "ph1" => {
        val s = l(1).toInt
        if(controller.checkCardHand(s)){
          controller.pushCardHand1A(s)
        } else {
          println("Diese Karte kannst du nicht ablegen.")
        }
      }
      case "ph2" => {
        val s = l(1).toInt
        if(controller.checkCardHand(s)){
          controller.pushCardHand2A(s)
        } else {
          println("Diese Karte kannst du nicht ablegen.")
        }
      }

      case "ph3" => {
        val s = l(1).toInt
        if(controller.checkCardHand(s)){
          controller.pushCardHand3A(s)
        } else {
          println("Diese Karte kannst du nicht ablegen.")
        }
      }
      case "ph4" => {
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
      case "pS1" => {
        controller.pushCardStapel1A()
      }
      case "pS2" => {
        controller.pushCardStapel2A()
      }
      case "pS3" => {
        controller.pushCardStapel3A()
      }
      case "pS4" => {
        controller.pushCardStapel4A()
      }
      case "end" => controller.Beenden
      case "help" => println(controller.hilfe)
      case "q"=>
    }




  }

 override def update: Unit = println(controller.gameToString)
}
