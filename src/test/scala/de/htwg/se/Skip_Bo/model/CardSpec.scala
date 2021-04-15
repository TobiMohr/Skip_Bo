package de.htwg.se.Skip_Bo.model


import org.scalatest._

class CardSpec extends WordSpec with Matchers {

  "A Card" when{
    "new" should{
      val card1 = new Card(Colour.red, 1)
      val card2 = new Card(Colour.green, 6)
      val card3 = new Card(Colour.blue, 12)

      "have a colour" in{
        card1.colour should be(Colour.red)
        card2.colour should be(Colour.green)
        card3.colour should be(Colour.blue)
      }
      "have a rank" in{
        card1.rank should be(1)
        card2.rank should be(6)
        card3.rank should be(12)
      }
      "have a nice String representation" in{
        card1.toString should be("a red 1")
        card2.toString should be("a green 6")
        card3.toString should be("a blue 12")
      }
    }
  }
}



