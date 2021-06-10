package de.htwg.se.Skip_Bo.controller

object GameState extends Enumeration{
  type GameState = Value
  val IDLE, PLACE, GAMEOVER = Value

  val map = Map[GameState, String](
    IDLE -> "",
    PLACE -> "Bitte lege eine Karte auf den Ablagestapel oder Hilfsstapel",
    GAMEOVER -> "Das Spiel ist vorbei"
  )

  def message(gameState: GameState): Unit ={
    map(gameState)
  }
}
