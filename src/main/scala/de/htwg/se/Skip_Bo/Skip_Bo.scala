package de.htwg.se.Skip_Bo

import de.htwg.se.Skip_Bo.aview.TUI
import de.htwg.se.Skip_Bo.controller
import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model._

object  Skip_Bo {
  val controller = new Controller(Stack(List(Card(Colour.red,1),Card(Colour.green,5))),Card(Colour.blue,3), new Board)
  def main(args: Array[String]): Unit = {
    println(" Wilkommen zum besten Spiel")
    val spieler1 = anmeld1(args)
    val spieler2 = anmeld2(args)

    println("Hallöle " + spieler1)
    println("Hallöle " + spieler2)
    println( spieler1 + " ist am zug")
    new TUI(controller);
  }

  def anmeld1(spieler:Array[String]):String ={
    readLine("Spieler 1 - Bitte Name eingeben: ")
  }

  def anmeld2(spieler:Array[String]):String ={
    readLine("Spieler 2 - Bitte Name eingeben: ")
  }



}
