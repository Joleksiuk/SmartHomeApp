package pl.smarthome.Models;

import lombok.Data;

import java.io.Serializable;


public class InstructionId implements Serializable {
    public Long deviceId;
    public Long sceneId;
}
