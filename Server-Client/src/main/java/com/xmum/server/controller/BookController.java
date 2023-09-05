package com.xmum.server.controller;

import com.xmum.server.entity.Book;
import com.xmum.server.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController//返回json字符串
@Slf4j//日志打印
public class BookController {

    @Autowired
    private BookService bookService;//这里注入接口，实际使用的是实现类
    //1.通过id获取book所有信息
    // http://localhost:8080/queryBook/1(此处1为要获取的id）
    @RequestMapping(value = "/queryBook/{id}",method = RequestMethod.GET)//使用restful风格传参数
    public Book queryBook(@PathVariable("id") Integer id){
        Book book = bookService.queryBook(id);
        log.info("查询结果：{}",book);
        return book;//@RestController使得返回的是book实体类的字符串形式
    }

    @GetMapping("/test")
    public String testAsync() throws InterruptedException {
        bookService.asyncTest();
        return "see log";
    }

}
