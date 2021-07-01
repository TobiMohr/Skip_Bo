package de.htwg.se.Skip_Bo.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import com.google.inject.name.Named
import de.htwg.se.Skip_Bo.Skip_BoModule
import de.htwg.se.Skip_Bo.controller.controllerComponent.GameState.{GameState, IDLE, NEXT, PLACEHS, PLACES, PLACESS, START, SAVED, LOADED, WIN}
import de.htwg.se.Skip_Bo.controller.controllerComponent._
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface
import de.htwg.se.Skip_Bo.util.UndoManager
import de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOInterface

import scala.swing.Publisher



class Controller @Inject() (var game: GameInterface) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManager
  var gameState: GameState = IDLE
  var newGameState:GameState = IDLE
  var oldGameState: GameState = IDLE
  var playerState: PlayerState = PlayerA
  var playerStateNow: PlayerState = PlayerA
  val injector = Guice.createInjector(new Skip_BoModule)
  val fileIO = injector.instance[fileIOInterface]

  def startGame( @Named("DefaultHandSize") size: Int ): Unit ={
    game = game.checkGameState()
    game = game.startGame(size)
    gameState = START
    //notifyObservers
    publish(new CardPlaced)
  }

  def pushCardHand(i: Int, j: Int,n: Int,helpst: Boolean ): Unit = {
    undoManager.doStep(new PushCardHandCommand(i, j, n, helpst, this))
    if (helpst) {
        beenden(playerState.turnChange.getPlayer)
    } else if (!helpst && game.player(playerState.turnChange.getPlayer).cards.size == 0){
      beenden(playerState.turnChange.getPlayer)
    } else {
      oldGameState = gameState
      gameState = PLACES
    }
    //notifyObservers
    publish(new CardPlaced)
  }

  //legt Karte vom Hilfsstapel auf Ablegestapel
  def pushCardHelp(i: Int, j:Int, n: Int): Unit = {
    undoManager.doStep(new PushCardHelpCommand(i, j, n, this))
    oldGameState = gameState
    gameState = PLACEHS
    //notifyObservers
    publish(new CardPlaced)
  }

  def pushCardPlayer(i: Int, n: Int):Unit = {
    undoManager.doStep(new PushCardPlayerCommand(i, n, this))
    oldGameState = gameState
    if(game.player(playerState.getPlayer).stack.isEmpty) {
      gameState = WIN
      //notifyObservers
      publish(new GameWon)
    } else {
      gameState = PLACESS
      //notifyObservers
      publish(new CardPlaced)
    }
  }


  def beenden(n:Int): Unit = {
    game = game.pull(n)
    oldGameState = gameState
    gameState = NEXT
  }

  /*def refill(j:Int): Unit = {
    game.refill(j) match {
      case None => game = game
      case Some(newGame) => game = newGame
    }
  }*/

  def gameToString(n:Int): String = game.toString(n)

  def undo: Unit={
    undoManager.undoStep
    newGameState = gameState
    gameState = oldGameState
    //notifyObservers
    publish(new CardPlaced)
  }

  def redo: Unit = {
    undoManager.redoStep
    oldGameState = gameState
    gameState = newGameState
    //notifyObservers
    publish(new CardPlaced)
  }

  def hilfe: String = {
    """---------Hilfe-----------
      ||       Handkarten      ||
      |
      || H1 | H2 | H3 | H4 | S ||
      |
      ||  A1 |  A2 |  A3 |  A4 ||
      |-------------------------
      |ph i j true = legt Handkarte(j) auf Hilfestapel(i) vom Spieler
      |ph i j false = legt Handkarte(j) auf Ablagestapel(i) vom Spieler
      |ps i = legt Karte von Spielerstapen vom Spieler  auf Ablagestapel(i)
      |philfe i j = Spieler legt Karte von Hilfestapel(i) auf Ablagestapel(j)
      |"""
      .stripMargin
  }


  def statusText:String = GameState.message(gameState)

  def save: Unit = {
    fileIO.save(game)
    gameState = SAVED
    publish(new CardPlaced)
    playerStateNow = playerState
  }

  def load: Unit = {
    game = fileIO.load
    playerState = playerStateNow
    gameState = LOADED
    publish(new CardPlaced)
  }

}
