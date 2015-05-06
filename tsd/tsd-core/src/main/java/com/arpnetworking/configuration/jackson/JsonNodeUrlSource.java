/**
 * Copyright 2015 Groupon.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.arpnetworking.configuration.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import net.sf.oval.constraint.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/**
 * <code>JsonNode</code> based configuration sourced from a file.
 *
 * @author Ville Koskela (vkoskela at groupon dot com)
 */
public final class JsonNodeUrlSource extends BaseJsonNodeSource implements JsonNodeSource {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<JsonNode> getValue(final String... keys) {
        return getValue(getJsonNode(), keys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", Integer.toHexString(System.identityHashCode(this)))
                .add("Url", _url)
                .add("JsonNode", _jsonNode)
                .toString();
    }

    /* package private */ Optional<JsonNode> getJsonNode() {
        return _jsonNode;
    }

    private JsonNodeUrlSource(final Builder builder) {
        super(builder);
        _url = builder._url;

        JsonNode jsonNode = null;
        try {
            jsonNode = _objectMapper.readTree(_url);
        } catch (final IOException e) {
            throw Throwables.propagate(e);
        }
        _jsonNode = Optional.fromNullable(jsonNode);
    }

    private final URL _url;
    private final Optional<JsonNode> _jsonNode;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonNodeUrlSource.class);

    /**
     * Builder for <code>JsonNodeUrlSource</code>.
     */
    public static final class Builder extends BaseJsonNodeSource.Builder<Builder, JsonNodeUrlSource> {

        /**
         * Public constructor.
         */
        public Builder() {
            super(JsonNodeUrlSource.class);
        }

        /**
         * Set the source <code>URL</code>.
         *
         * @param value The source <code>URL</code>.
         * @return This <code>Builder</code> instance.
         */
        public Builder setUrl(final URL value) {
            _url = value;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Builder self() {
            return this;
        }

        @NotNull
        private URL _url;
    }
}
