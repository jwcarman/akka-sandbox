package com.carmanconsulting.akka.sandbox.io.echo

import java.net.InetSocketAddress

import akka.actor.ActorSystem

object EchoServiceApp extends App {

  val system = ActorSystem("echo-service-system")
  val endpoint = new InetSocketAddress("localhost", 11111)
  system.actorOf(EchoService.props(endpoint), "echo-service")

  readLine(s"Hit ENTER to exit ...${System.getProperty("line.separator")}")
  system.shutdown()
}

