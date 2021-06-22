package com.netflix.dgs.fetchers;

import com.netflix.dgs.model.Restaurant;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import graphql.ExecutionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(classes = {DgsAutoConfiguration.class, RestaurantFetcher.class})
public class RestaurantFetcherTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    public void testGetRestaurants() {
        ExecutionResult result = dgsQueryExecutor.execute("{ restaurants(name: \"Famous 1\") {\n" +
                "  id\n" +
                "  name\n" +
                "  contact\n" +
                "}}");
        List<Restaurant> restaurants = (List<Restaurant>)((Map<String, Object>)result.getData()).get("restaurants");
        assertThat(restaurants.size()).isEqualTo(1);
    }
}
