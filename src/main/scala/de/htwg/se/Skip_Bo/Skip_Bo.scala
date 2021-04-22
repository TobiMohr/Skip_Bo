package de.htwg.se.Skip_Bo

import de.htwg.se.Skip_Bo.model.Player

object Skip_Bo {
  def main(args: Array[String]) = {
    println(" Wilkommen zum besten Spiel")
    val start = "Hallöle " + anmeld(args)
    println(start)
  }

  def anmeld(spieler:Array[String]):String ={
    readLine("Bitte Name eingeben: ")
  }



}
