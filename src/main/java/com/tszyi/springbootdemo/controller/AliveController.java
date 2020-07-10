package com.tszyi.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AliveController {

  @GetMapping("/alive")
  public String sayHello(){
    return "I am tim huang, a handsome guy.";
  }
}
