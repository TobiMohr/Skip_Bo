package de.htwg.se.Skip_Bo

import de.htwg.se.Skip_Bo.aview.TUI
import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.Game

object  Skip_Bo {
  def main(args: Array[String]): Unit = {
    println(" Wilkommen zum besten Spiel")
    val spieler1 = anmeld1(args)
    val spieler2 = anmeld2(args)

    println("Hallöle " + spieler1)
    println("Hallöle " + spieler2)
    println( spieler1 + " ist am zug")

    val controller = new Controller(Game())
    val tui = new TUI(controller)
    controller.notifyObservers
  }

  def anmeld1(spieler:Array[String]):String ={
    readLine("Spieler 1 - Bitte Name eingeben: ")
  }

  def anmeld2(spieler:Array[String]):String ={
    readLine("Spieler 2 - Bitte Name eingeben: ")
  }



}
