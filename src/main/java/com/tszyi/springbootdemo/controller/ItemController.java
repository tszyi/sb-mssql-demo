package com.tszyi.springbootdemo.controller;

import com.tszyi.springbootdemo.exception.SqlOperationException;
import com.tszyi.springbootdemo.service.InventoryHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mssql-crud-example")
public class ItemController {
  private static final String USER = "sa";
  private static final String PW = "Password@1234";

  private InventoryHelper helper = new InventoryHelper(USER, PW);

  @PostMapping("/inventories/{id}")
  public void addItem(
      @RequestParam("ip") String ip,
      @RequestParam("port") int port,
      @PathVariable("id") int id,
      @RequestParam("name") String name,
      @RequestParam("quantity") int quantity) {
    try {
      helper.connect(ip, port);
      if (helper.isConnect()) {
        helper.insertItem(id, name, quantity);
        System.out.println("新增紀錄成功");
      }
    } catch (Exception e) {
      throw new SqlOperationException(e);
    } finally {
      helper.close();
    }
  }

  @DeleteMapping("/inventories/{id}")
  public void deleteItem(
      @RequestParam("ip") String ip, @RequestParam("port") int port, @PathVariable("id") int id) {
    try {
      helper.connect(ip, port);
      if (helper.isConnect()) {
        helper.removeItemById(id);
        System.out.println("刪除紀錄成功");
      }
    } catch (Exception e) {
      throw new SqlOperationException(e);
    } finally {
      helper.close();
    }
  }

  @PutMapping("/inventories/{id}")
  public void updateItem(
      @RequestParam("ip") String ip,
      @RequestParam("port") int port,
      @PathVariable("id") int id,
      @RequestParam("name") String name,
      @RequestParam("quantity") int quantity) {
    try {
      helper.connect(ip, port);
      if (helper.isConnect()) {
        helper.updateItemById(id, name, quantity);
        System.out.println("更新資料成功");
      }
    } catch (Exception e) {
      throw new SqlOperationException(e);
    } finally {
      helper.close();
    }
  }

  @GetMapping("/inventories")
  public String showAllItems(@RequestParam("ip") String ip, @RequestParam("port") int port) {
    String result = "";
    try {
      helper.connect(ip, port);
      if (helper.isConnect()) {
        result = helper.getItems();
        System.out.println("查詢資料成功，資料如下:");
        System.out.println(result);
      }
    } catch (Exception e) {
      throw new SqlOperationException(e);
    } finally {
      helper.close();
    }
    return result;
  }
}
