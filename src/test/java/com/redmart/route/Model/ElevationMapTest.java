package com.redmart.route.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class ElevationMapTest {

    private ElevationMap elevationMap;

    @Before
    public void setUp() throws Exception {
        List<List<Integer>> grid = asList(
                asList(4, 8, 7, 3),
                asList(2, 5, 9, 3),
                asList(6, 3, 2, 5),
                asList(4, 4, 1, 6));
        elevationMap = new ElevationMap(grid);
    }

    @Test
    public void shouldReturnEmptyListWhenNoNeighboringPointsWithLesserElevation() throws Exception {
        List<MapPoint> neighbouringPoints = elevationMap.getNeighbouringPoints(new MapPoint(3,0, 4));

        assertThat(neighbouringPoints.size(), is(0));
    }

    @Test
    public void shouldReturnNeighboringPointsWithLesserElevation() throws Exception {
        List<MapPoint> neighbouringPoints = elevationMap.getNeighbouringPoints(new MapPoint(1, 1, 5));

        assertThat(neighbouringPoints.size(), is(2));
        List<Integer> actualElevation = neighbouringPoints.stream().map(MapPoint::getElevation).collect(toList());
        assertThat(actualElevation, hasItems(3, 2));
    }

    @Test
    public void shouldHaveLongestRouteLengthAsOneWhenOnlyOnePoint() throws Exception {
        RouteInfo routeInfo = new ElevationMap(asList(asList(12))).findLongestRouteWithMaximumDrop();

        assertThat(routeInfo.getLength(), is(1));
        assertThat(routeInfo.getSlope(), is(0));
    }

    @Test
    public void shouldGiveSteepestAndLongestRoute() throws Exception {
        RouteInfo routeInfo = elevationMap.findLongestRouteWithMaximumDrop();

        assertThat(routeInfo.getLength(), is(5));
        assertThat(routeInfo.getSlope(), is(8));
    }

    @Test
    public void shouldGiveAllThePointsOnTheRoute() throws Exception {

        RouteInfo routeInfo = elevationMap.findLongestRouteWithMaximumDrop();
        List<MapPoint> completeRoute = elevationMap.getCompleteRoute(routeInfo);
        List<Integer> actualElevations = completeRoute.stream().map(MapPoint::getElevation).collect(toList());
        assertThat(actualElevations, contains(9, 5, 3, 2, 1));

    }
}