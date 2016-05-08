package com.redmart.route.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ElevationMap {

    private List<List<MapPoint>> mapPoints;
    private HashMap<MapPoint, Route> routes = new HashMap<>();
    private int size;

    public ElevationMap(List<List<Integer>> grid) {

        this.mapPoints = new ArrayList<>(grid.size());
        this.size = grid.size();

        for (int i = 0; i < grid.size(); i++) {
            List<Integer> row = grid.get(i);
            List rowPoints = new ArrayList<MapPoint>(row.size());
            for (int j = 0; j < row.size(); j++) {
                MapPoint mapPoint = new MapPoint(i, j, row.get(j));
                rowPoints.add(mapPoint);
            }
            mapPoints.add(rowPoints);
        }
    }


    public Route findLongestRouteWithMaximumDrop() {
        Route longestSteepestRoute = null;
        for(List<MapPoint> rows : mapPoints) {
            for(MapPoint mapPoint : rows) {
                Route route = getRoute(mapPoint);
                longestSteepestRoute = Route.longerSteeper(longestSteepestRoute, route);
            }
        }

        return longestSteepestRoute;
    }

    private Route getRoute(MapPoint point) {

        if (routes.containsKey(point)) {
            return routes.get(point);
        }

        Route longestSteepestRoute = new Route(point);

        List<MapPoint> neighbouringPoints = getNeighbouringPoints(point);
        if (!neighbouringPoints.isEmpty()) {
            for (MapPoint neighbourPoint : neighbouringPoints) {
                Route pathFromNeighbour = getRoute(neighbourPoint);
                Route pathFromPoint = new Route(point, pathFromNeighbour);
                longestSteepestRoute = Route.longerSteeper(longestSteepestRoute, pathFromPoint);
            }
        }

        routes.put(point, longestSteepestRoute);
        return longestSteepestRoute;
    }


    protected List<MapPoint> getNeighbouringPoints(MapPoint point) {

        ArrayList<MapPoint> neighbourPoints = new ArrayList<>();

        //North exists
        if (point.getX() > 0) {
            neighbourPoints.add(mapPoints.get(point.getX() - 1).get(point.getY()));
        }

        // East exists
        if (point.getY() < size - 1) {
            neighbourPoints.add(mapPoints.get(point.getX()).get(point.getY() + 1));
        }
        //West exists
        if (point.getY() > 0) {
            neighbourPoints.add(mapPoints.get(point.getX()).get(point.getY() - 1));
        }
        //South exists
        if (point.getX() < size - 1) {
            neighbourPoints.add(mapPoints.get(point.getX() + 1).get(point.getY()));
        }

        return neighbourPoints.stream().filter(np -> np.getElevation() < point.getElevation()).collect(toList());
    }

}
