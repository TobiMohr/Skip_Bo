package de.htwg.se.Skip_Bo.util

object State {
  var state = println("")
  def handle(g: GameState)= {
    g match {
      case win: winEvent => state = winEvent
      case unknown: unknownCommandEvent => state = unknownCommandEvent
    }
    state
  }
  def winEvent = println("Du hast gewonnen!")
  def unknownCommandEvent = println("Dieser Befehl ist unbekannt.")

}
