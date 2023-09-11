package edu.eci.arsw.blueprints.filters;

import java.util.Set;
import edu.eci.arsw.blueprints.model.Blueprint;

public interface BlueprintFilters {
    
    public Blueprint filter(Blueprint blueprint);
    public Set<Blueprint> filters(Set<Blueprint> blueprints);

}
