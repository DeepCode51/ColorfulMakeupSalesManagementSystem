package com.susu.service;

import com.susu.entity.Announcement;

public interface AnnouncementService {
    void addAnnouncement(Announcement announcement);

    Announcement showAnnouncement();
}
