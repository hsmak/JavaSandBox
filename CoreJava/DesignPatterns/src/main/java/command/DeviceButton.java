package command;

import command.command.ICommand;

/**
 * Created by hsmak on 4/13/15.
 */
// This is known as the invoker
// It has a method press() that when executed
// causes the execute method to be called

// The execute method for the Command interface then calls
// the method assigned in the class that implements the
// Command interface

public class DeviceButton{

    ICommand theCommand;

    public DeviceButton(ICommand newCommand){

        theCommand = newCommand;

    }

    public void press(){

        theCommand.execute();

    }

    // Now the remote can undo past commands

    public void pressUndo(){

        theCommand.undo();

    }

}