package com.redmart.route.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Route {
    @Getter
    private MapPoint firstPoint;
    @Getter
    private MapPoint nextPoint;
    @Getter
    private MapPoint lastPoint;
    @Getter
    int length;

    public Route(MapPoint firstPoint, Route pathAhead) {
        this.firstPoint = firstPoint;
        this.nextPoint = pathAhead.firstPoint;
        this.lastPoint = pathAhead.lastPoint;
        this.length = pathAhead.length + 1;
    }


    public Route(MapPoint onlyPoint) {
        this.firstPoint = onlyPoint;
        this.lastPoint = onlyPoint;
        this.length = 1;
    }


    public int getSlope() {
        return firstPoint.getElevation() - lastPoint.getElevation();
    }


    public static Route longerSteeper(Route path1, Route path2) {
        if (null == path1) {
            return path2;
        }
        if (null == path2) {
            return path1;
        }
        if (path1.length > path2.length) {
            return path1;
        }
        if (path2.length > path1.length) {
            return path2;
        }

        //Equal Path length
        if (path1.getSlope() > path2.getSlope()) {
            return path1;
        }
        if (path2.getSlope() > path1.getSlope()) {
            return path2;
        }
        return null;
    }

}
