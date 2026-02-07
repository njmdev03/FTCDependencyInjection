package org.firstinspires.ftc.teamcode.lib.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Object to execute commands from a queue.
 *
 * This Object will only run/execute commands in queue when the run() method is called. This means
 * that the run() method should be called from your own loop. Doing this enables asynchronous
 * execution of Commands.
 *
 * This will run each Command by calling init() once after the previous command has finished, then
 * calling loop() until the Command returns true when isCompleted() is run.
 */
public class CommandScheduler {
    private final Queue<Command> commandQueue = new LinkedList<>();

    // Whether or not to init the command at the top of the queue
    private boolean initCommand = true;

    /**
     * Add a Command to the queue
     * @param command The command to queue
     */
    public void add(Command... command) {
        commandQueue.addAll(Arrays.asList(command));
    }

    /**
     * Add a list of commands to the queue
     * @param commands Commands to add
     */
    public void addAll(CommandList commands) {
        commandQueue.addAll(commands);
    }

    /**
     * Add a list of commands to the queue
     * @param commands Commands to add
     */
    public void addAll(Collection<Command> commands) {
        commandQueue.addAll(commands);
    }

    /**
     * Add a list of commands to the queue
     * @param commands Commands to add
     */
    public void addAll(Command[] commands) {
        commandQueue.addAll(Arrays.asList(commands));
    }

    /**
     * Update the queue of Commands. Each time this method is called the Command at the top of the
     * queue will have loop() called. If it is the first time that command is being called, first
     * init() will be called, then immediately after loop(). Finally after a call to loop(), the
     * isCompleted() method will be called. If it is true, the Command will be removed from queue
     * and the next call to run() will result in the next Command in queue being inited.
     */
    public void run() {
        if (commandQueue.peek() == null) {
            return;
        }

        if (initCommand) {
            commandQueue.peek().init();
            initCommand = false;
        }

        commandQueue.peek().loop();

        if (commandQueue.peek().isCompleted()) {
            commandQueue.remove();
            initCommand = true;
        }
    }

    /**
     * Check if the queue is empty.
     * @return true when the queue is empty.
     */
    public boolean isCompleted() {
        return commandQueue.isEmpty();
    }

}
