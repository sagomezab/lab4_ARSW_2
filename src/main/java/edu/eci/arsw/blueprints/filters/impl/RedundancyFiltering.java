package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.filters.BlueprintFilters;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

//@Service
public class RedundancyFiltering implements BlueprintFilters{
    
    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints();
        List<Point> filterPoints = new ArrayList<>();

        for (int i = 0; i < points.size() - 1; i++) {
            Point pointIni = points.get(i);
            Point pointNexr = points.get(i + 1);
            if (pointIni.getX() != pointNexr.getX() && pointIni.getY() != pointNexr.getY()) {
                filterPoints.add(pointIni);
            }
            if ( i ==  points.size() - 2) {
                filterPoints.add(points.get(points.size() - 1));
            }
        }
        
        blueprint.setPoint(filterPoints);
        return blueprint;
    }

    @Override
    public Set<Blueprint> filters(Set<Blueprint> blueprints) {
        // TODO Auto-generated method stub
        for (Blueprint blueprint : blueprints){
            filter(blueprint);
        }
        return blueprints;
    }

}
