package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.SendAlarmRequest;
import com.woonjin.blog.application.dto.response.SendAlarmResponse;
import com.woonjin.blog.domain.entity.Alarm;
import com.woonjin.blog.domain.entity.Alarm.MessageType;
import com.woonjin.blog.domain.entity.Alarm.Status;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.AlarmRepository;
import com.woonjin.blog.domain.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final IdentityAppService identityAppService;
    private final UserRepository userRepository;

    public AlarmService(
        AlarmRepository alarmRepository,
        IdentityAppService identityAppService,
        UserRepository userRepository
    ) {
        this.alarmRepository = alarmRepository;
        this.identityAppService = identityAppService;
        this.userRepository = userRepository;
    }

    //알람 전송
    @Transactional
    public SendAlarmResponse sendAlarmMessage(SendAlarmRequest sendAlarmRequest) {

        User sender = this.userRepository.findById(sendAlarmRequest.getSender_id());
        User receiver = this.userRepository.findById(sendAlarmRequest.getReceiver_id());
        MessageType messageType = sendAlarmRequest.getMessageType();
        String alarmMessage;

        if (messageType.toString() == "LikeBoard") {
            alarmMessage = sender.getBlog().getBlogName() + "님이 회원님의 게시글을 좋아합니다.";
        } else if (messageType.toString() == "WriteReply") {
            alarmMessage =
                sender.getBlog().getBlogName() + "님이 회원님의 게시글에 댓글을 남겼습니다. \"" + sendAlarmRequest.getMessage()
                    + "\"";
        } else if (messageType.toString() == "LikeReply") {
            alarmMessage = sender.getBlog().getBlogName() + "님이 회원님의 댓글을 좋아합니다.";
        } else if (messageType.toString() == "OnChatMessage") {
            alarmMessage =
                sender.getBlog().getBlogName() + "님으로부터 메세지가 왔습니다. \"" + sendAlarmRequest.getMessage() + "\"";
        } else if (messageType.toString() == "LikeChatMessage") {
            alarmMessage = sender.getBlog().getBlogName() + "님이 회원님의 메세지를 좋아합니다.";
        } else {
            alarmMessage = "";
        }

        Alarm sendAlarm = alarmRepository.save(
            Alarm.of(
                alarmMessage,
                sendAlarmRequest.getUrl(),
                sender,
                receiver,
                sendAlarmRequest.getMessageType(),
                Status.valueOf("UnRead")
            )
        );

        return SendAlarmResponse.of(
            sendAlarm.getMessage(),
            sendAlarm.getUrl(),
            sendAlarm.getSender(),
            sendAlarm.getReceiver(),
            sendAlarm.getAlarmDate()
        );
    }

    //알람 목록
    @Transactional(readOnly = true)
    public List<Alarm> showAlarmList() {
        User receiver = this.identityAppService.getAuthenticationUser();
        List<Alarm> alarmList = this.alarmRepository.findByReceiver(receiver);

        return alarmList;
    }

    //알림 확인 처리
    @Transactional
    public String checkAlarm() {
        User user = this.identityAppService.getAuthenticationUser();

        List<Alarm> alarm = this.alarmRepository.findByReceiver(user);

        for (int i = 0; i < alarm.size(); i++) {
            alarm.get(i).setStatus(Status.Read);
            this.alarmRepository.save(alarm.get(i));
        }
        return "Check Alarms";
    }
}
