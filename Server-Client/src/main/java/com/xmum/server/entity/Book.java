package com.xmum.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book{
    private int id;
    private String name;
    private String author;
    //两个参数的构造方法
    public Book(String name,String author){
        this.name = name;
        this.author = author;
    }

}