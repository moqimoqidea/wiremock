/*
 * Copyright (C) 2018-2025 Thomas Akehurst
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
package com.github.tomakehurst.wiremock.security;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import java.util.Collections;
import java.util.List;

public class SingleHeaderClientAuthenticator implements ClientAuthenticator {

  private final String key;
  private final String value;

  public SingleHeaderClientAuthenticator(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public List<HttpHeader> generateAuthHeaders() {
    return Collections.singletonList(new HttpHeader(key, value));
  }
}
