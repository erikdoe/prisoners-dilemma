package com.mullekybernetik.ipd.searcher;

import com.mullekybernetik.ipd.Searcher;
import com.mullekybernetik.ipd.strategies.Strategy;
import com.mullekybernetik.ipd.strategies.StrategyFactory;
import com.mullekybernetik.ipd.strategies.basic.Cooperator;
import com.mullekybernetik.ipd.strategies.basic.Defector;
import com.mullekybernetik.ipd.strategies.basic.TitForTat;
import com.mullekybernetik.ipd.strategies.encoded.ConditionalCooperatorFactory;
import com.mullekybernetik.ipd.tournament.Table;
import com.mullekybernetik.ipd.tournament.TableEntry;

import java.util.ArrayList;
import java.util.List;

public class RandomSearcher implements Searcher {

    private static final int SURVIVOR_PERCENTAGE = 80;
    private static final int STRATEGY_DEPTH = 5;

    @Override
    public List<Strategy> createInitialPopulation(int populationSize) {
        List<Strategy> strategies = new ArrayList<>();
        for (int i = 0; i < populationSize / 2 / 3; i++) {
            strategies.add(new Cooperator());
            strategies.add(new Defector());
            strategies.add(new TitForTat());
        }
        fillWithNewRandomStrategies(strategies, populationSize);
        return strategies;
    }

    @Override
    public List<Strategy> createNextPopulation(int populationSize, Table result) {
        List<Strategy> strategies = new ArrayList<>();
        for (TableEntry e : result.getTopEntries(populationSize * SURVIVOR_PERCENTAGE/100)) {
            strategies.add(e.getStrategy());
        }
        fillWithNewRandomStrategies(strategies, populationSize);
        return strategies;
    }

    private void fillWithNewRandomStrategies(List<Strategy> strategies, int populationSize) {
        StrategyFactory factory = new ConditionalCooperatorFactory();
        int n = populationSize - strategies.size();
        strategies.addAll(factory.createRandomStrategies(n, STRATEGY_DEPTH));
    }

}
