package de.htwg.se.Skip_Bo.aview.gui

import de.htwg.se.Skip_Bo.controller.controllerComponent.{CardPlaced, ControllerInterface, GameWon}

import scala.swing._
import scala.swing.event._



class SwingGui(controller: ControllerInterface) extends Frame {

  listenTo(controller)

  title = "HTWG Skip_Bo"
  preferredSize = new Dimension(600, 800)

  val statusline = new TextField(controller.statusText, 30)

  def aktion(): BoxPanel = new BoxPanel(Orientation.Vertical){
    if(!controller.game.player.isEmpty)contents += new Label(
      "Spieler " + controller.playerState.name + " ist am Zug. Wähle eine Aktion."
    ) else {
      contents += new Label(
        "Starte das Spiel über das Menüverzeichnis File/New"
      )
    }

    val indices = new TextField()
    val indices2 = new TextField()

    contents += Button("Karte von Hand auf Ablagestapel"){
      val i = (indices2.text.toInt - 1)        //Handindex
      val j = (indices.text.toInt  - 1)        //Stackindex
      controller.pushCardHand(i, j, controller.playerState.getPlayer, false)
    }
    contents += Button("Karte von Hand auf Hilfsstapel") {
      val i = indices2.text.toInt - 1        //Handindex
      val j = indices.text.toInt - 1         //Stackindex
      controller.pushCardHand(i, j, controller.playerState.getPlayer, true)
    }
    contents += Button("Karte von Hilfestapel auf Ablagestapel") {
      val i = indices.text.toInt - 1
      val j = indices2.text.toInt - 1
      controller.pushCardHelp(i, j, controller.playerState.getPlayer)
    }
    contents += Button("Karte vom Spielerstapel auf Ablagestapel") {
      val i = indices2.text.toInt - 1
      controller.pushCardPlayer(i, controller.playerState.getPlayer)
    }

    contents += new Label("Welche Karte(Index 1 -> 5): ")
    contents += indices
    contents += new Label("Wohin(Index 1 -> 5): ")
    contents += indices2


  }

  def board(): GridPanel = new GridPanel(4,1) {

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

    if (controller.game.player.isEmpty){
      contents += new Label( "Handkarten: | 0 || 0 || 0 || 0 || 0 |")
    } else {
      contents += new Label(
        if(controller.game.player(controller.playerState.getPlayer).cards.size == 5){
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(1).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(2).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(3).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(4).toString + " |"
        } else if (controller.game.player(controller.playerState.getPlayer).cards.size == 4){
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(1).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(2).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(3).toString + " |"
        } else if (controller.game.player(controller.playerState.getPlayer).cards.size == 3) {
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(1).toString + " | | " +
            controller.game.player(controller.playerState.getPlayer).cards(2).toString + " |"
        } else if (controller.game.player(controller.playerState.getPlayer).cards.size == 2) {
          "Handkarten: | " + controller.game.player(controller.playerState.getPlayer).cards(0).toString + " | | " +
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

  }

  def win(): GridPanel = new GridPanel(2,1){
    val label = new Label(
      "Spieler " + controller.playerState.name + " hat das Spiel gewonnen!!!"
    )
    label.preferredSize_=(new Dimension(70, 70))
    contents += label
    val button = new Button(Action("Play Again!"){
      controller.startGame(5)
    })
    button.preferredSize_=(new Dimension(30, 30))
    contents += button

  }


  contents = new BorderPanel {
    add(aktion, BorderPanel.Position.North)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.startGame(5)
      })
      contents += new MenuItem(Action("Save") { controller.save })
      contents += new MenuItem(Action("Load") { controller.load })
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
  centerOnScreen()

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
