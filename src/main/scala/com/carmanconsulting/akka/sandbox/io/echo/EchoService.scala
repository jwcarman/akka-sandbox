package com.carmanconsulting.akka.sandbox.io.echo

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, Props}
import akka.io.{IO, Tcp}

object EchoService {
  def props(endpoint: InetSocketAddress): Props =
    Props(new EchoService(endpoint))
}

class EchoService(endpoint: InetSocketAddress) extends Actor with ActorLogging {

  import context.system

  IO(Tcp) ! Tcp.Bind(self, endpoint)

  override def receive: Receive = {
    case Tcp.Connected(remote, _) =>
      log.debug("Remote address {} connected", remote)
      sender ! Tcp.Register(context.actorOf(EchoConnectionHandler.props(remote, sender)))
  }
}