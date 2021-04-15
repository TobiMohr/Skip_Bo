package de.htwg.se.Skip_Bo.model

import de.htwg.se.Skip_Bo.model.Colour.Colour

case class Card(colour: Colour, rank: Int){


  override def toString:String = "a " + colour + " " + rank



}
