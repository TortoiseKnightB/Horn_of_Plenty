package com.knight.web;

import com.knight.web.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Slf4j
class WebSingleApplicationTest {


    @Test
    void main() {
        String s = TestController.class.toString();
        System.out.println(s);

    }
}