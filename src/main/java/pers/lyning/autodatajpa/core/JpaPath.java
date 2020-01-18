package pers.lyning.autodatajpa.core;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author lyning
 */
class JpaPath<T> {

    private final Root<T> root;

    JpaPath(Root<T> root) {this.root = root;}

    public Path<String> extractPath(List<String> fields) {
        if (fields.size() == 1) {
            return root.get(fields.get(0));
        }
        Join<Object, Object> join = null;
        for (int index = 0; index < fields.size(); index++) {
            if (index == 0) {
                join = root.join(fields.get(index));
                continue;
            }
            if (index == fields.size() - 1) {
                return join.get(fields.get(index));
            }
            join = join(join, fields.get(index));
        }
        return null;
    }

    private Join<Object, Object> join(Join<Object, Object> join, String column) {
        return join.join(column);
    }
}
