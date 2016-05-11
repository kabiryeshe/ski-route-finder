package com.rm.route.Model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ElevationMap {

    private List<List<MapPoint>> mapPoints;
    private Hashtable<MapPoint, RouteInfo> routes = new Hashtable<>();
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


    public RouteInfo findLongestRouteWithMaximumDrop() {
        RouteInfo longestSteepestRouteInfo = null;
        for (List<MapPoint> rows : mapPoints) {
            for (MapPoint mapPoint : rows) {
                RouteInfo routeInfo = getRoute(mapPoint);
                longestSteepestRouteInfo = RouteInfo.longerSteeper(longestSteepestRouteInfo, routeInfo);
            }
        }

        return longestSteepestRouteInfo;
    }

    public RouteInfo getRoute(MapPoint point) {

        if (routes.containsKey(point)) {
            return routes.get(point);
        }

        RouteInfo longestSteepestRouteInfo = new RouteInfo(point);

        List<MapPoint> neighbouringPoints = getNeighbouringPoints(point);
        if (!neighbouringPoints.isEmpty()) {
            for (MapPoint neighbourPoint : neighbouringPoints) {
                RouteInfo pathFromNeighbour = getRoute(neighbourPoint);
                RouteInfo pathFromPoint = new RouteInfo(point, pathFromNeighbour);
                longestSteepestRouteInfo = RouteInfo.longerSteeper(longestSteepestRouteInfo, pathFromPoint);
            }
        }

        routes.put(point, longestSteepestRouteInfo);
        return longestSteepestRouteInfo;
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


    public List<MapPoint> getCompleteRoute(RouteInfo routeInfo) {

        ArrayList<MapPoint> pointsOnRoute = new ArrayList<>();

        pointsOnRoute.add(routeInfo.getFirstPoint());

        while (routeInfo.getFirstPoint() != routeInfo.getLastPoint()) {
            MapPoint mapPoint = routeInfo.getNextPoint();
            pointsOnRoute.add(mapPoint);
            routeInfo = getRoute(routeInfo.getNextPoint());
        }

        return pointsOnRoute;
    }

}
