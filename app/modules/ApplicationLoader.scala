package modules

import play.api.{Application, BuiltInComponentsFromContext, LoggerConfigurator}
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import router.Routes
import com.softwaremill.macwire._
import controllers.ApplicationController

/**
  * Application loader that wires up the application dependencies using Macwire
  */
class ApplicationLoader extends play.api.ApplicationLoader {
  def load(context: Context): Application = new ApplicationComponents(context).application
}

class ApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context)
  with play.filters.HttpFiltersComponents {

  // set up logger
  LoggerConfigurator(context.environment.classLoader).foreach {
    _.configure(context.environment, context.initialConfiguration, Map.empty)
  }

  lazy val applicationController = wire[ApplicationController]

  lazy val router: Router = {
    // add the prefix string in local scope for the Routes constructor
    val prefix: String = "/"
    wire[Routes]
  }
}
