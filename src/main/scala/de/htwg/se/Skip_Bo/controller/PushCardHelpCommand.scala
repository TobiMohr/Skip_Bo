package de.htwg.se.Skip_Bo.controller

import de.htwg.se.Skip_Bo.model.Game
import de.htwg.se.Skip_Bo.util.Command

import scala.util.{Failure, Success}

class PushCardHelpCommand (i: Int,j:Int,n: Int, controller: Controller) extends Command{

  var memento: Game = controller.game

  override def doStep: Unit = {
    memento = controller.game
    val newGame = controller.game.pushCardHelp(i, j, n) match {
      case Failure(exception) => controller.game
      case Success(value) => value

    }
    val newGame2 = newGame.refill(j)
    controller.game = newGame2
  }

  override def undoStep: Unit = {
    val new_memento = controller.game
    controller.game = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    memento = controller.game
    val newGame = controller.game.pushCardHelp(i, j, n) match {
      case Failure(exception) => controller.game
      case Success(value) => value

    }
    val newGame2 = newGame.refill(j)
    controller.game = newGame2
  }
}
