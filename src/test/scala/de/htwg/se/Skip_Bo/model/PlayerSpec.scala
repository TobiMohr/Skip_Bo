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
      "have a nice String representation" in {player.toString should be("Your Name")
      }
  }}

}
