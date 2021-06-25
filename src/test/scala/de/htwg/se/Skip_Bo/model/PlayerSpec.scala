package de.htwg.se.Skip_Bo.model

import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}


class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Your Name", List(CardComponent.Card(Value.Eight), CardComponent.Card(Value.Five), CardComponent.Card(Value.Joker),
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
      "manage handcards" in {
        player.getCard(6) should be(Failure(de.htwg.se.Skip_Bo.model.InvalidHandCard(6)))
      }
      "manage helpstack cards" in {
        player.helpCard(1) should be(Failure(InvalidMove))
        player.helpCard(0) should be(Success(Card(Value.Four),
          Player("Your Name", List(CardComponent.Card(Value.Eight), CardComponent.Card(Value.Five), CardComponent.Card(Value.Joker),
            CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)),
            List(Nil, Nil, Nil, Nil),
            List(CardComponent.Card(Value.Five), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Six),
              CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven)))))
      }
      "put in help" in {
        player.putInHelp(0, Card(Value.Five)) should be(
          Player("Your Name", List(CardComponent.Card(Value.Eight), CardComponent.Card(Value.Five), CardComponent.Card(Value.Joker),
          CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)),
          List(List(Card(Value.Five), Card(Value.Four)), Nil, Nil, Nil),
          List(CardComponent.Card(Value.Five), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Six),
            CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven))))
      }
      "get stackCard" in {
        player.stackCard() should be (
          Card(Value.Five), Player("Your Name", List(CardComponent.Card(Value.Eight), CardComponent.Card(Value.Five), CardComponent.Card(Value.Joker),
            CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)),
            List(List(Card(Value.Four)), Nil, Nil, Nil),
            List(CardComponent.Card(Value.Seven), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Six),
              CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven))))
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
