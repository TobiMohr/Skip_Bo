package de.htwg.se.Skip_Bo.controller

trait PlayerState {
  def turnChange: PlayerState
  def getPlayer: Int
}

object PlayerA extends PlayerState {
  override def turnChange: PlayerState = PlayerB
  override def getPlayer: Int = 0
}

object PlayerB extends PlayerState {
  override def turnChange: PlayerState = PlayerA
  override def getPlayer: Int = 1
}