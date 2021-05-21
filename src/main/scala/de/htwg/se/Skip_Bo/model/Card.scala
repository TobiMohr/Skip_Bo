package de.htwg.se.Skip_Bo.model

import de.htwg.se.Skip_Bo.model.Colour.Colour
import de.htwg.se.Skip_Bo.model.Value.Values

  case class Card( value: Values){

    override def toString:String ={
      value match{
        case Value.One =>"1"
        case Value.Two =>"2"
        case Value.Three =>"3"
        case Value.Four =>"4"
        case Value.Five =>"5"
        case Value.Six =>"6"
        case Value.Seven =>"7"
        case Value.Eight =>"8"
        case Value.Nine =>"9"
        case Value.Ten =>"10"
        case Value.Eleven =>"11"
        case Value.Twelve =>"12"
        case Value.Joker => "J"
        case Value.Null => "0"
      }

    }
//  def accuracy(): Boolean = {
//      if (rank <= 12 && rank > 0) {
//         true
//      } else {
//        false
//      }
//  }


   //override def toString:String = "a " + colour + " " + rank




}
