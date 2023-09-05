package com.xmum.server.service.impl;

import com.xmum.server.config.ExecutorConfig;
import com.xmum.server.entity.Book;
import com.xmum.server.mapper.BookMapper;
import com.xmum.server.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book queryBook(int id) {
        Book book = bookMapper.queryBook(id);
        return book;
    }


    @Async("asyncServiceExecutor")
    // 注：@Async所修饰的函数不能定义为static类型，这样异步调用不会生效
    public synchronized void  asyncTest() throws InterruptedException {
        logger.info("任务开始！");

        System.out.println("异步执行某耗时的事...");
        System.out.println("如休眠5秒");
        Thread.sleep(5000);

        logger.info("任务结束！");
    }
}