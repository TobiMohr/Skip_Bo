package de.htwg.se.Skip_Bo.model


import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardSpec extends AnyWordSpec with Matchers {

  "A Card" when{
    "new" should{
      val card1 = Card(Value.Eight)
      val card2 = Card(Value.Five)
      val card3 = CardComponent.Card(Value.One)
      val card4 = CardComponent.Card(Value.Joker)
      val card5 = CardComponent.Card(Value.Twelve)

      "have a Value" in{
        card1.value should be(Value.Eight)
        card2.value should be(Value.Five)
        card3.value should be(Value.One)
        card4.value should be(Value.Joker)
        card5.value should be(Value.Twelve)
      }

      "have a nice String representation" in{
        card1.toString should be("8")
        card2.toString should be("5")
        card3.toString should be("1")
        card4.toString should be("J")
        card5.toString should be("12")
      }
    }
  }
}



