package com.susu.dao;

import com.susu.entity.Announcement;

public interface AnnouncementMapper {

    int insert(Announcement announcement);

    Announcement showAnnouncement();
}
