/**
 * Copyright 2014 Groupon.com
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

package models.messages;

import com.google.common.base.MoreObjects;

import java.nio.file.Path;

/**
 * Message class to indicate a log file is not longer existent in the target path.
 *
 * @author Mohammed Kamel (mkamel at groupon dot com))
 */
public class LogFileDisappeared {
    /**
     * Public constructor.
     *
     * @param file The file path that's no longeravailable
     */
    public LogFileDisappeared(final Path file) {
        _file = file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("File", _file)
                .toString();
    }

    /**
     * Gets the file path.
     *
     * @return the file path
     */
    public Path getFile() {
        return _file;
    }

    private final Path _file;
}