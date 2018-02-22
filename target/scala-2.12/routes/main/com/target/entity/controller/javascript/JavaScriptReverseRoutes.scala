
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Arushi/workspace/EntityManagementSystem/conf/routes
// @DATE:Thu Feb 22 22:48:45 IST 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package com.target.entity.controller.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.target.entity.controller.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:7
  class ReverseEntityController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.target.entity.controller.EntityController.delete",
      """
        function(entity0,id1) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "entityservice/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("entity", entity0)) + "/delete/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("id", id1))})
        }
      """
    )
  
    // @LINE:7
    def find: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.target.entity.controller.EntityController.find",
      """
        function(entity0,id1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "entityservice/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("entity", entity0)) + "/find/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("id", id1))})
        }
      """
    )
  
    // @LINE:9
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.target.entity.controller.EntityController.update",
      """
        function(entity0,id1) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "entityservice/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("entity", entity0)) + "/update/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("id", id1))})
        }
      """
    )
  
    // @LINE:11
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.target.entity.controller.EntityController.create",
      """
        function(entity0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "entityservice/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("entity", entity0)) + "/create"})
        }
      """
    )
  
  }


}
