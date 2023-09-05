package com.xmum.server.service;

import com.xmum.server.entity.Book;

public interface BookService {
    //复制mapper接口中的方法到这里
    public Book queryBook(int id);

    public void asyncTest() throws InterruptedException;
}