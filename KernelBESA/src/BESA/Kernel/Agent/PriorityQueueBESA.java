/*
 * @(#)PriorityQueueBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import java.util.ArrayList;

/**
 * This class is used to represent the handling of events in a message queue in
 * which the messages have a priority.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
class PriorityQueueBESA extends QueueBESA {

    /**
     *
     */
    private ArrayList<Byte> prioridades;

    /**
     * Constructs an empty priority queue.
     */
    public PriorityQueueBESA() {
        super();
        prioridades = new ArrayList<Byte>();
    }

    /**
     * Obtains the first element in the queue. After read the first element in
     * the queue, this is eliminated from the list of messages and priorities.
     *
     * @return The first Object in the queue.
     */
    public Object first() {
        // Sacar y eliminar el primer elemento de la cola
        if (queue.size() > 0) {
            prioridades.remove(0);
            return queue.remove(0);
        } else {
            return null;
        }
    }

    /**
     * Adds an element in ordered form according to its priority.
     *
     * @param element Element to be added.
     * @param prioridad Byte value that represents the priority of the element.
     */
    public void add(Object element, byte priority) {
        // Add in order based on priority
        int i = 0;
        // Find the position according to the priority of the new element
        if (!queue.isEmpty()) {
            while (i < queue.size()) {
                if (prioridades.get(i) <= priority) {
                    ++i;
                } else {
                    break;
                }
            }
        }
        // Add the element at position i
        queue.add(i, element);
        prioridades.add(i, priority);
    }

}
