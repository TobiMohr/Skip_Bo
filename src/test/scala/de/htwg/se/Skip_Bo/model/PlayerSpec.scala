package de.htwg.se.Skip_Bo.model


import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}


class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Your Name", List(Card(Value.Eight), Card(Value.Five), CardComponent.Card(Value.Joker),
          CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)),
        List(List(CardComponent.Card(Value.Four)), Nil, Nil, Nil),
        List(CardComponent.Card(Value.Five), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Six),
          CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven)))
      "have a name"  in {
        player.name should be("Your Name")
      }
      "have 5 hand cards" in {
        player.cards should be(List(CardComponent.Card(Value.Eight), CardComponent.Card(Value.Five),
          CardComponent.Card(Value.Joker), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)))
      }
      "have 4 empty Helpstacks" in {
        player.helpstack should be(List(List(CardComponent.Card(Value.Four)), Nil, Nil, Nil))
      }
      "have a playerstack " in {
        player.stack should be(List(CardComponent.Card(Value.Five), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Joker),
          CardComponent.Card(Value.Six), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven)))
      }
      "have a nice String representation" in {player.toString should be("Your Name")
      }
      "pull" in {
        player.draw2(List(CardComponent.Card(Value.Joker)))
        player.cards.size should be(5)

      }
  }}

}
