package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.filters.BlueprintFilters;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
public class SubsamplingFiltering implements BlueprintFilters{
    @Override
    public Blueprint filter(Blueprint blueprint){
        List<Point> points = blueprint.getPoints();
        List<Point> filterPoints = new ArrayList<>();

        for (int i = 0; i < points.size() - 1; i = i + 2){
                filterPoints.add(points.get(i));
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
