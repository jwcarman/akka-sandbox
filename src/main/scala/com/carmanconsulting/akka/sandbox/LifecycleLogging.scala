package com.carmanconsulting.akka.sandbox

import akka.actor.{ActorLogging, Actor}

trait LifecycleLogging extends Actor with ActorLogging {

  log.debug("{}()", getClass.getSimpleName)

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    log.debug("preRestart({}, {})", reason, message)
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
    log.debug("postRestart({})", reason)
    super.postRestart(reason)
  }

  override def postStop(): Unit = {
    log.debug("postStop()")
    super.postStop()
  }

  override def preStart(): Unit = {
    log.debug("preStart()")
    super.preStart()
  }
}
