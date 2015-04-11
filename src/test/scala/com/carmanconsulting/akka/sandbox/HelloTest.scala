package com.carmanconsulting.akka.sandbox

import org.junit.Test
import akka.actor.{ActorRef, Props}
import scala.concurrent.duration._
import scala.language.postfixOps

class HelloTest extends AkkaTestCase {

  @Test
  def testHello(): Unit = {
    val hello = system.actorOf(Props[HelloActor], "hello")
    hello ! "Akka"
    expectMsg("Hello, Akka!")
  }

  @Test
  def testHelloWithNullSender(): Unit = {
    val hello = system.actorOf(Props[HelloActor], "hello")
    hello.tell("Akka", ActorRef.noSender)
    expectNoMsg(2 seconds)
  }


}
