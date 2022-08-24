package com.knight.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Slf4j
class WebSingleApplicationTest {

    @Test
    void main() {
        log.trace("### log trace");
        log.debug("### log debug");
        log.info("### log info");
        log.warn("### log warn");
        log.error("### log error");
    }
}