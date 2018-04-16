package edu.ap.spring.controllers;

import edu.ap.spring.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.ap.spring.model.InhaalExamen;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.Date;

@Controller
public class ExamenController {

    private RedisService service;

    @Autowired
    public void setRedisService(RedisService service) {
        this.service = service;
    }

    @RequestMapping("/student")
    @ResponseBody
    public String student(){return "student";}

    @RequestMapping("/examen")
    @ResponseBody
    public String grade() {return "examen";}

    @RequestMapping("/reden")
    @ResponseBody
    public String reden(){return "reden";}

    @RequestMapping("/datum")
    @ResponseBody
    public Date datum(){return datum() ;}
}
