package com.carmanconsulting.akka.sandbox

import org.junit.Test
import akka.actor.Props
import akka.routing.RoundRobinPool

class HelloTest extends AkkaTestCase {

  @Test
  def testHello(): Unit = {
    val hello = system.actorOf(RoundRobinPool(5).props(Props[HelloActor]), "hello")
    hello ! "Akka"
    expectMsg("Hello, Akka!")
  }

}
