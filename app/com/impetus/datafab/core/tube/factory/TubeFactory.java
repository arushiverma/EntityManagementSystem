package com.impetus.datafab.core.tube.factory;

import com.impetus.datafab.core.BaseTube;
import com.impetus.datafab.core.Tube;

/** Factory for getting Tube.
 *
 */
public class TubeFactory {

    /** Get tube on basis of operation.
     *
     * @param operation - operation
     * @return tube object
     */
    public static Tube getTube(String operation) {
        // Handling inband requests


        OperationType operationType = OperationType.getOperationType(operation);

        Tube tube = null;
        switch (operationType) {
            case FIND:
            case FIND_ONE:
            case FIND_INTACT:
            case AGGREGATE:
            case COUNT:
                tube = new BaseTube();
                break;
        }
        return tube;
    }
}
