package de.htwg.se.Skip_Bo.controller.controllerComponent

object GameState extends Enumeration {
  type GameState = Value
  val IDLE, START, PLACES, PLACEHS, PLACESS, NEXT, WIN = Value

  val map = Map[GameState, String](
    IDLE -> "",
    START -> "Das Spiel zwischen A und B hat begonnen, A ist am Zug",
    PLACES -> "platziert Karte von Hand auf Ablagestapel",
    PLACEHS -> "platziert karte von Hilfestapel auf Ablagestapel",
    PLACESS -> "platziert Karte von Spielerstapel auf Ablagestapel",
    NEXT -> "Zug beendet nächster Spieler ist am Zug",
    WIN -> "Aktueller Spieler hat das Spiel gewonnen"
  )

  def message(gameState: GameState) = {
    map(gameState)
  }


}
