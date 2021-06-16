package de.htwg.se.Skip_Bo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.+:
import scala.util.{Failure, Success}

class GameSpec extends AnyWordSpec with Matchers {
  "A Game" when {
    val game = Game(List(Nil, Nil, Nil, Nil), Nil, Nil)
    "new" should {
      "have 4 empty stacks" in {
        game.stack should be(List(Nil, Nil, Nil, Nil))
      }
      "have an empty List of Players" in {
        game.player should be(Nil)
      }
      "have an empty stack to draw from" in {
        game.cardsCovered should be(Nil)
      }
    }
    "started" should {
      val start = game.startGame(5)
      "have a string representation" in {
        start.toString should be ("test")
      }
      "4 stacks" in {
        start.stack(0) should be(List())
        start.stack(1) should be(List())
        start.stack(2) should be(List())
        start.stack(3) should be(List())
      }
      "a stack to draw from" in {
        start.cardsCovered should be(start.cardsCovered) //wie testet man random listen?
      }
      "have 2 players" in {
        start.player should be(List(start.player(0), start.player(1)))
      }
      "who can put a card from hand with correct index on a helpstack" in {
        start.pushCardHand(0, 1, 0, true) should be (Success(start))
      }
      "who can put a card from hand with correct index on a stack" in {
        start.pushCardHand(0, 1, 0, false) should be (Success(start))
      }
      "who cant push a card from a helpstack on a stack at the beginning" in {
        start.pushCardHelp(0, 1, 0) should be(Failure(InvalidMove))
      }
      "who can push a card from a playerstack on a stack" in {
        start.pushCardPlayer(0, 1) should be(Success(start))
      }
      "draw" in {
        start.pull(0) should be(start)
      }
      "be able to test if a card from hand is a valid placement on a stack" in {
        start.checkCardHand(Card(Value.One), Nil) should be(true)
        start.checkCardHand(Card(Value.Joker), Nil) should be(true)
        start.checkCardHand(Card(Value.Joker), List(Card(Value.One))) should be(true)
        start.checkCardHand(Card(Value.One), List(Card(Value.One))) should be(false)
        start.checkCardHand(Card(Value.Joker), List(Card(Value.Joker))) should be(false)
        start.checkCardHand(Card(Value.Five),
          List(Card(Value.Joker), Card(Value.Three), Card(Value.Two), Card(Value.One))) should be(true)
        start.checkCardHand(Card(Value.Five),
          List(Card(Value.Four), Card(Value.Three), Card(Value.Two), Card(Value.One))) should be(true)
      }

    }

  }

}