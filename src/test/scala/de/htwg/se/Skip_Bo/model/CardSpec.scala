package de.htwg.se.Skip_Bo.model


import org.scalatest._

class CardSpec extends WordSpec with Matchers {

  "A Card" when{
    "new" should{
      val card1 = Card(Colour.red, 1)
      val card2 = Card(Colour.green, 6)
      val card3 = Card(Colour.blue, 12)
      //val card4 = Card("red", 1)
      val card5 = Card(Colour.red, 0)

      "have a colour" in{
        card1.colour should be(Colour.red)
        card2.colour should be(Colour.green)
        card3.colour should be(Colour.blue)
        card5.colour should be(Colour.red)
      }
      "have a rank" in{
        card1.rank should be(1)
        card2.rank should be(6)
        card3.rank should be(12)
        card5.rank should be(0)
      }
      "have a rank between 1 and 12" in {
        card1.accuracy() should be(true)
        card2.accuracy() should be(true)
        card3.accuracy() should be(true)
        card5.accuracy() should be(false)
      }
      "have a nice String representation" in{
        card1.toString should be("a red 1")
        card2.toString should be("a green 6")
        card3.toString should be("a blue 12")
        card5.toString should be("a red 0")
      }
    }
  }
}



