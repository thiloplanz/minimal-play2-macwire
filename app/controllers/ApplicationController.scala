package controllers

import play.api.mvc.{ControllerComponents, AbstractController}

class ApplicationController(cc: ControllerComponents) extends AbstractController(cc) {
  def index = Action {
    Ok("OK!")
  }
}
