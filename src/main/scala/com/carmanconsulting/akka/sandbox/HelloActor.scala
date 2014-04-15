package com.carmanconsulting.akka.sandbox


class HelloActor extends LifecycleLogging {
  override def receive: Receive = {
    case msg => sender() ! "Hello, " + msg + "!"
  }
}
