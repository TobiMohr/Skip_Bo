package de.htwg.se.Skip_Bo.model


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}


class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Your Name", List(Card(Value.Eight), Card(Value.Five), Card(Value.Joker),
          Card(Value.Seven), Card(Value.Six)),
        List(List(Card(Value.Four)), Nil, Nil, Nil),
        List(Card(Value.Five), Card(Value.Seven), Card(Value.Joker), Card(Value.Six),
          Card(Value.Eight), Card(Value.Twelve), Card(Value.Two), Card(Value.Seven)))
      "have a name"  in {
        player.name should be("Your Name")
      }
      "have 5 hand cards" in {
        player.cards should be(List(Card(Value.Eight), Card(Value.Five),
          Card(Value.Joker), Card(Value.Seven), Card(Value.Six)))
      }
      "have 4 empty Helpstacks" in {
        player.helpstack should be(List(List(Card(Value.Four)), Nil, Nil, Nil))
      }
      "have a playerstack " in {
        player.stack should be(List(Card(Value.Five), Card(Value.Seven), Card(Value.Joker),
          Card(Value.Six), Card(Value.Eight), Card(Value.Twelve), Card(Value.Two), Card(Value.Seven)))
      }
      "be able to choose a certain card from his hand per  correct index" +
        " and shouldnt have it in hand afterwards" in {
        player.getCard(0) should be(Success(Card(Value.Eight),"Your Name"))
      }
      "shouldnt be able to choose a certain card from his hand per incorrect index" +
        " and should still have it in hand afterwards" in {
        player.getCard(6) should be(Failure(InvalidHandCard(6)))
      }
      "be able to see the top Card of his playerstack" in {
        player.stackCard() should be(Success(Card(Value.Five), "Your Name"))
      }
      "have no cards in his helpstacks" in {
        player.helpCard(0) should be(Success(Card(Value.Four), "Your Name"))
      }
      "have a nice String representation" in {player.toString should be("Your Name")
      }
  }}

}
