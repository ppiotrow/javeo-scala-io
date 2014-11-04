package ppiotrow.best_practices

import akka.actor.Actor

import collection.immutable.Set
import collection.mutable

/**
 * https://github.com/alexandru/scala-best-practices/blob/master/sections/5-actors.md#52-should-mutate-state-in-actors-only-with-contextbecome
 */
class MyActor extends Actor {
  val isInSet = mutable.Set.empty[String]

  def receive = {
    case Add(key) =>
      isInSet += key

    case Contains(key) =>
      sender() ! isInSet(key)
  }
}

class MyActor2 extends Actor {
  def receive = active(Set.empty)

  def active(isInSet: Set[String]): Receive = {
    case Add(key) =>
      context become active(isInSet + key)

    case Contains(key) =>
      sender() ! isInSet(key)
  }
}

// Messages
case class Add(key: String)
case class Contains(key: String)
