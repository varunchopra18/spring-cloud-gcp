/*
 * Copyright 2017-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.spring.autoconfigure.trace.pubsub.brave;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import brave.propagation.Propagation;
import brave.test.propagation.PropagationSetterTest;
import com.google.pubsub.v1.PubsubMessage;

public class PubSubProducerRequestSetterTest extends PropagationSetterTest<PubSubProducerRequest> {
	PubSubProducerRequest request = new PubSubProducerRequest(
			PubsubMessage.newBuilder(), "myTopic"
	);

	@Override
	protected PubSubProducerRequest request() {
		return request;
	}

	@Override
	protected Propagation.Setter<PubSubProducerRequest, String> setter() {
		return PubSubProducerRequest.SETTER;
	}

	@Override
	protected Iterable<String> read(PubSubProducerRequest request, String key) {
		return StreamSupport.stream(request.delegate.getAttributesMap().entrySet().spliterator(), false)
				.filter(entry -> entry.getKey().equals(key))
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
	}
}
