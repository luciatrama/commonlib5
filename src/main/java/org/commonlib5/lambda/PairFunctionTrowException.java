/*
 * Copyright (C) 2025 Nicola De Nisco
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.commonlib5.lambda;

/**
 * Represents a function that accepts one argument and produces a result.
 * Versione modificata di Function che può sollevare eccezioni.
 * Utilizzabile per procesare coppie di valori (esempio una map).
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <K> the type of the input to the function
 * @param <V> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 * @since 1.8
 */
@FunctionalInterface
public interface PairFunctionTrowException<K, V, R>
{

  /**
   * Applies this function to the given argument.
   *
   * @param k the function argument
   * @param v the function argument
   * @return the function result
   * @throws java.lang.Exception
   */
  R apply(K k, V v)
     throws Exception;
}
