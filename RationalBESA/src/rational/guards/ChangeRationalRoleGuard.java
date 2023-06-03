package rational.guards;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import java.util.Iterator;
import rational.RationalRole;
import rational.RationalState;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 * A class representing a guard that handles the change of a rational agent's
 * role. This class extends GuardBESA.
 */
public class ChangeRationalRoleGuard extends GuardBESA {

    /**
     * Executes the guard function when a change rational role event is
     * received.
     *
     * @param ebesa The event containing the new role and related data.
     */
    @Override
    public void funcExecGuard(EventBESA ebesa) {
        RationalState state = (RationalState) this.getAgent().getState();
        RationalRole newrole = (RationalRole) ebesa.getData();
        //ReportBESA.debug("🟢 MainRole " + state.getMainRole() + " - Trying to change to rol 🟩 " + newrole.getRoleName());

        if (state.getMainRole() != null && !state.getMainRole().getRoleName().equals(((RationalRole) ebesa.getData()).getRoleName())) {
            if (state.getMainRole() != null) {
                Plan plan = state.getMainRole().getRolePlan();
                if (plan != null) {
                    Iterator<Task> it = plan.getTasksInExecution().iterator();
                    while (it.hasNext()) {
                        Task task = it.next();
                        if (task.isInExecution()) {
                            //ReportBESA.warn("Tarea en ejecución: " + task.toString());
                            //task.cancelTask(state.getBelieves());
                            while (task.isInExecution()) {
                                try {
                                    Thread.sleep(50); // espera medio segundo antes de verificar de nuevo
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    Thread.currentThread().interrupt();
                                }
                            }
                            it.remove();
                        } else if (task.isFinalized()) {
                            it.remove();
                        }
                        task.setTaskFinalized();
                    }
                }
            }
            newrole.resetPlan();
            state.setMainRole(newrole);
        } else if (state.getMainRole() == null) {
            newrole.resetPlan();
            state.setMainRole(newrole);
        } else if (!state.getMainRole().getRolePlan().inExecution()) {
            state.getMainRole().getRolePlan().reset();
        }
    }

}
