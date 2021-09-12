package com.kubrafelek.homework05.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@Component
@SessionScope
public class ClientRequestInfo {

    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;
}
