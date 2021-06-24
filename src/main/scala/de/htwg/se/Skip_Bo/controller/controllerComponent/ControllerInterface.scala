package de.htwg.se.Skip_Bo.controller.controllerComponent

import de.htwg.se.Skip_Bo.controller.controllerComponent.GameState.GameState
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface

import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  var game:GameInterface

  def startGame(size: Int): Unit
  def pushCardHand(i: Int, j: Int, n: Int, helpst: Boolean): Unit
  def pushCardHelp(i: Int, j: Int, n: Int): Unit
  def pushCardPlayer(i: Int, n: Int): Unit
  def beenden(n: Int): Unit
  def refill(j: Int): Unit
  def gameToString(n: Int): String
  def undo: Unit
  def redo: Unit
  def hilfe: String
  def statusText: String

  def gameState: GameState
  def newGameState:GameState
  def oldGameState: GameState
  def playerState: PlayerState
}


import scala.swing.event.Event

class CardPlaced extends Event
class GameWon extends Event
