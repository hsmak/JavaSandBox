package command.command;

/**
 * Created by hsmak on 4/13/15.
 */
// Each command you want to issue will implement
// the Command interface

public interface ICommand {

    public void execute();

    // You may want to offer the option to undo a command

    public void undo();

}