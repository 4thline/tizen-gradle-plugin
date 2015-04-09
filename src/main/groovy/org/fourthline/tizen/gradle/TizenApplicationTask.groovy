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

import java.nio.file.Files
import java.nio.file.Paths

abstract class TizenApplicationTask extends TizenTask {

    def configure(Tizen tizen) {
        super.configure(tizen)

        if (!tizen.build.output) {
            tizen.build.output = "$project.buildDir/tizen/$tizen.applicationId"
        }

        if (!tizen.signing.input) {
            tizen.signing.input = tizen.build.output
        }

        if (!tizen.signing.profile) {
            tizen.signing.profile = "default"
        }
        if (!tizen.signing.profilesXml) {
            tizen.signing.profilesXml = "${tizen.sdkPath}/../workspace/.metadata/.plugins/org.tizen.common.sign/profiles.xml"
        }

        if (!Files.exists(Paths.get(tizen.signing.profilesXml))) {
            throw new InvalidUserDataException(
                    "Signing profiles XML file '$tizen.signing.profilesXml' doesn't exist, are you sure " +
                            "you created everything with the fantastic Tizen Eclipse IDE? You should be able to find it in " +
                            "'workspace/.metadata/.plugins/org.tizen.common.sign/profiles.xml'."
            )
        }

        if (!tizen.packaging.input) {
            tizen.packaging.input = tizen.build.output
        }

        if (!tizen.packaging.output) {
            tizen.packaging.output = "$project.buildDir/tizen/${tizen.applicationId}.wgt"
        }

        if (!tizen.install.widget) {
            tizen.install.widget = tizen.packaging.output
        }
    }

    @Override
    boolean requiresApplicationId() {
        true
    }
}
