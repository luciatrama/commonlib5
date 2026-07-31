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

import java.util.Objects;

/**
 * Represents an operation that accepts a single input argument and returns no
 * result. Unlike most other functional interfaces, {@code ConsumerThrowException} is expected
 * to operate via side-effects.
 * Questa versione usa due operatori.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object)}.
 *
 * @param <T> the type of the input to the operation
 * @param <V> the type of the input to the operation
 *
 * @since 1.8
 */
@FunctionalInterface
public interface PairConsumerThrowException<T, V>
{

  /**
   * Performs this operation on the given argument.
   *
   * @param t the input argument
   * @param v the input argument
   * @throws java.lang.Exception
   */
  void accept(T t, V v)
     throws Exception;

  /**
   * Returns a composed {@code ConsumerThrowException} that performs, in sequence, this
   * operation followed by the {@code after} operation. If performing either
   * operation throws an exception, it is relayed to the caller of the
   * composed operation. If performing this operation throws an exception,
   * the {@code after} operation will not be performed.
   *
   * @param after the operation to perform after this operation
   * @return a composed {@code ConsumerThrowException} that performs in sequence this
   * operation followed by the {@code after} operation
   * @throws NullPointerException if {@code after} is null
   */
  default PairConsumerThrowException<T, V> andThen(PairConsumerThrowException<? super T, ? super V> after)
  {
    Objects.requireNonNull(after);
    return (T t, V v) ->
    {
      accept(t, v);
      after.accept(t, v);
    };
  }
}
