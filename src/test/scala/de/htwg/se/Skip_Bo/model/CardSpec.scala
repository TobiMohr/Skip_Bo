package de.htwg.se.Skip_Bo.model


import org.scalatest._

class CardSpec extends WordSpec with Matchers {

    "A Card" when{

      "should consist of a rank and a colour"should{
        val card= Card("red", 1)
        "be red" in{
          card.colour should be("red")
        }
        "be green" in {
          card.colour should be("green")
        }
        "be blue" in{
          card.colour should be("blue")
        }
        "greater 0" in {
          card.rank should be >= 1
        }
        "lesser 13" in{
          card.rank should be <= 12
        }
      }
    }
}
