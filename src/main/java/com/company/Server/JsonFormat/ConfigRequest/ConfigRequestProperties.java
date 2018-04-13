package com.company.Server.JsonFormat.ConfigRequest;

import lombok.Data;

// TODO: make it completly parrallel in the client and the server for the gson class
@Data
public class ConfigRequestProperties {
    private String userEmail;
    private String workerName;
    private ConfigRequestData data;
}