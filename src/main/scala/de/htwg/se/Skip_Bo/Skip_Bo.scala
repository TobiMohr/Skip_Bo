package de.htwg.se.Skip_Bo

import de.htwg.se.Skip_Bo.model.Player

object Skip_Bo {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Moin, " + student.name)
    println("Commit test")
  }
  // Test

  case class Card(Colour: String, Number: Int) {
    override def toString: String = Number + "(" + Colour + ")"
  }





}
