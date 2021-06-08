package de.htwg.se.Skip_Bo.util

object State {
  var state= ""
  def handle(gs: GameState)={
    gs match{
      case a: nextPlayerEvent => state = nextPlayerEvent().nextPlayer
    }
    state
  }
}
