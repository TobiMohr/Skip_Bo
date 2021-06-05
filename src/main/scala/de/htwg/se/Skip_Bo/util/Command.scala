package de.htwg.se.Skip_Bo.util

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit
}
