package de.htwg.se.Skip_Bo.model.GameComponent

import de.htwg.se.Skip_Bo.model.CardComponent.Card
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerInterface

import scala.util.Try

trait GameInterface{

  val stack: List[List[Card]]
  val player: List[PlayerInterface]
  val cardsCovered: List[Card]

  def startGame(numOfPlayerCards: Int): GameInterface
  def pushCardHand(i: Int, j: Int, n: Int, helpst: Boolean): Try[GameInterface]
  def pushCardHelp(i: Int, j: Int, n: Int): Try[GameInterface]
  def pushCardPlayer(i: Int, n: Int): Try[GameInterface]
  def pull(n: Int): GameInterface
  def checkCardHand(card: Card, stack: List[Card]): Boolean
  def refill(j: Int): Option[GameInterface]
  def checkGameState(): GameInterface
  def toString(n: Int): String
}
