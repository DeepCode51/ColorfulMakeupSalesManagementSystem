package com.susu.controller;


import com.susu.entity.Announcement;
import com.susu.entity.Msg;
import com.susu.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;

@RestController
@CrossOrigin
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    //添加公告
    @RequestMapping("/addAnnouncement")
    public Msg addAnnouncement(@RequestBody Announcement announcement){

        announcementService.addAnnouncement(announcement);

        return Msg.success().add("announcement",announcement);
    }

    @RequestMapping("/showAnnouncement")
    public Msg showAnnouncement(){

        Announcement announcement = announcementService.showAnnouncement();
        System.out.println("controller 查看公告"+announcement.toString());
        return Msg.success().add("announcement",announcement);
    }
}
