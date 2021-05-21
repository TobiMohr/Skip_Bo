package de.htwg.se.Skip_Bo.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.Skip_Bo.model.{Colour, Value}
import scala.util.Random

case class Game(numOfCards: Int = 5) {
  //"Aufnehmstapel"
  var cardsCovered = new ListBuffer[Card]()
  //Handkarten Spieler A
  var plACards = new ListBuffer[Card]()
  // Handkarten Spieler B
  var plBCards = new ListBuffer[Card]()
  //Spielerstapel des Spielers A
  var plAstack = new ListBuffer[Card]()
  //Spielerstapel des SpielersB
  var plBstack = new ListBuffer[Card]()
  // Ablegestapel
  var stack1 = new ListBuffer[Card]()
  stack1 += Card(Value.Null)
  var stack2 = new ListBuffer[Card]()
  stack2 += Card(Value.Null)
  var stack3 = new ListBuffer[Card]()
  stack3 += Card(Value.Null)
  var stack4 = new ListBuffer[Card]()
  stack4 += Card(Value.Null)
  //"Hilfsstapel" des Spielers A
  var helpAstack1 = new ListBuffer[Card]()
  helpAstack1 += Card(Value.Null)
  var helpAstack2 = new ListBuffer[Card]()
  helpAstack2 += Card(Value.Null)
  var helpAstack3 = new ListBuffer[Card]()
  helpAstack3 += Card(Value.Null)
  var helpAstack4 = new ListBuffer[Card]()
  helpAstack4 += Card(Value.Null)
  //"Hilfsstapel" des Spielers B
  var helpBstack1 = new ListBuffer[Card]()
  helpBstack1 += Card(Value.Null)
  var helpBstack2 = new ListBuffer[Card]()
  helpBstack2 += Card(Value.Null)
  var helpBstack3 = new ListBuffer[Card]()
  helpBstack3 += Card(Value.Null)
  var helpBstack4 = new ListBuffer[Card]()
  helpBstack4 += Card(Value.Null)
  startGame(numOfCards)

  //baut Grundspiel auf
  def startGame(numOfPlayerCards: Int): Unit = {
    //erstellt Kartendeck
    for (value <- Value.values) {
      if(value == Value.Joker){
        for(_<-0 to 17){
          cardsCovered += Card(value)
        }
      }else if (value == Value.One){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Two){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Three){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Four){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Five){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Six){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Seven){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Eight){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Nine){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (Value == Value.Ten){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Eleven){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }else if (value == Value.Twelve){
        for(_<-0 to 11){
          cardsCovered += Card(value)
        }
      }
    }

    println(cardsCovered)

    //Mischelt Kartendeck (Aufnehmstapel)
    cardsCovered= Random.shuffle(cardsCovered)

    //erstellt Handkarten für Spieler A und B
    for (a <- 1 to numOfPlayerCards) {
      plACards = cardsCovered.head +: plACards
      cardsCovered = cardsCovered.drop(1)
      plBCards = cardsCovered.head +: plBCards
      cardsCovered = cardsCovered.drop(1)
    }

    println(plACards)

    //erstellt Spielerstapel für Spieler A und B
    for (a <- 1 to 30){
      plAstack = cardsCovered.head +: plAstack
      cardsCovered = cardsCovered.drop(1)
      plBstack = cardsCovered.head +: plBstack
      cardsCovered = cardsCovered.drop(1)
    }

  }

  //legt Karte auf Ablegestapel von Spieler A
  def pushCardHand1A(int: Int): Game = {
        stack1 = plACards(int) +: stack1
        plACards = plACards.take(int) ++ plACards.drop(int + 1)
    this
  }

  def pushCardHand2A(int: Int): Game = {
        stack2 = plACards(int) +: stack2
        plACards = plACards.take(int) ++ plACards.drop(int + 1)
    this
  }

  def pushCardHand3A(int :Int): Game = {
        stack3 = plACards(int) +: stack3
        plACards = plACards.take(int) ++ plACards.drop(int + 1)
    this
  }

  def pushCardHand4A(int: Int): Game = {
        stack4 = plACards(int) +: stack4
        plACards = plACards.take(int) ++ plACards.drop(int + 1)
    this
  }

  //legt Karte auf Hilfsstapel von Spieler A
  def ablegen1A(stapel: Int): Game={
    helpAstack1 = plACards(stapel) +: helpAstack1
    plACards = plACards.take(stapel) ++ plACards.drop(stapel + 1)
    this
  }

  def ablegen2A(stapel: Int): Game={
    helpAstack2 = plACards(stapel) +: helpAstack1
    plACards = plACards.take(stapel) ++ plACards.drop(stapel + 1)
    this
  }

  def ablegen3A(stapel: Int): Game={
    helpAstack3 = plACards(stapel) +: helpAstack1
    plACards = plACards.take(stapel) ++ plACards.drop(stapel + 1)
    this
  }

  def ablegen4A(stapel: Int): Game={
    helpAstack4 = plACards(stapel) +: helpAstack1
    plACards = plACards.take(stapel) ++ plACards.drop(stapel + 1)
    this
  }

  //legt Karte vom Spielerstapel auf Ablegestapel ab
  def pushCardStapel1A(): Game = {
    stack1 = plAstack.head +: stack1
    plAstack = plAstack.drop(1)
    this
  }

  def pushCardStapel2A(): Game = {
    stack2 = plAstack.head +: stack2
    plAstack = plAstack.drop(1)
    this
  }

  def pushCardStapel3A(): Game = {
    stack3 = plAstack.head +: stack3
    plAstack = plAstack.drop(1)
    this
  }

  def pushCardStapel4A(): Game = {
    stack4 = plAstack.head +: stack4
    plAstack = plAstack.drop(1)
    this
  }

  //füllt Karten auf, so dass Spieler A wieder 5 Karten hat
  def pullA() : Game ={
    while(plACards.length<5){
      plACards += Card(cardsCovered.head.value)
      cardsCovered = cardsCovered.drop(1)
    }
    this
  }


  //legt Karte auf Ablegestapel von Spieler B
  def pushCard1B(int: Int): Game = {
        stack1 = plBCards(int) +: stack1
        plBCards = plBCards.take(int) ++ plBCards.drop(int + 1)
    this
  }

  def pushCard2B(int: Int): Game = {
        stack2 = plBCards(int) +: stack2
        plBCards = plBCards.take(int) ++ plBCards.drop(int + 1)
    this
  }

  def pushCard3B(int: Int): Game = {
        stack3 = plBCards(int) +: stack3
        plBCards = plBCards.take(int) ++ plBCards.drop(int + 1)
    this
  }

  def pushCard4B(int: Int): Game = {
        stack4 = plBCards(int) +: stack4
        plBCards = plBCards.take(int) ++ plBCards.drop(int + 1)
    this
  }

  //legt Karte auf Hilfsstapel von Spieler B
  def ablegen1B(stapel: Int): Game={
    helpBstack1 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel) ++ plBCards.drop(stapel + 1)
    this
  }

  def ablegen2B(stapel: Int): Game={
    helpBstack2 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel) ++ plBCards.drop(stapel + 1)
    this
  }

  def ablegen3B(stapel: Int): Game={
    helpBstack3 = plBCards(stapel) +: helpBstack1
    plACards = plACards.take(stapel) ++ plACards.drop(stapel + 1)
    this
  }

  def ablegen4B(stapel: Int): Game={
    helpBstack4 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel) ++ plBCards.drop(stapel + 1)
    this
  }

  //füllt Karten auf, so dass Spieler A wieder 5 Karten hat
  def pullB() : Game ={
    while(plBCards.length<5){
      plBCards += Card(cardsCovered.head.value)
      cardsCovered = cardsCovered.drop(1)
    }
    this
  }

  //check Karte ob erlaubt zu legen
  def checkCardHand(i: Int): Boolean={
    if(plACards(i).toString != "J") {
      if (((plACards(i).toString.toInt) - 1 == stack1.head.toString.toInt) || ((plACards(i).toString.toInt) - 1 == stack2.head.toString.toInt)
        || ((plACards(i).toString.toInt) - 1 == stack3.head.toString.toInt) || ((plACards(i).toString.toInt) - 1 == stack4.head.toString.toInt)) {
        return true;
      }
    }
    if(plACards(i).toString == "J") {
     return true
    }
    false
  }

  override def toString: String = {

    val l = for (i <- 1 to plACards.length) yield
        ("| " + plACards(i - 1).toString + " | ")
    val b = ("| " + helpAstack1.head.toString + " | ")
    val c = ("| " + helpAstack2.head.toString + " | ")
    val d = ("| " + helpAstack3.head.toString + " | ")
    val e = ("| " + helpAstack4.head.toString + " | ")
    val f = ("| " + plAstack.head.toString + " | ")
    val g = ("| " + stack1.head.toString + " | ")
    val h = ("| " + stack2.head.toString + " | ")
    val j = ("| " + stack3.head.toString + " | ")
    val k = ("| " + stack4.head.toString + " | ")

    val playField = l + "\n" + b + "\t" + c + "\t" + d + "\t" + e + "\t" + f + "\n\n" + g + "\t" +
      h + "\t" + j + "\t" + k + "\t"

    playField
  }
}
