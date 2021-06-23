package de.htwg.se.Skip_Bo.model.GameComponent

import de.htwg.se.Skip_Bo.model.CardComponent.Card

import scala.util.Try

trait GameInterface{

  def startGame(numOfPlayerCards: Int): GameInterface
  def pushCardHand(i: Int, j: Int, n: Int, helpst: Boolean): Try[GameInterface]
  def pushCardHelp(i: Int, j: Int, n: Int): Try[GameInterface]
  def pushCardPlayer(i: Int, n: Int): Try[GameInterface]
  def pull(n: Int): GameInterface
  def checkCardHand(card: Card, stack: List[Card]): Boolean
  def refill(j: Int): GameInterface
  def checkGameState(): GameInterface
  def toString(n: Int): String
}
