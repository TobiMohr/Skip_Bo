package de.htwg.se.Skip_Bo.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Skip_Bo.controller.controllerComponent.PlayerState
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface
import de.htwg.se.Skip_Bo.util.Command

import scala.util.{Failure, Success}

class PushCardHandCommand (i: Int,j: Int,n: Int,helpst :Boolean, controller : Controller) extends Command{

  var memento: GameInterface = controller.game
  var state: PlayerState = controller.playerState

  override def doStep: Unit = {
    state = controller.playerState
    memento = controller.game
    val newGame = controller.game.pushCardHand(i, j, n, helpst) match {
      case Failure(_) => controller.game
      case Success(value) => value

    }
    if(helpst){
      controller.playerState = state.turnChange
    } else if ((!helpst) && newGame.player(controller.playerState.getPlayer).cards.size == 0) {
      controller.playerState = state.turnChange
    }

    val newGame2 = newGame.refill(i) match {
      case None => newGame
      case Some(value) => value
    }
    controller.game = newGame2

  }

  override def undoStep: Unit = {
    val new_memento = controller.game
    val new_state = controller.playerState
    controller.playerState = state
    controller.game = memento
    state = new_state
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = controller.game
    val new_state = controller.playerState
    controller.playerState = state
    controller.game = memento
    state = new_state
    memento = new_memento
  }
}
