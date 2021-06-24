package de.htwg.se.Skip_Bo.model.PlayerComponent

import de.htwg.se.Skip_Bo.model.CardComponent.Card
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerImpl.Player

import scala.util.Try

trait PlayerInterface {

  val name: String
  val cards: List[Card]
  val helpstack: List[List[Card]]
  val stack: List[Card]

  def getCard(int: Int): Try[(Card, PlayerInterface)]
  def stackCard(): (Card, PlayerInterface)
  def helpCard(int: Int): Try[(Card, PlayerInterface)]
  def putInHelp(int: Int, card: Card): PlayerInterface
  def draw2(card: List[Card]): PlayerInterface
}
