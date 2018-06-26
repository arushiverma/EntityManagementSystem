
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Arushi/workspace/EntityManagementSystem/conf/routes
// @DATE:Thu Feb 22 22:48:45 IST 2018

package com.target.entity.controller;

import router.RoutesPrefix;

public class routes {
  
  public static final com.target.entity.controller.ReverseHomeController HomeController = new com.target.entity.controller.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final com.target.entity.controller.ReverseEntityController EntityController = new com.target.entity.controller.ReverseEntityController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final com.target.entity.controller.javascript.ReverseHomeController HomeController = new com.target.entity.controller.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final com.target.entity.controller.javascript.ReverseEntityController EntityController = new com.target.entity.controller.javascript.ReverseEntityController(RoutesPrefix.byNamePrefix());
  }

}
