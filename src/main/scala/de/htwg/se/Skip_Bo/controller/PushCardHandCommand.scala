package de.htwg.se.Skip_Bo.controller

import de.htwg.se.Skip_Bo.model.Game
import de.htwg.se.Skip_Bo.util.Command

import scala.util.{Failure, Success}

class PushCardHandCommand (i: Int,j: Int,n: Int,helpst :Boolean, controller : Controller) extends Command{

  var memento: Game = controller.game
  var state: PlayerState = controller.playerState

  override def doStep: Unit = {
    state = controller.playerState
    memento = controller.game
    val newGame = controller.game.pushCardHand(i, j, n, helpst) match {
      case Failure(exception) => controller.game
      case Success(value) => value

    }
    if(helpst){
      controller.playerState = state.turnChange
    }
    val newGame2 = newGame.refill(i)
    controller.game = newGame
    controller.game = newGame2

    //Man kann aktuell keine Karten mit index 4 ablegen but why

  }

  override def undoStep: Unit = {
    val new_memento = controller.game
    val new_state = controller.playerState
    controller.playerState = state
    controller.game = memento
    state = new_state
    memento = new_memento
  }

  /*override def redoStep: Unit = memento = controller.game.pushCardHand(i, j, n, helpst) match {
    case Failure(exception) => controller.game
    case Success(value) => controller.game = value
      controller.game
  }*/

  override def redoStep: Unit = {
    val new_memento = controller.game
    val new_state = controller.playerState
    controller.playerState = state
    controller.game = memento
    state = new_state
    memento = new_memento
  }
}
