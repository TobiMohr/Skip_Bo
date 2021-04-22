package de.htwg.se.Skip_Bo

import de.htwg.se.Skip_Bo.model.{Player, TUI}

object Skip_Bo {
  def main(args: Array[String]): Unit = {
    println(" Wilkommen zum besten Spiel")
    val spieler1 = anmeld1(args)
    val spieler2 = anmeld2(args)

    println("Hallöle" + spieler1)
    println("Hallöle" + spieler2)
    println( spieler1 + " ist am zug")
    new TUI();
  }

  def anmeld1(spieler:Array[String]):String ={
    readLine("Spieler1 - Bitte Name eingeben: ")
  }

  def anmeld2(spieler:Array[String]):String ={
    readLine("Spieler2 - Bitte Name eingeben: ")
  }



}
