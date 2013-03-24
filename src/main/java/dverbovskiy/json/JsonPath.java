package dverbovskiy.json;

import dverbovskiy.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * User: dverbovskiy
 * Date: 24.03.13
 * Time: 19:53
 */
class JSONPath {
    private List<PathPiece> path = new ArrayList<PathPiece>();
    static final int OBJECT = 0;
    static final int ARRAY = 1;
    static final int ELEMENT = 2;
    static final int FIELD = 3;

    JSONPath(String fullPath) throws IllegalArgumentException {
        // sample input:      "data.person.children.0.children.0.married"
        // must be marked as:  OBJECT.OBJECT.ARRAY.ELEMENT.ARRAY.ELEMENT.FIELD
        String[] pathParts = fullPath.toLowerCase().split("\\.");
        int limit = pathParts.length - 1;

        if (limit < 1) {
            throw new IllegalArgumentException("Path cannot be empty");
        }

        for (int i = 0; i < limit; i++) {
            int jsonType = OBJECT;
            boolean isInt = Util.isInteger(pathParts[i]);

            if (i == 0 && isInt) {
                throw new IllegalArgumentException("Root element must be an object");
            }

            if (isInt) {
                // editing/marking preLast element as an array
                path.get(i - 1).value = ARRAY;
                jsonType = ELEMENT;
            }
            path.add(new PathPiece(pathParts[i], jsonType));
        }
        path.add(new PathPiece(pathParts[limit + 1], FIELD));
    }

    private class PathPiece {
        String path;
        int value;

        PathPiece(String p, int v) {
            path = p;
            value = v;
        }
    }
}
