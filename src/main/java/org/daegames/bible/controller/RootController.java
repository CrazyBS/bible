package org.daegames.bible.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Order(-1)
public class RootController {

    @RequestMapping("/")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public HttpHeaders getRoot() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/pages/index.html");
        return headers;
    }
}
