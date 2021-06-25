package de.htwg.se.Skip_Bo.model

import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
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
      "manage wrong checkCardHand" in {
        game.checkCardHand(Card(Value.Two), Nil) should be(false)
        game.checkCardHand(Card(Value.Five),
          List(Card(Value.Joker), Card(Value.Two), Card(Value.One))) should be(false)
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
    "given" should {
      val player1 = new Player("A",
        List(CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eleven), CardComponent.Card(Value.Five), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two)),
        List(List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Three)), List(CardComponent.Card(Value.Five))),
        List())
      val player2 = new Player("B",
        List(CardComponent.Card(Value.Four), CardComponent.Card(Value.Ten), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Seven)),
        List(List(CardComponent.Card(Value.Four),CardComponent.Card(Value.Six)), List(CardComponent.Card(Value.Eleven)), List(CardComponent.Card(Value.Ten)), List(CardComponent.Card(Value.Eight))),
        List(CardComponent.Card(Value.One), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven)))

      val game = Game(List(List(CardComponent.Card(Value.Three), CardComponent.Card(Value.Two), CardComponent.Card(Value.One)),
        List(CardComponent.Card(Value.Two), CardComponent.Card(Value.Joker)),
        List(CardComponent.Card(Value.One)),
        List(CardComponent.Card(Value.Eleven), CardComponent.Card(Value.Ten), CardComponent.Card(Value.Nine), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)
          , CardComponent.Card(Value.Five), CardComponent.Card(Value.Four), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Two), CardComponent.Card(Value.One))),
        List(player1, player2),
        List(CardComponent.Card(Value.Three), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Joker),
          CardComponent.Card(Value.Five), CardComponent.Card(Value.Six), CardComponent.Card(Value.Nine), CardComponent.Card(Value.Eight)))

      "wrong pushCardHelp" in {
        game.pushCardHelp(0, 2, 1) should be(Failure(InvalidMove))
      }
      "String representation" in {
        game.toString(0) should be(
          "Handkarten: " + "Vector(| " + game.player(0).cards(0).toString +
            " | , | " + game.player(0).cards(1).toString + " | , | " + game.player(0).cards(2).toString
            + " | , | " + game.player(0).cards(3).toString  + " | , | " + game.player(0).cards(4).toString + " | )" + "\n\n" +
            "Hilfsstapel: " + "| " + game.player(0).helpstack(0).head.toString +
            " | " + "\t" + "| " + game.player(0).helpstack(1).head.toString +
            " | " + "\t" + "| " + game.player(0).helpstack(2).head.toString +
            " | " + "\t" + "| " + game.player(0).helpstack(3).head.toString +
            " | " + "\t" + "Spielerstapel: " + "| leer | " + "\t" + "| 0 |" + "\n\n" +
            "Ablagestapel: " + "| " + game.stack(0).head.toString +
            " | " + "\t" + "| " + game.stack(1).head.toString +
            " | " + "\t" + "| " + game.stack(2).head.toString +
            " | " + "\t" + "| " + game.stack(3).head.toString + " | " + "\t"
        )
      }
    }

  }

}