package com.yujie.controller;

import com.yujie.model.Person;
import com.yujie.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository userRepository;

    @GetMapping("save")
    public String save() {
        Person userInfo = new Person(System.currentTimeMillis(),"李寻欢","男");
        userRepository.save(userInfo);
        return "success";
    }

    @GetMapping("getUserList")
    public List<Person> getUserList() {
        List<Person> userInfoList = userRepository.findAll();
        return userInfoList;
    }

    @GetMapping("delete")
    public String delete(Long id) {
        userRepository.delete(id);
        return "success";
    }

    @GetMapping("update")
    public String update(Long id, String username, String password) {
        Person userInfo = new Person(id, username, password);
        userRepository.save(userInfo);
        return "success";
    }
}
