/*
 * Copyright (C) 2015 4th Line GmbH, Switzerland
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.fourthline.tizen.gradle

import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.JavaExec

import java.nio.file.Files
import java.nio.file.Paths

class TizenTask extends JavaExec {

    @Override
    void exec() {
        Tizen tizen = project.tizen

        if (tizen.sdkPath == null || !Files.exists(Paths.get(tizen.sdkPath))) {
            throw new InvalidUserDataException("Invalid Tizen sdkPath: " + tizen.sdkPath)
        }

        configure(tizen)

        if (project.hasProperty("tizenLogLevel")) {
            args "--log", project.property("tizenLogLevel")
        } else if (tizen.logLevel) {
            args "--log", tizen.logLevel
        }

        if ((project.hasProperty("tizenQuiet") && project.property("tizenQuiet")) || tizen.quiet) {
            args "--quiet"
        }

        systemProperty "log4j.configuration", "log4j.xml"

        setStandardOutput(System.out)
        setErrorOutput(System.err)

        classpath(project.file("$tizen.sdkPath/tools/ide/conf"))
        classpath(project.fileTree("$tizen.sdkPath/tools/ide/lib/"))

        super.exec()
    }

    def configure(Tizen tizen) {

        if (project.hasProperty("tizenApplicationId")) {
            tizen.applicationId = project.property("tizenApplicationId")
        }

        if (requiresApplicationId() && !tizen.applicationId) {
            throw new InvalidUserDataException("Please set tizen.applicationId with same identifier as in your widget's config.xml")
        }
    }

    boolean requiresApplicationId() {
        false;
    }
}
