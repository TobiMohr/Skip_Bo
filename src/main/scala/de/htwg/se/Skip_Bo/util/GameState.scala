package de.htwg.se.Skip_Bo.util

trait GameState
case class nextPlayerEvent() extends GameState{
  def nextPlayer = if(State.state == "P1:") "P2:" else "P1:"
}