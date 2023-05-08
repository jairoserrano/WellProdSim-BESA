/*
 * @(#)XMLConfig.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;


/**
 * This class does load the configuration information of BESA container.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 * @since   JAXB2.0
 */
public class XMLConfig {

    /**
     * Represents the container tag.
     */
    protected Container container;

    /**
     * Gets the tag container.
     *
     * @return Tag container.
     */
    public Container getContainer() {
        return container;
    }
}
