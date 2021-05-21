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
  stack1 += Card(Value.One)
  var stack2 = new ListBuffer[Card]()
  stack2 += Card(Value.One)
  var stack3 = new ListBuffer[Card]()
  stack3 += Card(Value.One)
  var stack4 = new ListBuffer[Card]()
  stack4 += Card(Value.One)
  //"Hilfsstapel" des Spielers A
  var helpAstack1 = new ListBuffer[Card]()
  helpAstack1 += Card(Value.One)
  var helpAstack2 = new ListBuffer[Card]()
  helpAstack2 += Card(Value.One)
  var helpAstack3 = new ListBuffer[Card]()
  helpAstack3 += Card(Value.One)
  var helpAstack4 = new ListBuffer[Card]()
  helpAstack4 += Card(Value.One)
  //"Hilfsstapel" des Spielers B
  var helpBstack1 = new ListBuffer[Card]()
  helpBstack1 += Card(Value.One)
  var helpBstack2 = new ListBuffer[Card]()
  helpBstack2 += Card(Value.One)
  var helpBstack3 = new ListBuffer[Card]()
  helpBstack3 += Card(Value.One)
  var helpBstack4 = new ListBuffer[Card]()
  helpBstack4 += Card(Value.One)
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
  def pushCard1A(int: Int): Game = {
        stack1 = plACards(int) +: stack1
        plACards = plACards.take(int - 1) ++ plACards.drop(int)
    this
  }

  def pushCard2A(int: Int): Game = {
        stack2 = plACards(int) +: stack2
        plACards = plACards.take(int - 1) ++ plACards.drop(int)
    this
  }

  def pushCard3A(card: Card): Game = {
    for (t <- 1 to plACards.length)
      if (plACards(t - 1) == (card.value)|| helpAstack1.head ==(card.value )|| helpAstack2.head ==(card.value )||
        helpAstack3.head ==(card.value)|| helpAstack4.head ==(card.value ) || plAstack.head == (card.value )) {
        stack3 = plACards(t - 1) +: stack3
        plACards = plACards.take(t - 1) ++ plACards.drop(t)
      }
    this
  }

  def pushCard4A(card: Card): Game = {
    for (t <- 1 to plACards.length)
      if (plACards(t - 1) == (card.value )|| helpAstack1.head ==(card.value )|| helpAstack2.head ==(card.value )||
        helpAstack3.head ==(card.value)|| helpAstack4.head ==(card.value )|| plAstack.head == (card.value )) {
        stack4 = plACards(t - 1) +: stack4
        plACards = plACards.take(t - 1) ++ plACards.drop(t)
      }
    this
  }

  //legt Karte auf Hilfsstapel von Spieler A

  def ablegen1A(stapel: Int): Game={
    helpAstack1 = plACards(stapel) +: helpAstack1
    plACards = plACards.take(stapel-1) ++ plACards.drop(stapel)
    this
  }

  def ablegen2A(stapel: Int): Game={
    helpAstack2 = plACards(stapel) +: helpAstack1
    plACards = plACards.take(stapel-1) ++ plACards.drop(stapel)
    this
  }

  def ablegen3A(stapel: Int): Game={
    helpAstack3 = plACards(stapel) +: helpAstack1
    plACards = plACards.take(stapel-1) ++ plACards.drop(stapel)
    this
  }

  def ablegen4A(stapel: Int): Game={
    helpAstack4 = plACards(stapel) +: helpAstack1
    plACards = plACards.take(stapel-1) ++ plACards.drop(stapel)
    this
  }

  //füllt Karten auf, so dass Spieler A wieder 5 Karten hat
  def pullA() : Game ={
    while(plACards.length<=5){
      plACards += Card(cardsCovered.head.value)
      cardsCovered = cardsCovered.drop(1)
    }
    this
  }


  //legt Karte auf Ablegestapel von Spieler B
  def pushCard1B(card: Card): Game = {
    for (t <- 1 to plBCards.length)
      if (plBCards(t - 1) == (card.value)|| helpBstack1.head ==(card.value)|| helpBstack2.head ==(card.value)||
        helpBstack3.head ==(card.value)|| helpBstack4.head ==(card.value )|| plBstack.head == (card.value)) {
        stack1 = plBCards(t - 1) +: stack1
        plBCards = plBCards.take(t - 1) ++ plBCards.drop(t)
      }
    this
  }

  def pushCard2B(card: Card): Game = {
    for (t <- 1 to plBCards.length)
      if (plBCards(t - 1) == (card.value )|| helpBstack1.head ==(card.value )|| helpBstack2.head ==(card.value)||
        helpBstack3.head ==(card.value )|| helpBstack4.head ==(card.value )|| plBstack.head == (card.value )) {
        stack2 = plBCards(t - 1) +: stack2
        plBCards = plBCards.take(t - 1) ++ plBCards.drop(t)
      }
    this
  }

  def pushCard3B(card: Card): Game = {
    for (t <- 1 to plBCards.length)
      if (plBCards(t - 1) == (card.value )|| helpBstack1.head ==(card.value )|| helpBstack2.head ==(card.value )||
        helpBstack3.head ==(card.value )|| helpBstack4.head ==(card.value) || plBstack.head == (card.value )) {
        stack3 = plBCards(t - 1) +: stack3
        plBCards = plBCards.take(t - 1) ++ plBCards.drop(t)
      }
    this
  }

  def pushCard4B(card: Card): Game = {
    for (t <- 1 to plBCards.length)
      if (plBCards(t - 1) == (card.value)|| helpBstack1.head ==(card.value)|| helpBstack2.head ==(card.value)||
        helpBstack3.head ==(card.value)|| helpBstack4.head ==(card.value )|| plBstack.head == (card.value)) {
        stack4 = plBCards(t - 1) +: stack4
        plBCards = plBCards.take(t - 1) ++ plBCards.drop(t)
      }
    this
  }

  //legt Karte auf Hilfsstapel von Spieler B

  def ablegen1B(stapel: Int): Game={
    helpBstack1 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel-1) ++ plBCards.drop(stapel)
    this
  }

  def ablegen2B(stapel: Int): Game={
    helpBstack2 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel-1) ++ plBCards.drop(stapel)
    this
  }

  def ablegen3B(stapel: Int): Game={
    helpBstack3 = plBCards(stapel) +: helpBstack1
    plACards = plACards.take(stapel-1) ++ plACards.drop(stapel)
    this
  }

  def ablegen4B(stapel: Int): Game={
    helpBstack4 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel-1) ++ plBCards.drop(stapel)
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

  def getCardA (s: String): Card = {
    var c=0
    for(j<- 1 to plACards.length){
      if(s.equals(plACards(j-1).toString)){
        c = j-1
      }
    }
    plACards(c)
  }

  def getCardB (s: String): Card = {
    var c=0
    for(j<- 1 to plBCards.length){
      if(s.equals(plBCards(j-1).toString)){
        c = j-1
      }
    }
    plBCards(c)
  }

  override def toString: String = {
    var a, b, c, d, e, f, g, h, j, k = " "
    for (i <- 1 to plACards.length) {
       a = ("| " + plACards(i - 1).toString + " | ")
    }
    b = ("| " + helpAstack1.head.toString + " | ")
    c = ("| " + helpAstack2.head.toString + " | ")
    d = ("| " + helpAstack3.head.toString + " | ")
    e = ("| " + helpAstack4.head.toString + " | ")
    f = ("| " + plAstack.head.toString + " | ")
    g = ("| " + stack1.head.toString + " | ")
    h = ("| " + stack2.head.toString + " | ")
    j = ("| " + stack3.head.toString + " | ")
    k = ("| " + stack4.head.toString + " | ")

    val playField = a + "\n" + b + "\t" + c + "\t" + d + "\t" + e + "\t" + f + "\n\n" + g + "\t" +
      h + "\t" + j + "\t" + k + "\t"

    playField
  }
}
