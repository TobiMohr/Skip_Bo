package de.htwg.se.Skip_Bo.model

import de.htwg.se.Skip_Bo.model.Colour.Colour



  case class Card(colour: Colour, rank: Int){

  def accuracy(): Boolean = {
      if (rank <= 12 && rank > 0) {
         true
      } else {
        false
      }
  }


   override def toString:String = "a " + colour + " " + rank




}
