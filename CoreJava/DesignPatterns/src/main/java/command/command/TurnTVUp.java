package command.command;

import command.device.ElectronicDevice;

/**
 * Created by hsmak on 4/13/15.
 */
public class TurnTVUp implements ICommand {

    ElectronicDevice theDevice;

    public TurnTVUp(ElectronicDevice newDevice){

        theDevice = newDevice;

    }

    public void execute() {

        theDevice.volumeUp();

    }

    public void undo() {

        theDevice.volumenDown();

    }

}