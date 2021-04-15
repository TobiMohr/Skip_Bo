package de.htwg.se.Skip_Bo.model


import org.scalatest._

class CardSpec extends WordSpec with Matchers {

    "A Card" when{
      "new"should{
        val card = Card(String, int)
        "have value colour" in{
          card.1 should be(Colour)
        }
        "have value int" in {
          card.2 should be > 0
          card.2 should be <= 12
        }
      }
    }
}
