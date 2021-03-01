package com.xybl.server.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testToString() {
        Student student=new Student("111","张三");
        System.out.println(student.toString());
        System.out.println(student.getId());
    }
}