package rational.mapping;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import rational.tasks.VoidTask;

/**
 * This class represents a plan, containing tasks and their dependencies.
 */
public class Plan {

    private final HashMap<Task, List<Task>> graphPlan;
    private final HashMap<Task, List<Task>> dependencyGraph;
    private List<Task> tasksInExecution;
    private List<Task> tasksWaitingForExecution;
    private final Task initial;

    /**
     * Constructs a new Plan object.
     */
    public Plan() {
        graphPlan = new HashMap<>();
        tasksInExecution = new LinkedList<>();
        tasksWaitingForExecution = new LinkedList<>();
        dependencyGraph = new HashMap<>();
        initial = new VoidTask();
        initial.setTaskFinalized();
        tasksInExecution.add(initial);
        graphPlan.put(initial, new LinkedList<>());
        dependencyGraph.put(initial, new LinkedList<>());
    }

    /**
     * Returns the graph plan.
     *
     * @return the graph plan
     */
    public synchronized HashMap<Task, List<Task>> getGraphPlan() {
        return graphPlan;
    }

    /**
     * Returns the dependency graph.
     *
     * @return the dependency graph
     */
    public synchronized HashMap<Task, List<Task>> getDependencyGraph() {
        return dependencyGraph;
    }

    /**
     * Returns the list of tasks in execution.
     *
     * @return the list of tasks in execution
     */
    public synchronized List<Task> getTasksInExecution() {
        return tasksInExecution;
    }

    /**
     * Returns the list of tasks waiting for execution.
     *
     * @return the list of tasks waiting for execution
     */
    public synchronized List<Task> getTasksWaitingForExecution() {
        return tasksWaitingForExecution;
    }

    /**
     * Sets the list of tasks waiting for execution.
     *
     * @param tasksWaitingForExecution the new list of tasks waiting for
     * execution
     */
    public synchronized void setTasksWaitingForExecution(List<Task> tasksWaitingForExecution) {
        this.tasksWaitingForExecution = tasksWaitingForExecution;
    }

    /**
     * Returns the set of tasks in the plan.
     *
     * @return the set of tasks in the plan
     */
    public synchronized Set<Task> getTasks() {
        return graphPlan.keySet();
    }

    /**
     * Adds a task to the plan with the initial task as the previous task.
     *
     * @param task the task to be added
     */
    public synchronized void addTask(Task task) {
        List<Task> l = new LinkedList<>();
        l.add(initial);
        addTask(task, l);
    }

    /**
     * Adds a task to the plan with the specified list of previous tasks.
     *
     * @param task the task to be added
     * @param previousTask the list of previous tasks
     * @return true if the task is added successfully, false otherwise
     */
    public synchronized boolean addTask(Task task, List<Task> previousTask) {
        for (Task prevTask : previousTask) {
            if (!graphPlan.containsKey(prevTask)) {
                return false;
            }
        }
        graphPlan.put(task, new LinkedList<>());
        if (!dependencyGraph.containsKey(task)) {
            dependencyGraph.put(task, new LinkedList<>());
        }

        dependencyGraph.get(task).addAll(previousTask);

        for (Task prevTask : previousTask) {
            graphPlan.get(prevTask).add(task);
        }

        return true;
    }

    /**
     * Checks if there are tasks in execution.
     *
     * @return true if there are tasks in execution, false otherwise
     */
    public synchronized boolean inExecution() {
        return !tasksInExecution.isEmpty();
    }

    /**
     * Resets the plan by marking all tasks as waiting for execution and
     * clearing the lists of tasks in execution and waiting for execution.
     */
    public synchronized void reset() {
        tasksInExecution = new LinkedList<>();
        tasksWaitingForExecution = new LinkedList<>();
        for (Task task : this.graphPlan.keySet()) {
            task.setTaskWaitingForExecution();
        }
        tasksInExecution.add(initial);
    }
}
