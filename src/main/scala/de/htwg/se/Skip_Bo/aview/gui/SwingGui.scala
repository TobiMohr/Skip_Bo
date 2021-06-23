package de.htwg.se.Skip_Bo.aview.gui

import de.htwg.se.Skip_Bo.controller.{CardPlaced, Controller, GameWon}
import de.htwg.se.Skip_Bo.model.Card

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._
import scala.swing


class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "HTWG Skip_Bo"

  val statusline = new TextField(controller.statusText, 30)

  def aktion(): BoxPanel = new BoxPanel(Orientation.Vertical){

    val indices = new TextField()
    val indices2 = new TextField()
    contents += indices
    contents += indices2
    contents += Button("Push Card From Hand on Stack"){
      val i = (indices2.text.toInt - 1)        //Handindex
      val j = (indices.text.toInt  - 1)        //Stackindex
      controller.pushCardHand(i, j, controller.playerState.getPlayer, false)
    }
    contents += Button("Push Card From Hand on Helpstack") {
      val i = indices2.text.toInt - 1        //Handindex
      val j = indices.text.toInt - 1         //Stackindex
      controller.pushCardHand(i, j, controller.playerState.getPlayer, true)
    }
    contents += Button("Push Card From Helpstack on Stack") {
      val i = indices.text.toInt - 1
      val j = indices2.text.toInt - 1
      controller.pushCardHelp(i, j, controller.playerState.getPlayer)
    }
    contents += Button("Push Card From Playerstack") {
      val i = indices.text.toInt - 1
      controller.pushCardPlayer(i, controller.playerState.getPlayer)
    }

  }

  def board(): GridPanel = new GridPanel(2,2) {
    if (controller.game.player.isEmpty){
      contents += new Label( "Handkarten: | 0 || 0 || 0 || 0 || 0 |")
    } else {
      contents += new Label(
        if(controller.game.player(controller.playerState.getPlayer).cards.size == 5){
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " || " +
            controller.game.player(controller.playerState.getPlayer).cards(1).toString + "|| " +
            controller.game.player(controller.playerState.getPlayer).cards(2).toString + "|| " +
            controller.game.player(controller.playerState.getPlayer).cards(3).toString + "|| " +
            controller.game.player(controller.playerState.getPlayer).cards(4).toString + " |"
        } else if (controller.game.player(controller.playerState.getPlayer).cards.size == 4){
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " || " +
            controller.game.player(controller.playerState.getPlayer).cards(1).toString + "|| " +
            controller.game.player(controller.playerState.getPlayer).cards(2).toString + "|| " +
            controller.game.player(controller.playerState.getPlayer).cards(3).toString + " |"
        } else if (controller.game.player(controller.playerState.getPlayer).cards.size == 3) {
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " || " +
            controller.game.player(controller.playerState.getPlayer).cards(1).toString + "|| " +
            controller.game.player(controller.playerState.getPlayer).cards(2).toString + " |"
        } else if (controller.game.player(controller.playerState.getPlayer).cards.size == 2) {
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " || " +
            controller.game.player(controller.playerState.getPlayer).cards(1).toString + " |"
        } else if (controller.game.player(controller.playerState.getPlayer).cards.size == 1){
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " |"
        } else {
          ""
        }
      )
    }

    if(controller.game.player.isEmpty) {
      contents += new Label("Hilfsstapel: | leer |  | leer |  | leer |  | leer |")
    } else {
      val h1 = if (controller.game.player(controller.playerState.getPlayer).helpstack(0).isEmpty){
        "leer"
      } else {
        controller.game.player(controller.playerState.getPlayer).helpstack(0).head.toString
      }
      val h2 = if (controller.game.player(controller.playerState.getPlayer).helpstack(1).isEmpty){
        "leer"
      } else {
        controller.game.player(controller.playerState.getPlayer).helpstack(1).head.toString
      }
      val h3 = if (controller.game.player(controller.playerState.getPlayer).helpstack(2).isEmpty){
        "leer"
      } else {
        controller.game.player(controller.playerState.getPlayer).helpstack(2).head.toString
      }
      val h4 = if (controller.game.player(controller.playerState.getPlayer).helpstack(3).isEmpty){
        "leer"
      } else {
        controller.game.player(controller.playerState.getPlayer).helpstack(3).head.toString
      }
      contents += new Label("Hilfsstapel: | " + h1 + " |  | " + h2 + " |  | " + h3 + " |  | " + h4 + " |")
    }

    if (controller.game.stack.isEmpty){
      contents += new Label("Ablagestapel: | leer |  | leer |  | leer |  | leer |")
    } else {
      contents += new Label(
        "Ablagestapel: | " + controller.game.stack(0).size.toString + " |  | " +
          controller.game.stack(1).size.toString + " |  | " +
          controller.game.stack(2).size.toString + " |  | " +
          controller.game.stack(3).size.toString + " |"
      )
    }

    if(controller.game.player.isEmpty){
      contents += new Label("Spielerstapel: | leer | - verbleibende Karten: 0")
    } else {
      if(!controller.game.player(controller.playerState.getPlayer).stack.isEmpty) {
        contents += new Label("Spielerstapel: | " + controller.game.player(controller.playerState.getPlayer).stack.head.toString
          + " | - verbleibende Karten: " + controller.game.player(controller.playerState.getPlayer).stack.size.toString)
      } else {
        contents += new Label("Spielerstapel: | leer | - verbleibende Karten: 0")
      }
    }
  }

  def win(): GridPanel = new GridPanel(1, 1){
    val label = new Label(
      "Spieler " + controller.playerState.name + " hat das Spiel gewonnen!!!"
    )
    label.preferredSize_=(new Dimension(70, 70))
    contents += label
  }


  contents = new BorderPanel {
    add(aktion, BorderPanel.Position.North)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.startGame()
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {
        controller.undo
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo
      })
    }
  }

  visible = true
  redraw

  reactions += {
    case event: CardPlaced => redraw
    case event: GameWon => rewin

  }
  

  def redraw: Unit = {
    contents = new BorderPanel {
      add(aktion, BorderPanel.Position.North)
      add(board, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
    }
    statusline.text = controller.statusText
    repaint
  }

  def rewin: Unit = {
    contents = new BorderPanel {
      add(win, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
    }
    statusline.text = controller.statusText
    repaint
  }

}
