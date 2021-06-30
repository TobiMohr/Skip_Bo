package de.htwg.se.Skip_Bo.model.fileIOComponent

import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface

trait fileIOInterface {
  def load: GameInterface
  def save(game: GameInterface): Unit
}
