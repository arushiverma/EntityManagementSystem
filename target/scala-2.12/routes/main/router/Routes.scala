
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Arushi/workspace/EntityManagementSystem/conf/routes
// @DATE:Thu Feb 22 22:48:45 IST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_1: com.target.entity.controller.HomeController,
  // @LINE:7
  EntityController_2: com.target.entity.controller.EntityController,
  // @LINE:15
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_1: com.target.entity.controller.HomeController,
    // @LINE:7
    EntityController_2: com.target.entity.controller.EntityController,
    // @LINE:15
    Assets_0: controllers.Assets
  ) = this(errorHandler, HomeController_1, EntityController_2, Assets_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, EntityController_2, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """com.target.entity.controller.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """entityservice/""" + "$" + """entity<[^/]+>/find/""" + "$" + """id<[^/]+>""", """com.target.entity.controller.EntityController.find(entity:String, id:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """entityservice/""" + "$" + """entity<[^/]+>/update/""" + "$" + """id<[^/]+>""", """com.target.entity.controller.EntityController.update(entity:String, id:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """entityservice/""" + "$" + """entity<[^/]+>/delete/""" + "$" + """id<[^/]+>""", """com.target.entity.controller.EntityController.delete(entity:String, id:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """entityservice/""" + "$" + """entity<[^/]+>/create""", """com.target.entity.controller.EntityController.create(entity:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val com_target_entity_controller_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val com_target_entity_controller_HomeController_index0_invoker = createInvoker(
    HomeController_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "com.target.entity.controller.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val com_target_entity_controller_EntityController_find1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("entityservice/"), DynamicPart("entity", """[^/]+""",true), StaticPart("/find/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val com_target_entity_controller_EntityController_find1_invoker = createInvoker(
    EntityController_2.find(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "com.target.entity.controller.EntityController",
      "find",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """entityservice/""" + "$" + """entity<[^/]+>/find/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val com_target_entity_controller_EntityController_update2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("entityservice/"), DynamicPart("entity", """[^/]+""",true), StaticPart("/update/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val com_target_entity_controller_EntityController_update2_invoker = createInvoker(
    EntityController_2.update(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "com.target.entity.controller.EntityController",
      "update",
      Seq(classOf[String], classOf[String]),
      "POST",
      this.prefix + """entityservice/""" + "$" + """entity<[^/]+>/update/""" + "$" + """id<[^/]+>""",
      """POST    /entityservice/:entity/find        com.target.entity.controller.EntityController.find(entity)""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val com_target_entity_controller_EntityController_delete3_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("entityservice/"), DynamicPart("entity", """[^/]+""",true), StaticPart("/delete/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val com_target_entity_controller_EntityController_delete3_invoker = createInvoker(
    EntityController_2.delete(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "com.target.entity.controller.EntityController",
      "delete",
      Seq(classOf[String], classOf[String]),
      "DELETE",
      this.prefix + """entityservice/""" + "$" + """entity<[^/]+>/delete/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val com_target_entity_controller_EntityController_create4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("entityservice/"), DynamicPart("entity", """[^/]+""",true), StaticPart("/create")))
  )
  private[this] lazy val com_target_entity_controller_EntityController_create4_invoker = createInvoker(
    EntityController_2.create(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "com.target.entity.controller.EntityController",
      "create",
      Seq(classOf[String]),
      "POST",
      this.prefix + """entityservice/""" + "$" + """entity<[^/]+>/create""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_Assets_versioned5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned5_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case com_target_entity_controller_HomeController_index0_route(params@_) =>
      call { 
        com_target_entity_controller_HomeController_index0_invoker.call(HomeController_1.index)
      }
  
    // @LINE:7
    case com_target_entity_controller_EntityController_find1_route(params@_) =>
      call(params.fromPath[String]("entity", None), params.fromPath[String]("id", None)) { (entity, id) =>
        com_target_entity_controller_EntityController_find1_invoker.call(EntityController_2.find(entity, id))
      }
  
    // @LINE:9
    case com_target_entity_controller_EntityController_update2_route(params@_) =>
      call(params.fromPath[String]("entity", None), params.fromPath[String]("id", None)) { (entity, id) =>
        com_target_entity_controller_EntityController_update2_invoker.call(EntityController_2.update(entity, id))
      }
  
    // @LINE:10
    case com_target_entity_controller_EntityController_delete3_route(params@_) =>
      call(params.fromPath[String]("entity", None), params.fromPath[String]("id", None)) { (entity, id) =>
        com_target_entity_controller_EntityController_delete3_invoker.call(EntityController_2.delete(entity, id))
      }
  
    // @LINE:11
    case com_target_entity_controller_EntityController_create4_route(params@_) =>
      call(params.fromPath[String]("entity", None)) { (entity) =>
        com_target_entity_controller_EntityController_create4_invoker.call(EntityController_2.create(entity))
      }
  
    // @LINE:15
    case controllers_Assets_versioned5_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned5_invoker.call(Assets_0.versioned(path, file))
      }
  }
}
