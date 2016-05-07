package com.redmart.route.Model;

import java.util.ArrayList;
import java.util.List;

public class ElevationMap {

    private List<List<MapPoint>> mapPoints;

    public ElevationMap(List<List<Integer>> grid) {

        this.mapPoints = new ArrayList<>(grid.size());

        for (int i = 0; i < grid.size(); i++) {
            List<Integer> row = grid.get(i);
            List rowPoints = new ArrayList<MapPoint>(row.size());
            for (int j = 0; j < row.size(); i++) {
                MapPoint mapPoint = new MapPoint(i, j, row.get(j));
                rowPoints.add(mapPoint);
            }
            mapPoints.add(rowPoints);
        }
    }

}
