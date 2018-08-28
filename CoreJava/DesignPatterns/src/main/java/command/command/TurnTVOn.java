package command.command;

import command.device.ElectronicDevice;

/**
 * Created by hsmak on 4/13/15.
 */
public class TurnTVOn implements ICommand {

    ElectronicDevice theDevice;

    public TurnTVOn(ElectronicDevice newDevice){

        theDevice = newDevice;

    }

    public void execute() {

        theDevice.on();

    }

    public void undo() {

        theDevice.off();

    }

}