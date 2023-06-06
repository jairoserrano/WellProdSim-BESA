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
            //ReportBESA.warn("rst.getMainRole() " + rst.getMainRole());
            Plan plan = rst.getMainRole().getRolePlan();
            Iterator<Task> it1 = plan.getTasksInExecution().iterator();
            while (it1.hasNext()) {
                Task task = it1.next();
                if (task.isFinalized()) {
                    for (Task nextTask : plan.getGraphPlan().get(task)) {
                        boolean canExecute = true;
                        for (Task dependencyTask : plan.getDependencyGraph().get(nextTask)) {
                            //ReportBESA.warn("rst.ge " + plan.getDependencyGraph().toString());
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
