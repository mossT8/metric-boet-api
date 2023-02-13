package com.javatpoint.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ServerStatusController
{
    @RequestMapping("/")
    public String serverUp()
    {
        return "Server Up";
    }
}