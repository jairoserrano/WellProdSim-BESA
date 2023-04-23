package wpsWorld.automata.core.layer;

import java.util.HashMap;

/**
 * Generic world layer, abstract implementation of a generic world simulation layer
 */
public abstract class GenericWorldLayer implements WorldLayer {

    /**
     * Data structure that holds the dependant layers
     */
    protected HashMap<String, WorldLayer> dependantLayers;

    /**
     *
     */
    public GenericWorldLayer() {
        this.dependantLayers = new HashMap<>();
    }

    @Override
    public void bindLayer(String layerName, WorldLayer layer) {
        this.dependantLayers.put(layerName, layer);
    }

    @Override
    public WorldLayer getLayer(String layerName) {
        return this.dependantLayers.get(layerName);
    }
}
