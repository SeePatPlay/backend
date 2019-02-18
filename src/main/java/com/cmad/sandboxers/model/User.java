package com.cmad.sandboxers.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;

@XmlRootElement
public class User {
	@Id
	private String id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	private List<String> devices = new ArrayList<>();

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the devices
     */
    public List<String> getDevices() {
        return devices;
    }

    /**
     * @param devices the devices to set
     */
    public void setDevices(List<String> devices) {
        this.devices = devices;
    }


}