package rational.mapping;

import java.util.*;
import java.util.concurrent.*;
import rational.tasks.VoidTask;

public class Plan {

    private final HashMap<Task, List<Task>> graphPlan;
    private final HashMap<Task, List<Task>> dependencyGraph;
    private List<Task> tasksInExecution;
    private List<Task> tasksWaitingForExecution;
    private final Task initial;
    private ExecutorService executorService;

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

        // Initialize executor service
        executorService = Executors.newCachedThreadPool();
    }

    public synchronized HashMap<Task, List<Task>> getGraphPlan() {
        return graphPlan;
    }

    public synchronized HashMap<Task, List<Task>> getDependencyGraph() {
        return dependencyGraph;
    }

    public synchronized List<Task> getTasksInExecution() {
        return tasksInExecution;
    }

    public synchronized List<Task> getTasksWaitingForExecution() {
        return tasksWaitingForExecution;
    }

    public synchronized void setTasksWaitingForExecution(List<Task> tasksWaitingForExecution) {
        this.tasksWaitingForExecution = tasksWaitingForExecution;
    }

    public synchronized Set<Task> getTasks() {
        return graphPlan.keySet();
    }

    public synchronized void addTask(Task task) {
        List<Task> l = new LinkedList<>();
        l.add(initial);
        addTask(task, l);
    }

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
        tasksWaitingForExecution.add(task);

        return true;
    }

    public synchronized boolean inExecution() {
        return !tasksInExecution.isEmpty();
    }

    public synchronized void reset() {
        tasksInExecution.clear();
        tasksWaitingForExecution.clear();
        for (Task task : this.graphPlan.keySet()) {
            task.setTaskWaitingForExecution();
        }
        tasksInExecution.add(initial);
    }

    public void executeTasks(Believes believes) throws InterruptedException {
        while (!tasksWaitingForExecution.isEmpty()) {
            Iterator<Task> taskIterator = tasksWaitingForExecution.iterator();
            while (taskIterator.hasNext()) {
                Task task = taskIterator.next();
                if (!task.isInExecution() && !task.isFinalized()) {
                    executorService.submit(() -> {
                        task.run(believes);
                        synchronized (this) {
                            tasksInExecution.add(task);
                            taskIterator.remove();
                        }
                    });
                }
            }
            Thread.sleep(1000); // Sleep for a while before next check
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }
}
