package de.htwg.se.Skip_Bo.util

trait Observer {
  def update: Unit
  def error(throwable: Throwable): Unit
}

class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers: Unit = subscribers.foreach(o => o.update)

  def onError(throwable:Throwable): Unit = subscribers.foreach(o => o.error(throwable))
}


