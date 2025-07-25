/*
 * Copyright (C) 2023-2025 Thomas Akehurst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tomakehurst.wiremock.matching;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotPattern extends StringValuePattern {

  private StringValuePattern unexpectedPattern;

  public NotPattern(@JsonProperty("not") StringValuePattern unexpectedPattern) {
    super(unexpectedPattern.getExpected());
    this.unexpectedPattern = unexpectedPattern;
  }

  public StringValuePattern getNot() {
    return unexpectedPattern;
  }

  @Override
  public MatchResult match(String value) {
    return invert(unexpectedPattern.match(value));
  }

  private MatchResult invert(final MatchResult matchResult) {
    return new MatchResult() {
      @Override
      public boolean isExactMatch() {
        return !matchResult.isExactMatch();
      }

      @Override
      public double getDistance() {
        if (isExactMatch()) {
          return 0;
        }

        return 1.0;
      }
    };
  }
}
