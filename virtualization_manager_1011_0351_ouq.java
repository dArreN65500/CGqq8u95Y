// 代码生成时间: 2025-10-11 03:51:24
package com.example.virtualization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

// VirtualizationManagerApplication is the main Spring Boot application class
@SpringBootApplication
public class VirtualizationManagerApplication {

    // Main method to run the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(VirtualizationManagerApplication.class, args);
    }
}

// VirtualizationManagerController is a REST controller that handles API requests
@RestController
class VirtualizationManagerController {

    // A map to simulate virtual machines
    private Map<String, String> virtualMachines = new HashMap<>();

    public VirtualizationManagerController() {
        // Initialize with some sample virtual machines
        virtualMachines.put("vm1", "Running");
        virtualMachines.put("vm2", "Stopped");
        virtualMachines.put("vm3", "Paused");
    }

    // GET endpoint to retrieve the state of all virtual machines
    @GetMapping("/virtual-machines")
    public Map<String, String> getAllVirtualMachines() {
        return virtualMachines;
    }

    // GET endpoint to retrieve the state of a specific virtual machine
    @GetMapping("/virtual-machines/{vmId}")
    public String getVirtualMachineState(@PathVariable String vmId) {
        if (!virtualMachines.containsKey(vmId)) {
            // Return an error message if the virtual machine does not exist
            return "Virtual machine not found";
        }
        return virtualMachines.get(vmId);
    }

    // POST endpoint to start a virtual machine
    @GetMapping("/virtual-machines/{vmId}/start")
    public String startVirtualMachine(@PathVariable String vmId) {
        if (!virtualMachines.containsKey(vmId)) {
            return "Virtual machine not found";
        }
        virtualMachines.put(vmId, "Running");
        return "Virtual machine started";
    }

    // POST endpoint to stop a virtual machine
    @GetMapping("/virtual-machines/{vmId}/stop")
    public String stopVirtualMachine(@PathVariable String vmId) {
        if (!virtualMachines.containsKey(vmId)) {
            return "Virtual machine not found";
        }
        virtualMachines.put(vmId, "Stopped");
        return "Virtual machine stopped";
    }

    // POST endpoint to pause a virtual machine
    @GetMapping("/virtual-machines/{vmId}/pause")
    public String pauseVirtualMachine(@PathVariable String vmId) {
        if (!virtualMachines.containsKey(vmId)) {
            return "Virtual machine not found";
        }
        virtualMachines.put(vmId, "Paused");
        return "Virtual machine paused";
    }
}
