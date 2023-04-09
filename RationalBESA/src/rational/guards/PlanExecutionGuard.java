package rational.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import java.util.Iterator;
import rational.RationalState;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 *
 * @author Andres
 */
public class PlanExecutionGuard extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        RationalState rst = (RationalState) this.getAgent().getState();
        if (rst.getMainRole() != null) {
            Plan plan = rst.getMainRole().getRolePlan();
            //ReportBESA.debug(plan.getTasks());
            Iterator<Task> it1 = plan.getTasksInExecution().iterator();
            while (it1.hasNext()) {
                Task task = it1.next();
                if (task.isFinalized()) {
                    Iterator<Task> it2 = plan.getGraphPlan().get(task).iterator();
                    while (it2.hasNext()) {
                        Task nextTask = it2.next();
                        boolean canExecute = true;
                        Iterator<Task> it3 = plan.getDependencyGraph().get(nextTask).iterator();
                        while (it3.hasNext()) {
                            Task dependencyTask = it3.next();
                            ReportBESA.debug(dependencyTask.toString());
                            if (!dependencyTask.isFinalized()) {
                                canExecute = false;
                                break;
                            }
                        }
                        if (canExecute) {
                            plan.getTasksWaitingForExecution().add(nextTask);
                        }
                    }
                    it1.remove();
                } else {
                    task.run(rst.getBelieves());
                }
            }

            for (Iterator<Task> iterator = plan.getTasksWaitingForExecution().iterator(); iterator.hasNext();) {
                Task next = iterator.next();
                next.run(rst.getBelieves());
                plan.getTasksInExecution().add(next);
                iterator.remove();
            }
        }
    }
}
