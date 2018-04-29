package com.jsen.test.utils.modelcore.common.logical;

import com.google.common.collect.Maps;
import com.jsen.test.utils.modelcore.common.CalcType;
import com.jsen.test.utils.modelcore.common.logical.builder.*;

import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public class CalcBuilder {
    private static Map<CalcType, AbstractBuild> builderMap = Maps.newHashMap();

    static {
        builderMap.put(CalcType.ModeAggregation, new ModeAggregationBuild());
        builderMap.put(CalcType.AddColumns, new ModelAddColumnBuild());
        builderMap.put(CalcType.ChangeColumn, new ModelChangeColumnBuild());


        builderMap.put(CalcType.AggJoin, new ModelInnerJoinBuild());
        builderMap.put(CalcType.LeftJoin, new ModelInnerJoinBuild());
        builderMap.put(CalcType.FullJoin, new ModelFullJoinBuild());
    }

    public static AbstractBuild getBuild(CalcType calcType) {
        return builderMap.get(calcType);
    }
}
