package rational.mapping;

/**
 * Abstract Task class representing a task within a BDI rational agent.
 */
public abstract class Task {

    /**
     * Enumeration of possible task states.
     */
    protected enum STATE {
        WAITING_FOR_EXECUTION,
        IN_EXECUTION,
        FINALIZED;
    }

    /**
     * Current state of the task.
     */
    protected STATE taskState;

    /**
     * Task class constructor that initializes the task state to
     * WAITING_FOR_EXECUTION.
     */
    public Task() {
        this.taskState = STATE.WAITING_FOR_EXECUTION;
    }

    /**
     * Sets the task state to IN_EXECUTION.
     */
    public synchronized void setTaskInExecution() {
        this.taskState = STATE.IN_EXECUTION;
    }

    /**
     * Sets the task state to WAITING_FOR_EXECUTION.
     */
    public synchronized void setTaskWaitingForExecution() {
        this.taskState = STATE.WAITING_FOR_EXECUTION;
    }

    /**
     * Sets the task state to FINALIZED.
     */
    public synchronized void setTaskFinalized() {
        this.taskState = STATE.FINALIZED;
    }

    /**
     * Checks if the task state is IN_EXECUTION.
     *
     * @return True if the state is IN_EXECUTION, false otherwise.
     */
    public synchronized boolean isInExecution() {
        return this.taskState == STATE.IN_EXECUTION;
    }

    /**
     * Checks if the task state is WAITING_FOR_EXECUTION.
     *
     * @return True if the state is WAITING_FOR_EXECUTION, false otherwise.
     */
    public synchronized boolean isWaitingForExecution() {
        return this.taskState == STATE.WAITING_FOR_EXECUTION;
    }

    /**
     * Checks if the task state is FINALIZED.
     *
     * @return True if the state is FINALIZED, false otherwise.
     */
    public synchronized boolean isFinalized() {
        return this.taskState == STATE.FINALIZED;
    }

    /**
     * Runs the task if it is not in execution and has not finished.
     *
     * @param believes Agent beliefs.
     */
    public synchronized void run(Believes believes) {
        if (this.checkFinish(believes)) {
            this.setTaskFinalized();
        } else {
            if (!this.isInExecution()) {
                this.setTaskInExecution();
                this.executeTask(believes);
            }
        }
    }

    /**
     * Checks if the task has finished.
     *
     * @param believes Agent beliefs.
     * @return True if the task has finished, false otherwise.
     */
    public abstract boolean checkFinish(Believes believes);

    /**
     * Executes the task.
     *
     * @param parameters Task parameters.
     */
    public abstract void executeTask(Believes parameters);

    /**
     * Interrupts the task.
     *
     * @param believes Agent beliefs.
     */
    public abstract void interruptTask(Believes believes);

    /**
     * Cancels the task.
     *
     * @param believes Agent beliefs.
     */
    public abstract void cancelTask(Believes believes);
}
