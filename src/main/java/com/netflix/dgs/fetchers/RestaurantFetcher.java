package com.netflix.dgs.fetchers;

import com.netflix.dgs.model.Dish;
import com.netflix.dgs.model.Restaurant;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class RestaurantFetcher {

    List<Restaurant> allRestaurants = List.of(
            new Restaurant(1, "Famous 1", 123, "Glasgow"),
            new Restaurant(2, "Famous 2", 234, "Edinburgh"),
            new Restaurant(3, "Famous 3", 345, "Pune"),
            new Restaurant(4, "Famous 4", 567, "Jbp")
    );

    List<Dish> dishes = List.of(
            new Dish(1, 1, "Ckn Biryani", 10),
            new Dish(2, 2, "Pastry", 11),
            new Dish(3, 3, "Burger", 12),
            new Dish(4, 4, "Pizza", 13)
    );

    @DgsData(parentType = "Query", field = "restaurants")
    // If mapping with DgsQuery is given then method name is matched with query name
    public List<Restaurant> getAllRestaurants(@InputArgument(name = "name") String name1) {
        // InputArgument by default takes param name as argument name Gql query
        if (name1 == null) {
            return null;
        }
        return allRestaurants.stream().filter(temp -> temp.getName().equalsIgnoreCase(name1))
                .collect(Collectors.toList());

    }

    @DgsData(parentType = "Restaurant", field = "dish")
    public List<Dish> getDishes(DataFetchingEnvironment dfe) {
        Restaurant restaurant = dfe.getSource();
        return dishes.stream().filter(temp -> temp.getRestId().equals(restaurant.getId()))
                .collect(Collectors.toList());

    }
}
