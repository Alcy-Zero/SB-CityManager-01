package com.hand.demo.api.controller.v0;

import com.hand.demo.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0")
public class AuthorController {

    @Autowired
    private Author author;

    //读取配置文件中属性
    @Value("${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;

    @RequestMapping("/books")
    String getBook() {
        return "Hello Spring Boot!<br/>"
                + "bookAuthor:" + bookAuthor + "<br/>"
                + "bookName:" + bookName + "<br/>";
    }

    @RequestMapping("/authors")
    String getAuthor() {
        return "AuthorName:" + author.getName() + "<br/>"
                + "AuthorAge:" + author.getAge() + "<br/>";
    }
}
