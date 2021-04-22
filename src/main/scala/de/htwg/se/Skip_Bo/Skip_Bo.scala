package de.htwg.se.Skip_Bo

import de.htwg.se.Skip_Bo.model.{Player, TUI}

object Skip_Bo {
  def main(args: Array[String]): Unit = {
    println(" Wilkommen zum besten Spiel")
    val start = "Hallöle " + anmeld(args)
    println(start)
    new TUI();
  }

  def anmeld(spieler:Array[String]):String ={
    readLine("Bitte Name eingeben: ")
  }



}
