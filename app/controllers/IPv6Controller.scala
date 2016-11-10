package controllers

import play.api.mvc.{Action, Controller}

class IPv6Controller extends Controller {
  def ok = Action { request =>
    request.headers.get("X-Forwarded-For") match {
      case Some(ipv6) if ipv6 contains ':' => Ok("ipv6")
      case Some(ipv4) => Ok("ipv4")
      case None => Ok("unknown")
    }
  }
}