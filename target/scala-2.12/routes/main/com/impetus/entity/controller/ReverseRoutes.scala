
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Arushi/workspace/EntityManagementSystem/conf/routes
// @DATE:Thu Feb 22 22:48:45 IST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package com.target.entity.controller {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:7
  class ReverseEntityController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def delete(entity:String, id:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "entityservice/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("entity", entity)) + "/delete/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:7
    def find(entity:String, id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "entityservice/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("entity", entity)) + "/find/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:9
    def update(entity:String, id:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "entityservice/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("entity", entity)) + "/update/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:11
    def create(entity:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "entityservice/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("entity", entity)) + "/create")
    }
  
  }


}
