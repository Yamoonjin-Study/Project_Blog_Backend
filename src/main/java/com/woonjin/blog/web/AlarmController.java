package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.SendAlarmRequest;
import com.woonjin.blog.application.dto.response.SendAlarmResponse;
import com.woonjin.blog.application.service.AlarmService;
import com.woonjin.blog.domain.entity.Alarm;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"6. Alarm Service"})
public class AlarmController {

    private final AlarmService alarmService;

    public AlarmController(
        AlarmService alarmService
    ) {
        this.alarmService = alarmService;
    }

    @PostMapping("/send-alarm")
    public SendAlarmResponse sendAlarmMessage(@RequestBody SendAlarmRequest sendAlarmRequest) {
        System.out.println(sendAlarmRequest.getMessage());
        return this.alarmService.sendAlarmMessage(sendAlarmRequest);
    }

    @GetMapping("/show-alarm")
    public List<Alarm> showAlarmMessages() {
        return this.alarmService.showAlarmList();
    }

    @PutMapping("/check-alarm")
    public String checkAlarm() {
        return this.alarmService.checkAlarm();
    }
}
