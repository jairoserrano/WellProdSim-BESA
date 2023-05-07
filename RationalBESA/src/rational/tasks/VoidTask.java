package rational.tasks;

import rational.mapping.Believes;
import rational.mapping.Task;

/**
 * A class representing a void task that extends the Task class. This task does
 * nothing and is always considered finished.
 */
public class VoidTask extends Task {

    /**
     * Executes the void task. This task does nothing and is immediately set to
     * finalized.
     *
     * @param parameters The believes used as input for the task execution.
     */
    @Override
    public void executeTask(Believes parameters) {
        this.setTaskFinalized();
    }

    /**
     * Interrupts the void task. This method is empty as there is nothing to
     * interrupt.
     *
     * @param believes The believes used for task interruption.
     */
    @Override
    public void interruptTask(Believes believes) {
    }

    /**
     * Cancels the void task. This method is empty as there is nothing to
     * cancel.
     *
     * @param believes The believes used for task cancellation.
     */
    @Override
    public void cancelTask(Believes believes) {
    }

    /**
     * Checks if the void task is finished. This task is always considered
     * finished.
     *
     * @param believes The believes used to determine if the task is finished.
     * @return true Always returns true, as this task is always considered
     * finished.
     */
    @Override
    public boolean checkFinish(Believes believes) {
        return true;
    }

}
