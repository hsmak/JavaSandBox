package command;

import command.device.ElectronicDevice;
import command.device.Television;

/**
 * Created by hsmak on 4/13/15.
 */
public class TVRemote {

    public static ElectronicDevice getDevice(){

        return new Television();

    }

}