package de.htwg.se.Skip_Bo.model.fileIOComponent

import de.htwg.se.Skip_Bo.model.CardComponent.Card
import de.htwg.se.Skip_Bo.model.CardComponent.Value.Values
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface

trait fileIOInterface {
  def load: GameInterface
  def save(game: GameInterface): Unit
  def stringToValue(s: String): Values

  def stack1: List[Card]
  def stack2: List[Card]
  def stack3: List[Card]
  def stack4: List[Card]
  def cp1: List[Card]
  def cp2: List[Card]
  def hs1p1: List[Card]
  def hs2p1: List[Card]
  def hs3p1: List[Card]
  def hs4p1: List[Card]
  def hs1p2: List[Card]
  def hs2p2: List[Card]
  def hs3p2: List[Card]
  def hs4p2: List[Card]
  def sp1: List[Card]
  def sp2: List[Card]
  def cover: List[Card]
}
