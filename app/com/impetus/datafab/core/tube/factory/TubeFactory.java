package com.impetus.datafab.core.tube.factory;

import com.impetus.datafab.core.SearchTube;
import com.impetus.datafab.core.SyncTube;
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


        OperationType operationType = OperationType.getOperationType(operation);

        Tube tube = null;
        switch (operationType) {
            case FIND:
            case FIND_ONE:
            //case COUNT:
                tube = new SearchTube();
                break;
            case UPDATE:
            case CREATE:
            	tube = new SyncTube();
            	break;
        }
        return tube;
    }
}
