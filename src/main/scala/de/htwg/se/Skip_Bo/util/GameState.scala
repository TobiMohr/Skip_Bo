package de.htwg.se.Skip_Bo.util

trait GameState

case class winEvent() extends GameState{

}

case class unknownCommandEvent() extends GameState{

}
