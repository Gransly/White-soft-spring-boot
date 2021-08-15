package com.example.calculatorspring.filter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;

public class OptionalPredicate {

    public static OptionalPredicateBuilder builder() {
        return new OptionalPredicateBuilder();
    }

    public static class OptionalPredicateBuilder {
        private BooleanBuilder builder = new BooleanBuilder();

        public <T> OptionalPredicateBuilder optionalAnd(T item, Function<T, BooleanExpression> function) {
            if (item != null)
                builder.and(function.apply(item));
            return this;
        }

        public Predicate build() {
            return builder;
        }
    }
}

