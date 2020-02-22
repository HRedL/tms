package com.qdu.tms.service;

import com.qdu.tms.Bean.Manager;
import com.qdu.tms.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    ManagerMapper managerMapper;


    public Manager getManagerById(Integer id) {
        return managerMapper.getManagerById(id);
    }

    public List<Manager> getAllManagers() {
        return managerMapper.getAllManagers();
    }
}
