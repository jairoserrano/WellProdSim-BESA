package BESA.World.layer;

import BESA.World.automata.core.layer.LayerExecutionParams;

/**
 * Pojo implementation that holds the necessary parameters for the layer executions
 */
public class LayerFunctionParams implements LayerExecutionParams {
    private String date;

    public LayerFunctionParams(String date) {
        this.date = date;
    }

    public LayerFunctionParams() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
