package de.htwg.se.Skip_Bo.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface
import de.htwg.se.Skip_Bo.util.Command

import scala.util.{Failure, Success}

class PushCardPlayerCommand (i: Int, n: Int, controller: Controller) extends Command{

  var memento: GameInterface = controller.game

  override def doStep: Unit = {
    memento = controller.game
    val newGame = controller.game.pushCardPlayer(i, n) match {
      case Failure(_) => controller.game
      case Success(value) => value

    }
    val newGame2 = newGame.refill(i) match {
      case None => newGame
      case Some(value) => value
    }

    controller.game = newGame2
  }

  override def undoStep: Unit = {
    val new_memento = controller.game
    controller.game = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    memento = controller.game
    val newGame = controller.game.pushCardPlayer(i, n) match {
      case Failure(_) => controller.game
      case Success(value) => value

    }
    val newGame2 = newGame.refill(i) match {
      case None => newGame
      case Some(value) => value
    }
    controller.game = newGame2
  }
}
