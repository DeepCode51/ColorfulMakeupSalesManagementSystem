package com.susu.service.impl;

import com.susu.dao.AnnouncementMapper;
import com.susu.entity.Announcement;
import com.susu.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;


    @Override
    public void addAnnouncement(Announcement announcement) {
        announcementMapper.insert(announcement);
    }

    @Override
    public Announcement showAnnouncement() {
        Announcement announcement = announcementMapper.showAnnouncement();
        System.out.println("service 查看公告"+announcement.toString());
        return announcement;
    }
}
