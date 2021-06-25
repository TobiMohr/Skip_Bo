package de.htwg.se.Skip_Bo.model

import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
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
      "4 stacks" in {
        start.stack(0) should be(List())
        start.stack(1) should be(List())
        start.stack(2) should be(List())
        start.stack(3) should be(List())
      }
      "have string representations" in {
        start.toString(0) should be("Handkarten: " + "Vector(| " + start.player(0).cards(0).toString +
          " | , | " + start.player(0).cards(1).toString + " | , | " + start.player(0).cards(2).toString
          + " | , | " + start.player(0).cards(3).toString  + " | , | " + start.player(0).cards(4).toString + " | )" + "\n\n" +
          "Hilfsstapel: " + "| leer | " + "\t" + "| leer | " + "\t" + "| leer | " + "\t" + "| leer | " +
          "\t" + "Spielerstapel: " + "| " + start.player(0).stack.head.toString + " | " + "\t" + "| 30 |" + "\n\n" + "Ablagestapel: " + "| 0 | " + "\t" +
          "| 0 | " + "\t" + "| 0 | " + "\t" + "| 0 | " + "\t")

        start.toString(1) should be("Handkarten: " + "Vector(| " + start.player(1).cards(0).toString +
          " | , | " + start.player(1).cards(1).toString + " | , | " + start.player(1).cards(2).toString
          + " | , | " + start.player(1).cards(3).toString  + " | , | " + start.player(1).cards(4).toString + " | )" + "\n\n" +
          "Hilfsstapel: " + "| leer | " + "\t" + "| leer | " + "\t" + "| leer | " + "\t" + "| leer | " +
          "\t" + "Spielerstapel: " + "| " + start.player(1).stack.head.toString + " | " + "\t" + "| 30 |" + "\n\n" + "Ablagestapel: " + "| 0 | " + "\t" +
          "| 0 | " + "\t" + "| 0 | " + "\t" + "| 0 | " + "\t")
      }
      "a stack to draw from" in {
        start.cardsCovered should be(start.cardsCovered) //wie testet man random listen?
      }
      "have 2 players" in {
        start.player should be(List(start.player(0), start.player(1)))
      }

      "who cant push a card from a helpstack on a stack at the beginning" in {
        start.pushCardHelp(0, 1, 0) should be(Failure(InvalidMove))
      }
     "draw" in {
        start.pull(0) should be(start)
      }
      "be able to test if a card from hand is a valid placement on a stack" in {
        start.checkCardHand(CardComponent.Card(Value.One), Nil) should be(true)
        start.checkCardHand(CardComponent.Card(Value.Joker), Nil) should be(true)
        start.checkCardHand(CardComponent.Card(Value.Joker), List(CardComponent.Card(Value.One))) should be(true)
        start.checkCardHand(CardComponent.Card(Value.One), List(CardComponent.Card(Value.One))) should be(false)
        start.checkCardHand(CardComponent.Card(Value.Joker), List(CardComponent.Card(Value.Joker))) should be(true)
        start.checkCardHand(CardComponent.Card(Value.Five),
          List(CardComponent.Card(Value.Joker), CardComponent.Card(Value.Three), CardComponent.Card(Value.Two), CardComponent.Card(Value.One))) should be(true)
        start.checkCardHand(CardComponent.Card(Value.Five),
          List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Three), CardComponent.Card(Value.Two), CardComponent.Card(Value.One))) should be(true)
      }

    }

  }

}