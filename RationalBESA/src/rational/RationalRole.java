package rational;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.List;
import rational.mapping.Plan;
import rational.mapping.Task;

/**
 * The RationalRole class represents a role that an agent can adopt in the
 * Rational Agent framework. It contains a role name and a plan associated with
 * that role.
 */
public class RationalRole extends DataBESA {

    private String roleName;
    private Plan rolePlan;

    /**
     * Constructs a RationalRole with the specified role name and plan.
     *
     * @param roleName The name of the role.
     * @param rolePlan The plan associated with the role.
     */
    public RationalRole(String roleName, Plan rolePlan) {
        this.roleName = roleName;
        this.rolePlan = rolePlan;
    }

    /**
     * Constructs a RationalRole with the specified role name and an empty plan.
     *
     * @param roleName The name of the role.
     */
    public RationalRole(String roleName) {
        this.roleName = roleName;
        this.rolePlan = new Plan();
    }

    /**
     * Returns the plan associated with this role.
     *
     * @return The plan associated with the role.
     */
    public Plan getRolePlan() {
        return rolePlan;
    }

    /**
     * Returns the name of this role.
     *
     * @return The name of the role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Adds a task to the role plan.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        rolePlan.addTask(task);
    }

    /**
     * Adds a task to the role plan with a list of previous tasks.
     *
     * @param task The task to be added.
     * @param prevTasks The list of previous tasks for the task.
     */
    public void addTask(Task task, List<Task> prevTasks) {
        rolePlan.addTask(task, prevTasks);
    }

    /**
     * Returns the role name as a string.
     *
     * @return The role name as a string.
     */
    @Override
    public String toString() {
        return this.roleName;
    }

    /**
     * Sets the role plan.
     *
     * @param rolePlan The plan to be associated with this role.
     */
    public void setRolePlan(Plan rolePlan) {
        this.rolePlan = rolePlan;
    }

    /**
     * Resets the role plan.
     */
    public void resetPlan() {
        this.rolePlan.reset();
    }
}
