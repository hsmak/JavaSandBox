package command.command;

import command.device.ElectronicDevice;

/**
 * Created by hsmak on 4/13/15.
 */
public class TurnTVOff implements ICommand {

    ElectronicDevice theDevice;

    public TurnTVOff(ElectronicDevice newDevice){

        theDevice = newDevice;

    }

    public void execute() {

        theDevice.off();

    }

    // Used if you want to allow for undo
    // Do the opposite of execute()

    public void undo() {

        theDevice.on();

    }

}