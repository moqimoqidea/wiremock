/*
 * Copyright (C) 2017-2025 Thomas Akehurst
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
package com.github.tomakehurst.wiremock.testsupport;

import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.common.ParameterUtils;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.StubMappingTransformer;
import com.github.tomakehurst.wiremock.recording.StubGenerationResult;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

public class StubMappingTransformerWithServeEvent extends StubMappingTransformer {
  @Override
  public StubGenerationResult transform(
      StubMapping stubMapping, FileSource files, Parameters parameters, ServeEvent serveEvent) {
    return new StubGenerationResult.Success(
        WireMock.get(
                urlEqualTo(
                    ParameterUtils.getFirstNonNull(
                            stubMapping.getRequest().getUrl(),
                            stubMapping.getRequest().getUrlPath())
                        + "?transformed="
                        + serveEvent.getRequest().queryParameter("some-query").firstValue()))
            .build());
  }

  @Override
  public String getName() {
    return "stub-transformer-with-serve-event";
  }
}
