package com.susu.controller;

import com.susu.service.BgOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/bgorderitem")
public class BgOrderItemController {
    @Autowired
    private BgOrderItemService bgOrderItemService;
}
