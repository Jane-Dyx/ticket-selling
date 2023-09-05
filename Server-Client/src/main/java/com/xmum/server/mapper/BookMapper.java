package com.xmum.server.mapper;

import com.xmum.server.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper//指定这是一个操作数据库的mapper
@Repository
public interface BookMapper {
    //增加,修改，删除图书，都返回int，用于判断是否增加成功等
    /*
    public int addBook(Book book);
    //删除图书
    public int delBook(int id);
    //修改图书
    public int updateBook(Book book);
    */
    //查询图书
    public Book queryBook(int id);
}