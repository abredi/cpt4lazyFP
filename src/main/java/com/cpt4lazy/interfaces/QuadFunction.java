package com.cpt4lazy.interfaces;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * @param <N> the type of the input to the function
 * @param <O> the type of the input to the function
 * @param <P> the type of the input to the function
 * @param <Q> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 * @Author Abdulaziz Ali
 */
@FunctionalInterface
public interface QuadFunction<N, O, P, Q, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param n the function argument
     * @param o the function argument
     * @param p the function argument
     * @param q the function argument
     * @return the function result
     */
    R apply(N n, O o, P p, Q q);
}
