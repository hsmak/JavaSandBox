package command.command;

/**
 * Created by hsmak on 4/13/15.
 */
import command.device.ElectronicDevice;

import java.util.List;

public class TurnItAllOff implements ICommand {
    List<ElectronicDevice> theDevices;

    public TurnItAllOff(List<ElectronicDevice> newDevices) {
        theDevices = newDevices;
    }

    public void execute() {

        for (ElectronicDevice device : theDevices) {
            device.off();
        }

    }

    public void undo() {

        for (ElectronicDevice device : theDevices) {
            device.on();
        }

    }
}